package service;

import entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static final CategoryService categoryService =  new CategoryService();

    private CategoryService() {
        categoryList = new ArrayList<>();
    }

    public static CategoryService getInstance() {
        return categoryService;
    }

    private List<Category> categoryList;
    private Category currentCategory;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }
}
