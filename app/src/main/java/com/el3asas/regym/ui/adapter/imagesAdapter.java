package com.el3asas.regym.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.el3asas.regym.R;

public class imagesAdapter extends PagerAdapter {
    private final Context context;

    public imagesAdapter(Context context) {
        this.context = context;
    }

    private final int[] lst_images = {
            R.drawable.slomab,
            R.drawable.weight,
            R.drawable.plan,
            R.drawable.food,
            R.drawable.afterseam,
            R.drawable.last
    };
    private final int[] lst_titles = {
            R.string.app_name,
            R.string.weghtt,
            R.string.seam,
            R.string.so3ratS,
            R.string.afterSyamt,
            R.string.goal
    };
    private final int[] lst_description = {
            R.string.openInfo,
            R.string.weeghhht,
            R.string.seami,
            R.string.so3ratSi,
            R.string.afterSyam,
            R.string.goall
    };

    @Override
    public int getCount() {
        return lst_titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;
        if (inflater != null) {
            view = inflater.inflate(R.layout.one_item_slider, container, false);
        }
        assert view != null;
        TextView info = view.findViewById(R.id.infoTitle);
        ImageView img = view.findViewById(R.id.img);
        TextView description = view.findViewById(R.id.description);
        info.setText(lst_titles[position]);

        img.setBackgroundResource(lst_images[position]);
        description.setText(lst_description[position]);
        container.addView(view);
        return view;
    }
}