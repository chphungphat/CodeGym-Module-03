package entity;

import lombok.Builder;

@Builder
public class Category {
    private int category_id;
    private String categoryName;

    public Category(int category_id, String categoryName) {
        this.category_id = category_id;
        this.categoryName = categoryName;
    }

    public Category() {}

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category: " + categoryName;
    }
}
