package com.example.eugenedolgushev.internet_m;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<ViewHolder>{

    private ArrayList<Category> categories;

    public CategoryAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

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

    public void setList(ArrayList<Category> list) {
        categories = list;
        notifyDataSetChanged();
    }
}
