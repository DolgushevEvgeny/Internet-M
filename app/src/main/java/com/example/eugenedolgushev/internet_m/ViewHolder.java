package com.example.eugenedolgushev.internet_m;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import org.json.JSONObject;

public class ViewHolder extends RecyclerView.ViewHolder implements OnAsyncTaskCompleted {

    private TextView categoryNameView;
    private SmartImageView categoryImageView;

    public ViewHolder(View v) {
        super(v);

        categoryNameView = (TextView) v.findViewById(R.id.category_name);
        categoryImageView = (SmartImageView) v.findViewById(R.id.category_image);
    }

    public void setCategoryName(final String categoryName) {
        categoryNameView.setText(categoryName);
    }

    public void setCategoryImage(final String imageUrl) {
        categoryImageView.setImageUrl(imageUrl);
    }

    @Override
    public void taskCompleted(JSONObject result) {

    }
}
