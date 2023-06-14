package service;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class ProductService {
    private static final ProductService productService = new ProductService();
    private static final ProductDAO productDAO = new ProductDAO();

    private ProductService() {
    }

    public static ProductService getInstance() {
        return productService;
    }

    public List<Product> getProductList() {
        return productDAO.getAll();
    }
}
