package com.el3asas.regym.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import com.el3asas.regym.DB.Repository;
import com.el3asas.regym.DB.models.LongPlan;
import com.el3asas.regym.R;
import com.el3asas.regym.receivers.AlarmService;
import com.el3asas.regym.helpers.LongPlans;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.Calendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LongPlanBotmDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private LinearLayout sat, sun, mon, tue, wed, thu, fri;
    private boolean[] selected;

    private boolean monthly_weekly;
    private int plan = 6;
    private NumberPicker hours, minutes, am_pm;

    private Repository repository;
    private final CompositeDisposable disposable = new CompositeDisposable();

    @SuppressLint({"CommitPrefEdits", "NonConstantResourceId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_dialog, container, false);

        repository = Repository.getInstance(requireActivity());

        View picker = view.findViewById(R.id.picker);
        hours = picker.findViewById(R.id.hours);
        minutes = picker.findViewById(R.id.minutes);
        am_pm = picker.findViewById(R.id.am_pm);

        NumberPicker numberPicker = view.findViewById(R.id.numberPicker);

        numberPicker.setDividerColor(ContextCompat.getColor(requireActivity(), R.color.actv));
        numberPicker.setDividerColorResource(R.color.actv);

        numberPicker.setTextColor(ContextCompat.getColor(requireActivity(), R.color.inActv));
        numberPicker.setTextColorResource(R.color.inActv);

        numberPicker.setSelectedTextColor(ContextCompat.getColor(requireActivity(), R.color.actv));
        numberPicker.setSelectedTextColorResource(R.color.actv);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(20);
        numberPicker.setValue(6);
        numberPicker.setOnValueChangedListener((picker1, oldVal, newVal) -> plan = newVal);

        selected = new boolean[7];

        monthly_weekly = true;

        RadioGroup radioGroup = view.findViewById(R.id.group);
        RadioButton month = radioGroup.findViewById(R.id.monthly);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.monthly:
                    monthly_weekly = false;
                    break;
                case R.id.weekly:
                    monthly_weekly = true;
                    break;
            }
        });

        repository.initLongPlanDao();

        setPicker(
                Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                Calendar.getInstance().get(Calendar.AM_PM));
        disposable.add(repository.getLongPlanInfo(0).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(longPlan -> {
                    selected = longPlan.getSelected();
                    setPicker(
                            longPlan.getHour(),
                            longPlan.getMinute(),
                            longPlan.getAm_pm());
                    monthly_weekly = longPlan.isWeek_cheek();
                    month.setChecked(!monthly_weekly);
                    plan = longPlan.getSelectedPlan();
                    numberPicker.setValue(plan);

                    selectedListener(sat, selected[0]);
                    selectedListener(sun, selected[1]);
                    selectedListener(mon, selected[2]);
                    selectedListener(tue, selected[3]);
                    selectedListener(wed, selected[4]);
                    selectedListener(thu, selected[5]);
                    selectedListener(fri, selected[6]);
                }, throwable -> Log.d("bottom", "error" + throwable.getMessage())));

        sat = view.findViewById(R.id.sat);
        sun = view.findViewById(R.id.sun);
        mon = view.findViewById(R.id.mon);
        tue = view.findViewById(R.id.tue);
        wed = view.findViewById(R.id.wed);
        thu = view.findViewById(R.id.thu);
        fri = view.findViewById(R.id.fri);

        sat.setOnClickListener(this);
        sun.setOnClickListener(this);
        mon.setOnClickListener(this);
        tue.setOnClickListener(this);
        wed.setOnClickListener(this);
        thu.setOnClickListener(this);
        fri.setOnClickListener(this);

        Button save, del;
        save = view.findViewById(R.id.save);
        del = view.findViewById(R.id.del);

        ImageButton delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(v -> {
            repository.initPlanDao();
            if (!AlarmService.service_started) {
                requireActivity().stopService(new Intent(requireActivity(), AlarmService.class));
            }else {
                AlarmService.longPlanStarted=false;
            }
            disposable.add(
                    repository.deleteLongPlan(0)
                            .observeOn(Schedulers.io())
                            .subscribeOn(Schedulers.io())
                            .subscribe());
            LongPlans longPlans = new LongPlans();
            longPlans.init(requireContext());
            longPlans.stopPlan();
            Toast.makeText(requireActivity(), R.string.youDeleteWPlan, Toast.LENGTH_LONG).show();
        });

        save.setOnClickListener(v -> {
            long end=saveEvent();
            LongPlans longPlans = new LongPlans();
            longPlans.init(requireContext());
            Calendar calendar = longPlans.getCalenders(selected, hours.getValue(), minutes.getValue(), am_pm.getValue(),end);
            if (calendar != null)
                longPlans.startService(calendar);
            dismiss();
        });

        del.setOnClickListener(v ->

                dismiss());
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setOnShowListener(dia -> {
            BottomSheetDialog dialog = (BottomSheetDialog) dia;
            FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            assert bottomSheet != null;
            bottomSheet.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            BottomSheetBehavior.from(bottomSheet).setSkipCollapsed(false);
            BottomSheetBehavior.from(bottomSheet).setHideable(false);
        });
        bottomSheetDialog.setCancelable(false);

        return bottomSheetDialog;
    }

    @SuppressLint({"NonConstantResourceId", "ResourceAsColor"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sat:
                selected[0] = !selected[0];
                selectedListener(sat, selected[0]);
                break;
            case R.id.sun:
                selected[1] = !selected[1];
                selectedListener(sun, selected[1]);
                break;
            case R.id.mon:
                selected[2] = !selected[2];
                selectedListener(mon, selected[2]);
                break;
            case R.id.tue:
                selected[3] = !selected[3];
                selectedListener(tue, selected[3]);
                break;
            case R.id.wed:
                selected[4] = !selected[4];
                selectedListener(wed, selected[4]);
                break;
            case R.id.thu:
                selected[5] = !selected[5];
                selectedListener(thu, selected[5]);
                break;
            case R.id.fri:
                selected[6] = !selected[6];
                selectedListener(fri, selected[6]);
                break;
        }
    }

    private void selectedListener(View v, boolean b) {
        if (b)
            v.setBackground(AppCompatResources.getDrawable(requireActivity(), R.drawable.selected_calender_item_background));
        else
            v.setBackground(AppCompatResources.getDrawable(requireActivity(), R.drawable.calender_item_background));
    }

    private long saveEvent() {
        Toast.makeText(requireActivity(), R.string.editsytings, Toast.LENGTH_LONG).show();
        long end;
        if (monthly_weekly)
            end = System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000L);
        else
            end = System.currentTimeMillis() + (30 * 24 * 60 * 60 * 1000L);

        if (!AlarmService.service_started) {
            Intent intent = new Intent(requireActivity(), AlarmService.class);
            ContextCompat.startForegroundService(requireActivity(), intent);
        }
        disposable.add(repository.setLongPlanInfo(new LongPlan(0, selected, hours.getValue(), minutes.getValue(), am_pm.getValue(), end, plan, monthly_weekly))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> Log.d("TAG", "saveEvent: +++++++++++")
                        , throwable -> Log.d("TAG", "saveEvent: ++++++" + throwable.getMessage())));

        return end;
    }

    private void setPicker(int hour, int min, int ap) {
        setNumPickerStyle(hours);
        setNumPickerStyle(minutes);
        setNumPickerStyle(am_pm);
        am_pm.setDisplayedValues(new String[]{"AM", "PM"});
        hours.setValue(hour);
        minutes.setValue(min);
        am_pm.setValue(ap);
    }

    private void setNumPickerStyle(NumberPicker numberPicker) {
        numberPicker.setDividerColor(ContextCompat.getColor(requireActivity(), R.color.actv));
        numberPicker.setDividerColorResource(R.color.actv);

        numberPicker.setTextColor(ContextCompat.getColor(requireActivity(), R.color.inActv));
        numberPicker.setTextColorResource(R.color.inActv);

        numberPicker.setSelectedTextColor(ContextCompat.getColor(requireActivity(), R.color.actv));
        numberPicker.setSelectedTextColorResource(R.color.actv);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.clear();
    }
}