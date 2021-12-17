package com.el3asas.regym.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.el3asas.regym.R;
import com.el3asas.regym.ui.ShowItem;
import com.el3asas.regym.helpers.image;
import com.el3asas.regym.helpers.primaryOPJ;

import java.util.ArrayList;

public class primaryAdapter extends RecyclerView.Adapter<primaryAdapter.So3ratViewHolder> {
    private final ArrayList<primaryOPJ> primaryOpj;
    private int h = 0;
    private final Context context;

    public primaryAdapter(Context context, java.util.ArrayList<primaryOPJ> primaryOpj) {
        this.primaryOpj = primaryOpj;
        this.context = context;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public So3ratViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.primary_recycle_item, null, false);
        if (h == 0)
            h = parent.getWidth();
        return new So3ratViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final primaryAdapter.So3ratViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        primaryOPJ f = primaryOpj.get(position);//-nAds);
        holder.title.setText(f.getTitle());
        holder.img.setImageBitmap(image.decodeSampledBitmapFromResource(context.getResources(), f.getImgRs(), 100, 100));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowItem.class);
            Pair pair = new Pair<View, String>(holder.cardView, "titleT");
            ActivityOptions option = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pair);
            intent.putExtra("title", primaryOpj.get(position).getTitle());
            intent.putExtra("img", primaryOpj.get(position).getImgRs());
            context.startActivity(intent, option.toBundle());
        });
    }

    @Override
    public int getItemCount() {
        return primaryOpj.size();
    }

    static class So3ratViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        CardView cardView;

        So3ratViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.c);
        }
    }
}
