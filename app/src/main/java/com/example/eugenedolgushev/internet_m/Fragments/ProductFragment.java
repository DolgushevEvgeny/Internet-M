package com.example.eugenedolgushev.internet_m.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.eugenedolgushev.internet_m.Api.ProductApi.ProductApiListener;
import com.example.eugenedolgushev.internet_m.Api.ProductApi.impl.ProductApiImpl;
import com.example.eugenedolgushev.internet_m.CustomOnItemClickListener;
import com.example.eugenedolgushev.internet_m.EndlessScroll;
import com.example.eugenedolgushev.internet_m.ListAdapters.ProductAdapter;
import com.example.eugenedolgushev.internet_m.Model.Product;
import com.example.eugenedolgushev.internet_m.R;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    private RecyclerView productsRV = null;
    private ProductAdapter productAdapter;
    private ArrayList<Product> products = new ArrayList<>();
    private Context context;
    private ProductApiImpl productApi;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private ProductFragment that;
    private Integer categoryId;

    public ProductFragment() {}

    public void setContext(final Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        that = this;
        getActivity().setTitle("Продукты");

        productApi = new ProductApiImpl(context);
        layoutManager = new LinearLayoutManager(context);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);

        productsRV = (RecyclerView) getActivity().findViewById(R.id.product_recycler_view);
        productsRV.setLayoutManager(layoutManager);
        productsRV.addOnScrollListener(new EndlessScroll(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                RequestParams params  = new RequestParams();
                params.put("categoryId", categoryId);
                params.put("offset", productAdapter.getItemCount());
                getProducts(params);
            }
        });
        productAdapter = new ProductAdapter(products, new CustomOnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        productsRV.setAdapter(productAdapter);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        RequestParams params  = new RequestParams();
        params.put("offset", productAdapter.getItemCount());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Object obj = bundle.get("categoryTitle");
            if (obj != null) {
                String title = (String) obj;
                getActivity().setTitle(title);
            }
            obj = bundle.get("categoryId");
            if (obj != null) {
                categoryId = (Integer) obj;
                params.put("categoryId", categoryId);
            }
        }

        getProducts(params);
    }

    private void getProducts(final RequestParams params) {
        productApi.getProducts(params, new ProductApiListener() {
            @Override
            public void onProductsLoaded(ArrayList<Product> products) {
                that.onProductsLoaded(products);
            }
        });
    }

    private void onProductsLoaded(ArrayList<Product> products) {
        productAdapter.setList(products);
        productsRV.swapAdapter(productAdapter, false);
        progressBar.setVisibility(ProgressBar.GONE);
    }
}
