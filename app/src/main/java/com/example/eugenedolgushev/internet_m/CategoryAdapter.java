package com.example.eugenedolgushev.internet_m;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<ViewHolder>{

    private ArrayList<Category> categories;
    private CustomOnItemClickListener listener;

    public CategoryAdapter(ArrayList<Category> categories, CustomOnItemClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setCategoryName(this.categories.get(position).getName());
        holder.setCategoryImage(this.categories.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }

    public Category getItem(int position) {
        if (position >= 0 && position <= this.categories.size() - 1) {
            return this.categories.get(position);
        }
        return null;
    }

    public void setList(ArrayList<Category> list) {
        categories = list;
        notifyDataSetChanged();
    }
}
