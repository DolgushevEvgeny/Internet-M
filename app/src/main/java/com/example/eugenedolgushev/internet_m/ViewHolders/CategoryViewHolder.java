package com.example.eugenedolgushev.internet_m.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eugenedolgushev.internet_m.R;
import com.loopj.android.image.SmartImageView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView categoryNameView;
    private SmartImageView categoryImageView;
    private LinearLayout categoryWrapper;

    public CategoryViewHolder(View v) {
        super(v);

        categoryWrapper = (LinearLayout) v.findViewById(R.id.category_wrapper);
        categoryNameView = (TextView) v.findViewById(R.id.category_name);
        categoryImageView = (SmartImageView) v.findViewById(R.id.category_image);
    }

    public void setCategoryName(final String categoryName) {
        categoryNameView.setText(categoryName);
    }

    public void setCategoryImage(final String imageUrl) {
        categoryImageView.setImageUrl(imageUrl);
    }
}
