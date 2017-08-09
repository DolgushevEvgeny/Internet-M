package com.example.eugenedolgushev.internet_m.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eugenedolgushev.internet_m.R;
import com.loopj.android.image.SmartImageView;

public class ProductViewHolder extends RecyclerView.ViewHolder{

    private TextView productNameView;
    private TextView productPriceView;
    private SmartImageView productImageView;
    private LinearLayout productWrapper;

    public ProductViewHolder(View v) {
        super(v);

        productWrapper = (LinearLayout) v.findViewById(R.id.product_wrapper);
        productNameView = (TextView) v.findViewById(R.id.product_name);
        productPriceView = (TextView) v.findViewById(R.id.product_price);
        productImageView = (SmartImageView) v.findViewById(R.id.product_image);
    }

    public void setProductName(final String productName) {
        if (productName == null) {
            productNameView.setText("empty");
        } else {
            productNameView.setText(productName);
        }
    }

    public void setProductPrice(final Integer productPrice) {
        if (productPrice == null) {
            productPriceView.setText("empty");
        } else {
            productPriceView.setText(productPrice.toString());
        }
    }

    public void setProductImage(final String imageUrl) {
        if (imageUrl == null) {
            productImageView.setImageUrl("empty");
        } else {
            productImageView.setImageUrl(imageUrl);
        }

    }
}
