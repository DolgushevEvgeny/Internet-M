package com.example.eugenedolgushev.internet_m.Api.ProductApi.impl;

import android.content.Context;

import com.example.eugenedolgushev.internet_m.Api.BaseApi;
import com.example.eugenedolgushev.internet_m.Api.ProductApi.ProductApiListener;
import com.example.eugenedolgushev.internet_m.AsyncTask.OnAsyncTaskCompleted;
import com.example.eugenedolgushev.internet_m.Model.Product;
import com.example.eugenedolgushev.internet_m.Parsers.ProductParser;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

public class ProductApiImpl extends BaseApi {

    private static final String URL = "common/product/list";
    private Context context;

    public ProductApiImpl(final Context context) {
        super();
        this.context = context;
    }

    public void getProducts(RequestParams params, final ProductApiListener listener) {
        super.get(URL, params, new OnAsyncTaskCompleted() {
            @Override
            public void taskCompleted(Object data) {
                JSONArray productsArray = (JSONArray) data;
                ArrayList<Product> products = ProductParser.parseProducts(productsArray);
                if (products != null) {
                    listener.onProductsLoaded(products);
                }
            }
        });
    }

    public void getProducts(final ProductApiListener listener) {
        this.getProducts(new RequestParams(), listener);
    }
}
