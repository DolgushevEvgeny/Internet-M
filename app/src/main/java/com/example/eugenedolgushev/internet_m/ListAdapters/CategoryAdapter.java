package com.example.eugenedolgushev.internet_m.ListAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eugenedolgushev.internet_m.CustomOnItemClickListener;
import com.example.eugenedolgushev.internet_m.Model.Category;
import com.example.eugenedolgushev.internet_m.R;
import com.example.eugenedolgushev.internet_m.ViewHolders.CategoryViewHolder;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{

    private ArrayList<Category> categories;
    private CustomOnItemClickListener listener;

    public CategoryAdapter(ArrayList<Category> categories, CustomOnItemClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        final CategoryViewHolder mViewHolder = new CategoryViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getLayoutPosition());
            }
        });

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
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
