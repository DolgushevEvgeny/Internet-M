package com.example.eugenedolgushev.internet_m;

public class Category {

    private Integer categoryId;
    private String title;
    private String imageUrl;
    private Integer subCategories;
    private String name;
    private String description;

    public Category() {}

    public Category(final Integer categoryId, final String title, final String imageUrl,
        final Integer subCategories, final String name, final String description) {
        this.categoryId = categoryId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.subCategories = subCategories;
        this.name = name;
        this.description = description;
    }

    public void setCategoryId(final Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() { return this.imageUrl; }

    public void setSubCategories(final Integer subCategories) {
        this.subCategories = subCategories;
    }

    public Integer getSubCategories() {
        return this.subCategories;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
