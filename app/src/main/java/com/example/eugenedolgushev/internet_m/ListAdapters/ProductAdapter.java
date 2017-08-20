package com.example.eugenedolgushev.internet_m.ListAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eugenedolgushev.internet_m.CustomOnItemClickListener;
import com.example.eugenedolgushev.internet_m.Model.Product;
import com.example.eugenedolgushev.internet_m.R;
import com.example.eugenedolgushev.internet_m.ViewHolders.ProductViewHolder;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private ArrayList<Product> products;
    private CustomOnItemClickListener listener;

    public ProductAdapter(ArrayList<Product> products, CustomOnItemClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        final ProductViewHolder mViewHolder = new ProductViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.setProductName(this.products.get(position).getTitle());
        holder.setProductPrice(this.products.get(position).getPrice());
        holder.setProductImage(this.products.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

    public Product getItem(int position) {
        if (position >= 0 && position <= this.products.size() - 1) {
            return this.products.get(position);
        }
        return null;
    }

    public void setList(ArrayList<Product> list) {
        products.addAll(list);
        notifyDataSetChanged();
    }
}
