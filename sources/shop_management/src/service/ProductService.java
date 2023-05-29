package service;

import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static final ProductService productService  = new ProductService();

    private ProductService() {
        productList = new ArrayList<>();
    }

    public static ProductService getInstance() {
        return productService;
    }

    private List<Product> productList;
    private Product currentProduct;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }
}
