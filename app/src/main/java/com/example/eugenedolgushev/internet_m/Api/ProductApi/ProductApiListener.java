package com.example.eugenedolgushev.internet_m.Api.ProductApi;

import com.example.eugenedolgushev.internet_m.Model.Product;

import java.util.ArrayList;

public interface ProductApiListener {

    void onProductsLoaded(ArrayList<Product> products);
}
