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

import com.example.eugenedolgushev.internet_m.Api.CategoryApi.OnCategoryParsed;
import com.example.eugenedolgushev.internet_m.Api.CategoryApi.impl.CategoryApiImpl;
import com.example.eugenedolgushev.internet_m.CustomOnItemClickListener;
import com.example.eugenedolgushev.internet_m.ListAdapters.CategoryAdapter;
import com.example.eugenedolgushev.internet_m.Model.Category;
import com.example.eugenedolgushev.internet_m.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements OnCategoryParsed {

    private static final String URL = "http://onlinestore.whitetigersoft.ru/api/common/category/list";
    private RecyclerView categoriesRV = null;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categories = new ArrayList<Category>();
    private static final String testValue = "{\"meta\":{\"success\":true,\"error\":\"\"},\"data\":{\"categories\":[{\"categoryId\":66,\"title\":\"Видеокарты\",\"imageUrl\":\"http://onlinestore.whitetigersoft.ru/uploads/category-icons/e6wJIGyFBUkH2hxc_ObC2RW9Rq-9Whys.jpg\",\"hasSubcategories\":0,\"fullName\":\"Видеокарты\",\"categoryDescription\":null},{\"categoryId\":70,\"title\":\"Оперативная память\",\"imageUrl\":null,\"hasSubcategories\":0,\"fullName\":\"Оперативная память\",\"categoryDescription\":null},{\"categoryId\":71,\"title\":\"Системный блок\",\"imageUrl\":null,\"hasSubcategories\":0,\"fullName\":\"Системный блок\",\"categoryDescription\":null},{\"categoryId\":72,\"title\":\"Мониторы\",\"imageUrl\":null,\"hasSubcategories\":0,\"fullName\":\"Мониторы\",\"categoryDescription\":null}]}}\n";
    private Context context;
    private TransferData transferData;
    private CategoryApiImpl categoryApi;
    private ProgressBar progressBar;

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

        getActivity().setTitle("Каталог");

        categoryApi = new CategoryApiImpl(context, this);

        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);

        categoriesRV = (RecyclerView) getActivity().findViewById(R.id.catalogRecyclerView);
        categoriesRV.setLayoutManager(new LinearLayoutManager(context));
        categoryAdapter = new CategoryAdapter(categories, new CustomOnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CategoryAdapter adapter = (CategoryAdapter) categoriesRV.getAdapter();
                Category category = adapter.getItem(position + 1);
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", category.getCategoryId());
                bundle.putString("categoryTitle", category.getTitle());
                transferData.sendData(bundle);
            }
        });
        categoriesRV.setAdapter(categoryAdapter);
//        processResult();
        progressBar.setVisibility(ProgressBar.VISIBLE);
        categoryApi.getCategories();
    }

    private void processResult() {
        Gson gson = new Gson();
        try {
            JSONObject result = new JSONObject(testValue);
            if (result.has("meta")) {
                JSONObject meta = result.getJSONObject("meta");
                if (meta.has("success")) {
                    boolean success = meta.getBoolean("success");
                    if (success) {
                        if (result.has("data")) {
                            JSONObject data = result.getJSONObject("data");
                            if (data.has("categories")) {
                                JSONArray list = data.getJSONArray("categories");
                                for (int i = 0; i < list.length(); ++i) {
                                    categories.add(gson.fromJson(String.valueOf(list.getJSONObject(i)), Category.class));
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        categoryAdapter.setList(categories);
        categoriesRV.setAdapter(categoryAdapter);
    }

    @Override
    public void setCategories(ArrayList<Category> categories) {
        categoryAdapter.setList(categories);
        categoriesRV.setAdapter(categoryAdapter);
        progressBar.setVisibility(ProgressBar.GONE);
    }
}
