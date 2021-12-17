package com.el3asas.regym.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.el3asas.regym.DB.Repository;
import com.el3asas.regym.DB.models.ProfileInfo;
import com.el3asas.regym.R;
import com.el3asas.regym.databinding.ProfileFragmentBinding;
import com.el3asas.regym.helpers.Ads;
import com.el3asas.regym.helpers.colSo3rat;
import com.google.android.ads.nativetemplates.TemplateView;

import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "profile";
    private TextView val1;
    private TextView val2;
    private TextView val3;
    private TextView val4;
    private TextView val5;
    private TextView so3rVal, cardS3orVal;
    private String jobStatusS, genderr;
    private AlertDialog dialog;
    private CardView info;
    private ImageView arrow;
    private Repository repository;
    private SharedPreferences profInfo;
    private final CompositeDisposable disposable = new CompositeDisposable();

    private String vall = null;

    private TemplateView templateView;

    private Ads ads;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment, container, false);

        templateView = v.findViewById(R.id.my_template);

        ads = new Ads();
        info = v.findViewById(R.id.info);
        CardView so3rCard = v.findViewById(R.id.so3rCard);
        cardS3orVal = v.findViewById(R.id.youSo3r);
        cardS3orVal.setOnClickListener(this);

        LinearLayout goToInfo = v.findViewById(R.id.goToInfo);
        goToInfo.setOnClickListener(this);
        info.setVisibility(View.GONE);

        so3rCard.setOnClickListener(this);

        arrow = goToInfo.findViewById(R.id.arrow);
        ImageView dark = v.findViewById(R.id.dark);
        TextView weekPlans = v.findViewById(R.id.weekPlans);

        final LongPlanBotmDialog bottomSheet = new LongPlanBotmDialog();
        weekPlans.setOnClickListener(v12 -> bottomSheet.show(getParentFragmentManager(), ""));

        final SharedPreferences preferences = requireActivity().getSharedPreferences("dark", 0);
        profInfo = requireActivity().getSharedPreferences("profileInfo", 0);

        if (!preferences.getBoolean("check", false))
            dark.setImageResource(R.drawable.ic_moon__1_);
        else
            dark.setImageResource(R.drawable.ic_sun);

        dark.setOnClickListener(v1 -> {
            boolean b = false;
            if (!preferences.getBoolean("check", false)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                b = true;
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("check", b);
            editor.apply();
        });

        ImageView icon1, icon2, icon3, icon4, icon5;
        TextView title1, title2, title3, title4, title5;
        View ageCard, heightCard, weightCard, effortCard, genderCard;

        ageCard = v.findViewById(R.id.age);
        heightCard = v.findViewById(R.id.height);
        weightCard = v.findViewById(R.id.weight);
        effortCard = v.findViewById(R.id.effort);
        genderCard = v.findViewById(R.id.genderCard);

        ageCard.setOnClickListener(this);
        effortCard.setOnClickListener(this);
        heightCard.setOnClickListener(this);
        weightCard.setOnClickListener(this);
        genderCard.setOnClickListener(this);

        icon1 = ageCard.findViewById(R.id.icon);
        icon2 = weightCard.findViewById(R.id.icon);
        icon4 = effortCard.findViewById(R.id.icon);
        icon3 = heightCard.findViewById(R.id.icon);
        icon5 = genderCard.findViewById(R.id.icon);

        title1 = ageCard.findViewById(R.id.title);
        title2 = weightCard.findViewById(R.id.title);
        title4 = effortCard.findViewById(R.id.title);
        title3 = heightCard.findViewById(R.id.title);
        title5 = genderCard.findViewById(R.id.title);

        val1 = ageCard.findViewById(R.id.val);
        val4 = effortCard.findViewById(R.id.val);
        val2 = weightCard.findViewById(R.id.val);
        val3 = heightCard.findViewById(R.id.val);
        val5 = genderCard.findViewById(R.id.val);

        so3rVal = v.findViewById(R.id.so3rVal);

        icon1.setImageResource(R.drawable.age);
        title1.setText(R.string.enterage);

        icon2.setImageResource(R.drawable.weighttt);
        title2.setText(R.string.weightt);

        icon3.setImageResource(R.drawable.height);
        title3.setText(R.string.height);

        icon4.setImageResource(R.drawable.effort);
        title4.setText(R.string.effort);

        icon5.setImageResource(R.drawable.gender);
        title5.setText(R.string.genderr);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        repository = Repository.getInstance(requireActivity());
        repository.initProfileDao();
        getBackData();
    }

    @SuppressLint({"InflateParams", "NonConstantResourceId", "ResourceAsColor", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        View vv;
        Button go;
        AlertDialog.Builder builder;
        WindowManager.LayoutParams wmlp;
        builder = new AlertDialog.Builder(requireActivity());
        vv = getLayoutInflater().inflate(R.layout.dialog_edit_text, null);
        com.shawnlin.numberpicker.NumberPicker numberPicker = vv.findViewById(R.id.numberPicker);

        numberPicker.setDividerColor(ContextCompat.getColor(requireActivity(), R.color.actv));
        numberPicker.setDividerColorResource(R.color.actv);

        numberPicker.setTextColor(ContextCompat.getColor(requireActivity(), R.color.inActv));
        numberPicker.setTextColorResource(R.color.inActv);

        numberPicker.setSelectedTextColor(ContextCompat.getColor(requireActivity(), R.color.actv));
        numberPicker.setSelectedTextColorResource(R.color.actv);

        switch (v.getId()) {
            case R.id.age:
                TextView title = vv.findViewById(R.id.DialogTitle);
                title.setText(getString(R.string.enterage));
                numberPicker.setMinValue(0);
                numberPicker.setMaxValue(100);
                numberPicker.setValue(23);
                vall = "23";
                numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> vall = newVal + "");

                go = vv.findViewById(R.id.go);
                go.setOnClickListener(v1 -> {
                    val1.setText(vall);
                    dialog.dismiss();
                });
                builder.setView(vv);
                dialog = builder.create();
                wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.effort:
                builder = new AlertDialog.Builder(requireActivity());
                vv = getLayoutInflater().inflate(R.layout.dialog_effort, null);
                RadioGroup radioGroup = vv.findViewById(R.id.jobStatus);
                jobStatusS = getString(R.string.level1);
                radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                    switch (checkedId) {
                        case R.id.level1:
                            jobStatusS = getString(R.string.level1);
                            break;
                        case R.id.level2:
                            jobStatusS = getString(R.string.level2);
                            break;
                        case R.id.level3:
                            jobStatusS = getString(R.string.level3);
                            break;
                    }
                });
                go = vv.findViewById(R.id.go);
                go.setOnClickListener(v15 -> {
                    val4.setText(jobStatusS);
                    dialog
                            .dismiss();
                });
                builder.setView(vv);
                dialog = builder.create();
                wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.height:
                builder = new AlertDialog.Builder(requireActivity());
                title = vv.findViewById(R.id.DialogTitle);
                title.setText(getString(R.string.enterheight));
                numberPicker.setMinValue(0);
                numberPicker.setMaxValue(250);
                numberPicker.setValue(170);
                vall = "170";
                numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> vall = newVal + "");

                go = vv.findViewById(R.id.go);
                go.setOnClickListener(v16 -> {
                    val3.setText(vall);
                    dialog.dismiss();
                });
                builder.setView(vv);
                dialog = builder.create();
                wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.weight:
                title = vv.findViewById(R.id.DialogTitle);
                title.setText(getString(R.string.enterweight));
                numberPicker.setMinValue(0);
                numberPicker.setMaxValue(200);
                numberPicker.setValue(78);
                vall = "78";
                numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> vall = newVal + "");

                go = vv.findViewById(R.id.go);
                go.setOnClickListener(v12 -> {
                    val2.setText(vall);
                    dialog.dismiss();
                });
                builder.setView(vv);
                dialog = builder.create();
                wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.genderCard:
                builder = new AlertDialog.Builder(requireActivity());
                vv = getLayoutInflater().inflate(R.layout.dialog_gender, null);

                RadioGroup radioGroup2 = vv.findViewById(R.id.gender);
                genderr = getString(R.string.male);
                radioGroup2.setOnCheckedChangeListener((group, checkedId) -> {
                    switch (checkedId) {
                        case R.id.male:
                            genderr = getString(R.string.male);
                            break;
                        case R.id.female:
                            genderr = getString(R.string.female);
                            break;
                    }
                });

                go = vv.findViewById(R.id.go);
                builder.setView(vv);
                dialog = builder.create();
                go.setOnClickListener(v17 -> {
                    val5.setText(genderr);
                    dialog.dismiss();
                });
                wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;

            case R.id.so3rCard:
                final Handler handler = new Handler(Looper.getMainLooper());
                if (profInfo.getInt("count", 0) == 0)
                    try {
                        profInfo.edit().putInt("count", 1).apply();
                        int bodyStatus = getSo4rat();
                        String s = String.format(Locale.ENGLISH, "%s %d %s %s %d %s %s", getString(R.string.sneed), bodyStatus, getString(R.string.nowww), getString(R.string.sNeedd), bodyStatus - 500, getString(R.string.sneeeed), getString(R.string.end));
                        cardS3orVal.setText(bodyStatus + "");
                        so3rVal.setText(s);
                        info.setVisibility(View.VISIBLE);
                        arrow.setImageResource(R.drawable.row_up);
                        handler.postDelayed(() -> {
                            info.setVisibility(View.GONE);
                            arrow.setImageResource(R.drawable.row_down);
                        }, 5000);
                    } catch (Exception ignored) {
                    }
                else
                    try {
                        builder = new AlertDialog.Builder(requireActivity());
                        vv = getLayoutInflater().inflate(R.layout.sso3rat_info_dialog, null);

                        TextView infoo = vv.findViewById(R.id.so3rVal);
                        TemplateView templateView = vv.findViewById(R.id.my_template);
                        int bodyStatus = getSo4rat();
                        String s = String.format(Locale.ENGLISH, "%s %d %s %s %d %s %s", getString(R.string.sneed), bodyStatus, getString(R.string.nowww), getString(R.string.sNeedd), bodyStatus - 500, getString(R.string.sneeeed), getString(R.string.end));
                        infoo.setText(s);

                        Button y, n;
                        y = vv.findViewById(R.id.yes);
                        n = vv.findViewById(R.id.no);

                        y.setOnClickListener(v13 -> {
                            int bodyStatus1 = getSo4rat();
                            cardS3orVal.setText((bodyStatus1 - 500) + "");
                            String s1 = String.format(Locale.ENGLISH, "%s %d %s %s %d %s %s", getString(R.string.sneed), bodyStatus1, getString(R.string.nowww), getString(R.string.sNeedd), bodyStatus1 - 500, getString(R.string.sneeeed), getString(R.string.end));
                            so3rVal.setText(s1);

                            dialog.dismiss();
                            info.setVisibility(View.VISIBLE);
                            arrow.setImageResource(R.drawable.row_up);
                            handler.postDelayed(() -> {
                                info.setVisibility(View.GONE);
                                arrow.setImageResource(R.drawable.row_down);
                            }, 5000);
                        });
                        n.setOnClickListener(v14 -> {
                            getBackData();
                            dialog.dismiss();
                            info.setVisibility(View.VISIBLE);
                            arrow.setImageResource(R.drawable.row_up);
                            handler.postDelayed(() -> {
                                info.setVisibility(View.GONE);
                                arrow.setImageResource(R.drawable.row_down);
                            }, 5000);
                        });

                        builder.setView(vv);
                        dialog = builder.create();
                        wmlp = dialog.getWindow().getAttributes();
                        wmlp.gravity = Gravity.BOTTOM;
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        ads.refreshAd(vv.getContext(), "dialog", templateView, getString(R.string.smlladg));
                    } catch (Exception e) {
                        Log.d("TAG", "onClick:   " + e.getMessage());
                    }
                break;
            case R.id.goToInfo:

                if (info.getVisibility() == View.GONE) {
                    info.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.row_up);
                } else {
                    info.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.row_down);
                }
        }
    }

    @SuppressLint("SetTextI18n")
    private void getBackData() {
        disposable.add(repository.getInfoData(0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(profileInfo -> {
                    Log.d(TAG, "getBackData: ++++++++++++++++");
                    int i = profileInfo.getBodyStatus() - 500;
                    so3rVal.setText(String.format(Locale.ENGLISH, "%s %s %s %s %d %s %s", getString(R.string.sneed), profileInfo.getBodyStatus() + "", getString(R.string.nowww), getString(R.string.sNeedd), i, getString(R.string.sneeeed), getString(R.string.end)));
                    val1.setText(String.format(Locale.ENGLISH, "%d", profileInfo.getAge()));
                    val2.setText(String.format("%s", profileInfo.getWeight()));
                    val3.setText(String.format(Locale.ENGLISH, "%d", profileInfo.getHeight()));
                    val4.setText(String.format(Locale.ENGLISH, "%s", profileInfo.getJobStatus()));
                    val5.setText(String.format(Locale.ENGLISH, "%s", profileInfo.getGender()));
                    jobStatusS = val4.getText().toString();
                    genderr = val5.getText().toString();
                    cardS3orVal.setText(i + "");
                }, throwable -> Log.d(TAG, "error")));
    }

    private int getSo4rat() {
        int height, age;
        float weight;
        try {
            age = Integer.parseInt(arabicToDecimal(val1.getText().toString()));
            height = Integer.parseInt(arabicToDecimal(val3.getText().toString()));
            weight = Float.parseFloat(arabicToDecimal(val2.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(requireActivity(), "تاكد من ادخال جميع القيم", Toast.LENGTH_SHORT).show();
            return 0;
        }

        int bodyStatus = 0;
        int i = 1;
        switch (jobStatusS) {
            case "ليس مرهقا":
                i = 1;
                break;
            case "مرهقا":
                i = 2;
                break;
            case "مرهقا جدا":
                i = 3;
                break;
        }
        colSo3rat colSo3rat = new colSo3rat();
        if (genderr.equals(getString(R.string.male))) {
            bodyStatus = colSo3rat.setBodyStatusForMens(i, weight, height, age);
        } else if (genderr.equals(getString(R.string.female))) {
            bodyStatus = colSo3rat.setBodyStatusForWomans(i, weight, height, age);
        }

        disposable.add(repository.insertInfo(new ProfileInfo(0, age, weight, genderr, height, jobStatusS, bodyStatus))
                .subscribeOn(Schedulers.io())
                .subscribe(() -> Log.d(TAG, "getSo4rat: ++++++")));

        return bodyStatus;
    }

    private static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }

    @Override
    public void onResume() {
        super.onResume();
        ads.refreshAd(requireContext(), "profile", templateView, getString(R.string.adG));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ads.destroyAd();
        disposable.clear();
    }
}