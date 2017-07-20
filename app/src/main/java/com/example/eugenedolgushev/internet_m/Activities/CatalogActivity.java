package com.example.eugenedolgushev.internet_m.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.eugenedolgushev.internet_m.AsyncTask;
import com.example.eugenedolgushev.internet_m.Category;
import com.example.eugenedolgushev.internet_m.CategoryAdapter;
import com.example.eugenedolgushev.internet_m.OnAsyncTaskCompleted;
import com.example.eugenedolgushev.internet_m.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity implements OnAsyncTaskCompleted {

    private static final String URL = "http://onlinestore.whitetigersoft.ru/api/common/category/list";
    private RecyclerView categoriesRV = null;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        categoriesRV = (RecyclerView) findViewById(R.id.catalogRecyclerView);
        categoriesRV.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter((ArrayList) categories);
        categoriesRV.setAdapter(categoryAdapter);

        AsyncTask getCategories = new AsyncTask(this, this);
        getCategories.executeAsyncTask(URL, "yx-1PU73oUj6gfk0hNyrNUwhWnmBRld7-SfKAU7Kg6Fpp43anR261KDiQ-MY4P2SRwH_cd4Py1OCY5jpPnY_Viyzja-s18njTLc0E7XcZFwwvi32zX-B91Sdwq1KeZ7m");
    }

    @Override
    public void taskCompleted(JSONObject result) {
        JSONArray list;
        try {
            if (result.has("meta")) {
                JSONObject meta = result.getJSONObject("meta");
                if (meta.has("success")) {
                    boolean success = meta.getBoolean("success");
                    if (success) {
                        if (result.has("data")) {
                            JSONObject data = result.getJSONObject("data");
                            if (data.has("categories")) {
                                list = data.getJSONArray("categories");
                                for (int i = 0; i < list.length(); ++i) {
                                    Category category = new Category();
                                    JSONObject recordJsonObj = list.getJSONObject(i);
                                    if (recordJsonObj.has("categoryId")) {
                                        category.setCategoryId(recordJsonObj.getInt("categoryId"));
                                    }
                                    if (recordJsonObj.has("title")) {
                                        category.setTitle(recordJsonObj.getString("title"));
                                    }
                                    if (recordJsonObj.has("imageUrl")) {
                                        category.setImageUrl(recordJsonObj.getString("imageUrl"));
                                    }
                                    if (recordJsonObj.has("hasSubcategories")) {
                                        category.setSubCategories(recordJsonObj.getInt("hasSubcategories"));
                                    }
                                    if (recordJsonObj.has("fullName")) {
                                        category.setName(recordJsonObj.getString("fullName"));
                                    }
                                    if (recordJsonObj.has("categoryDescription")) {
                                        category.setDescription(recordJsonObj.getString("categoryDescription"));
                                    }

                                    categories.add(category);
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        categoryAdapter.setList((ArrayList) categories);
        categoriesRV.setAdapter(categoryAdapter);
        //categories = gson.fromJson(result, Category.class);
        Log.i("Categories", "onSuccess: " + result);
    }
}
