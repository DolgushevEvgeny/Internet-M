package com.example.eugenedolgushev.internet_m.Model;

public class Category {

    private Integer categoryId;
    private String title;
    private String imageUrl;
    private Integer hasSubcategories;
    private String fullName;
    private String categoryDescription;

    public Category() {}

    public Category(final Integer categoryId, final String title, final String imageUrl,
        final Integer subCategories, final String name, final String description) {
        this.categoryId = categoryId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.hasSubcategories = subCategories;
        this.fullName = name;
        this.categoryDescription = description;
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
        this.hasSubcategories = subCategories;
    }

    public Integer getSubCategories() {
        return this.hasSubcategories;
    }

    public void setName(final String name) {
        this.fullName = name;
    }

    public String getName() {
        return this.fullName;
    }

    public void setDescription(final String description) {
        this.categoryDescription = description;
    }

    public String getDescription() {
        return this.categoryDescription;
    }
}
