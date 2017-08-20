package com.example.eugenedolgushev.internet_m.Api.CategoryApi;

import com.example.eugenedolgushev.internet_m.Model.Category;

import java.util.ArrayList;

public interface CategoryApiListener {

    void onCategoriesLoaded(ArrayList<Category> categories);
}
