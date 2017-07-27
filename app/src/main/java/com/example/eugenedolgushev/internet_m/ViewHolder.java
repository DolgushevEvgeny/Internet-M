package com.example.eugenedolgushev.internet_m;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

class ViewHolder extends RecyclerView.ViewHolder {

    private TextView categoryNameView;
    private SmartImageView categoryImageView;
    private LinearLayout categoryWrapper;

    ViewHolder(View v) {
        super(v);

        categoryWrapper = (LinearLayout) v.findViewById(R.id.category_wrapper);
        categoryNameView = (TextView) v.findViewById(R.id.category_name);
        categoryImageView = (SmartImageView) v.findViewById(R.id.category_image);
    }

    void setCategoryName(final String categoryName) {
        categoryNameView.setText(categoryName);
    }

    void setCategoryImage(final String imageUrl) {
        categoryImageView.setImageUrl(imageUrl);
    }
}
