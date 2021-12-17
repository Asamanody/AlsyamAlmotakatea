package com.el3asas.regym.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.el3asas.regym.R;
import com.el3asas.regym.helpers.So3rOPJ;

import java.util.ArrayList;

public class So3ratAdapter extends RecyclerView.Adapter<So3ratAdapter.So3ratViewHolder> {
    private final ArrayList<So3rOPJ> so3rOPJS;
    private int h = 0;

    public So3ratAdapter(java.util.ArrayList<So3rOPJ> so3rOPJS) {
        this.so3rOPJS = so3rOPJS;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public So3ratViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_so3rat, null, false);
        if (h == 0)
            h = parent.getWidth();
        return new So3ratViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull So3ratAdapter.So3ratViewHolder holder, int position) {
        So3rOPJ f = so3rOPJS.get(position);
        holder.type.setText(f.getType());
        holder.amount.setText(f.getAmount());
        holder.so3r.setText(f.getSo3r());
    }

    @Override
    public int getItemCount() {
        return so3rOPJS.size();
    }

    static class So3ratViewHolder extends RecyclerView.ViewHolder {
        TextView type, amount, so3r;

        So3ratViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            amount = itemView.findViewById(R.id.amount);
            so3r = itemView.findViewById(R.id.so3r);
        }
    }
}
