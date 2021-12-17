package com.el3asas.regym.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.el3asas.regym.R;
import com.el3asas.regym.helpers.Ads;
import com.google.android.ads.nativetemplates.TemplateView;

public class PlansFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    private TextView plan1_title;
    private TextView plan2_title;
    private TextView plan3_title;
    private TextView plan1_info1;
    private TextView plan1_info2;
    private TextView plan2_info1;
    private TextView plan2_info2;
    private TextView plan3_info1;
    private TextView plan3_info2;
    private String vall;
    private TextView plan4_title;
    private TextView plan4_info1;
    private TextView plan4_info2;
    private TemplateView templateView;
    private Ads ads;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.plans_frag, container, false);
        View plan1,plan2,plan3;
        CardView plan4;
        SharedPreferences pref = null;
        templateView = root.findViewById(R.id.my_template);

        ads=new Ads();

        try {
            pref = requireActivity().getSharedPreferences("customCounter", 0);
        }catch (NullPointerException e){
            Toast.makeText(requireActivity(),"Error",Toast.LENGTH_SHORT).show();
        }
        assert pref != null;
        int i= pref.getInt("cutomHours",6);

        plan1=root.findViewById(R.id.plan_1);
        plan1_title=plan1.findViewById(R.id.plan_title);
        plan1_info1=plan1.findViewById(R.id.plan_info1);
        plan1_info2=plan1.findViewById(R.id.plan_info2);

        plan2=root.findViewById(R.id.plan_2);
        plan2_title=plan2.findViewById(R.id.plan_title);
        plan2_info1=plan2.findViewById(R.id.plan_info1);
        plan2_info2=plan2.findViewById(R.id.plan_info2);

        plan3=root.findViewById(R.id.plan_3);
        plan3_title=plan3.findViewById(R.id.plan_title);
        plan3_info1=plan3.findViewById(R.id.plan_info1);
        plan3_info2=plan3.findViewById(R.id.plan_info2);

        plan4=root.findViewById(R.id.plan_4);
        ImageButton imgBtn=root.findViewById(R.id.imgBtn);
        imgBtn.setOnClickListener(v -> showCustomDialog());

        plan4_title = plan4.findViewById(R.id.plan_title);
        plan4_info1 = plan4.findViewById(R.id.plan_info1);
        plan4_info2 = plan4.findViewById(R.id.plan_info2);

        plan4_title.setText(i+"/"+(24-i));
        plan4_info1.setText("الصيام لمده "+i+"ساعه");
        plan4_info2.setText("فتره تناول الطعام"+ (24-i) +"ساعات");


        plan1.setOnClickListener(this);
        plan2.setOnClickListener(this);
        plan3.setOnClickListener(this);
        plan4.setOnClickListener(this);

        plan4.setOnLongClickListener(this);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        setText();
    }

    private void setText(){
        plan1_title.setText(getString(R.string._16_8));
        plan1_info1.setText(R.string._16_8_info1);
        plan1_info2.setText(R.string._16_8_info2);

        plan2_title.setText(getString(R.string._18_6));
        plan2_info1.setText(R.string._18_6_info1);
        plan2_info2.setText(R.string._18_6_info2);

        plan3_title.setText(getString(R.string._20_4));
        plan3_info1.setText(R.string._20_4_info1);
        plan3_info2.setText(R.string._20_4_info2);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(requireActivity(), PlanActivity.class);
        switch (view.getId()){
            case R.id.plan_1:
                intent.putExtra("time",16);
                startActivity(intent);
                break;
            case R.id.plan_2:
                intent.putExtra("time",18);
                startActivity(intent);
                break;
            case R.id.plan_3:
                intent.putExtra("time",20);
                startActivity(intent);
                break;
            case R.id.plan_4:
                SharedPreferences pref=null;
                try {
                    pref = requireActivity().getSharedPreferences("customCounter", 0);
                }catch (NullPointerException e){
                    Toast.makeText(requireActivity(),"Error",Toast.LENGTH_SHORT).show();
                }
                assert pref != null;
                int i=pref.getInt("cutomHours",6);

                intent.putExtra("time",i);
                startActivity(intent);
                break;
        }
    }
    @SuppressLint("SetTextI18n")
    private void showCustomDialog(){
        SharedPreferences pref=null;
        try {
            pref = requireActivity().getSharedPreferences("customCounter", 0);
        }catch (NullPointerException e){
            Toast.makeText(requireActivity(),"Error",Toast.LENGTH_SHORT).show();
        }
        assert pref != null;
        final SharedPreferences.Editor editor=pref.edit();
        AlertDialog.Builder builder=new AlertDialog.Builder(requireActivity());
        @SuppressLint("InflateParams")
        View v= getLayoutInflater().inflate(R.layout.dialog_edit_text,null,false);
        TextView title=v.findViewById(R.id.DialogTitle);
        title.setText(getString(R.string.entercustomh));

        com.shawnlin.numberpicker.NumberPicker numberPicker = v.findViewById(R.id.numberPicker);

        numberPicker.setDividerColor(ContextCompat.getColor(requireActivity(), R.color.actv));
        numberPicker.setDividerColorResource(R.color.actv);

        numberPicker.setTextColor(ContextCompat.getColor(requireActivity(), R.color.inActv));
        numberPicker.setTextColorResource(R.color.inActv);

        numberPicker.setSelectedTextColor(ContextCompat.getColor(requireActivity(), R.color.actv));
        numberPicker.setSelectedTextColorResource(R.color.actv);

        Button go = v.findViewById(R.id.go);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(24);
        numberPicker.setValue(pref.getInt("cutomHours",6));
        vall="6";
        numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> vall = newVal + "");

        builder.setView(v);
        final AlertDialog dialog=builder.create();
        go.setOnClickListener(v1 -> {
            int i=Integer.parseInt(vall);
            plan4_title.setText(i+"/"+(24-i));
            plan4_info1.setText("الصيام لمده "+i+"ساعه");
            plan4_info2.setText("فتره تناول الطعام"+ (24-i) +"ساعات");
            editor.putInt("cutomHours",i);
            editor.apply();
            Intent intent=new Intent(requireActivity(),PlanActivity.class);
            intent.putExtra("time",i);
            startActivity(intent);
            dialog.dismiss();

        });
        WindowManager.LayoutParams wmlp;
        wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.BOTTOM;
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public boolean onLongClick(View v) {
        showCustomDialog();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        ads.refreshAd(requireContext(),"plans_frag",templateView,getString(R.string.adG));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ads.destroyAd();
    }
}