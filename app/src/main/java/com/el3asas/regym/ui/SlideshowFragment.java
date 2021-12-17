package com.el3asas.regym.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.el3asas.regym.R;
import com.el3asas.regym.helpers.Ads;
import com.el3asas.regym.ui.adapter.primaryAdapter;
import com.el3asas.regym.helpers.primaryOPJ;
import com.google.android.ads.nativetemplates.TemplateView;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {
    private TemplateView templateView;
    private  RecyclerView recyclerView;
    private Ads ads;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        templateView=root.findViewById(R.id.my_template);

        ads=new Ads();
        recyclerView = root.findViewById(R.id.parentRecycleView);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<primaryOPJ> primaryOPJS=new ArrayList<>();
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للاسماك",R.drawable.fish));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للحوم",R.drawable.l7m));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للحوم المصنعه",R.drawable.l7m2));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للمشروبات والعصائر",R.drawable.aser));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للالبان ومنتجاتها",R.drawable.lban2));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه لحلويات",R.drawable.shho));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للمكسرات",R.drawable.mokasa));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للبقوليات والنشويات",R.drawable.paqoliat));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للدهون",R.drawable.dhon));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للخضراوات",R.drawable.khdra));
        primaryOPJS.add(new primaryOPJ("السعرات الحراريه للفواكه",R.drawable.fruts));

        primaryAdapter so3ratAdapter=new primaryAdapter(requireActivity(),primaryOPJS);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(requireActivity());
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(so3ratAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ads.refreshAd(requireContext(),"slideShow",templateView,getString(R.string.smlladg));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ads.destroyAd();
    }
}