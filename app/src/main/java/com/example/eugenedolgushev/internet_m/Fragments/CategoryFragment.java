package com.example.eugenedolgushev.internet_m.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.eugenedolgushev.internet_m.Api.CategoryApi.CategoryApiListener;
import com.example.eugenedolgushev.internet_m.Api.CategoryApi.impl.CategoryApiImpl;
import com.example.eugenedolgushev.internet_m.CustomOnItemClickListener;
import com.example.eugenedolgushev.internet_m.EndlessScroll;
import com.example.eugenedolgushev.internet_m.ListAdapters.CategoryAdapter;
import com.example.eugenedolgushev.internet_m.Model.Category;
import com.example.eugenedolgushev.internet_m.R;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    private RecyclerView categoriesRV = null;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categories = new ArrayList<Category>();
    private Context context;
    private TransferData transferData;
    private CategoryApiImpl categoryApi;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private CategoryFragment that;

    public CategoryFragment() {}

    public void setContext(final Context context) {
        this.context = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        transferData = (TransferData) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        that = this;
        getActivity().setTitle("Каталог");

        categoryApi = new CategoryApiImpl(context);
        layoutManager = new LinearLayoutManager(context);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);

        categoriesRV = (RecyclerView) getActivity().findViewById(R.id.catalogRecyclerView);
        categoriesRV.addOnScrollListener(new EndlessScroll(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                RequestParams params  = new RequestParams();
                params.put("offset", categoryAdapter.getItemCount());
                getCategories(params);
            }
        });
        categoriesRV.setLayoutManager(new LinearLayoutManager(context));
        categoryAdapter = new CategoryAdapter(categories, new CustomOnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CategoryAdapter adapter = (CategoryAdapter) categoriesRV.getAdapter();
                Category category = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", category.getCategoryId());
                bundle.putString("categoryTitle", category.getTitle());
                transferData.sendData(bundle);
            }
        });
        categoriesRV.setAdapter(categoryAdapter);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        RequestParams params  = new RequestParams();
        params.put("offset", categoryAdapter.getItemCount());
        getCategories(params);
    }

    private void getCategories(final RequestParams params) {
        categoryApi.getCategories(params, new CategoryApiListener() {
            @Override
            public void onCategoriesLoaded(ArrayList<Category> categories) {
                that.onCategoriesLoaded(categories);
            }
        });
    }

    public void onCategoriesLoaded(ArrayList<Category> categories) {
        categoryAdapter.setList(categories);
        categoriesRV.swapAdapter(categoryAdapter, false);
        progressBar.setVisibility(ProgressBar.GONE);
    }
}
