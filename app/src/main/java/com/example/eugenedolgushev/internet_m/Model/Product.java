package com.example.eugenedolgushev.internet_m.Model;

import java.util.ArrayList;

public class Product {

    private Integer productId;
    private String title;
    private String description;
    private Integer price;
    private Integer rating;
    private String imageUrl;
    private ArrayList<String> images;

    public Product() {}

    public Product(final Integer id, final String title, final String description, final Integer price,
           final Integer rating, final String imageUrl, final ArrayList<String> images) {
        this.productId = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.images = images;
    }

    public void setProductId(final Integer id) {
        this.productId = id;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setRating(final Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImages(final ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getImages() {
        return this.images;
    }
}
