package entity;

import lombok.Builder;

import java.text.DecimalFormat;

@Builder
public class Product {
    private static final DecimalFormat formatter = new DecimalFormat("#,###");

    private int product_id;
    private String productName;
    private String description;
    private long price;
    private int category_id;

    public Product(int product_id, String productName, String description, long price, int category_id) {
        this.product_id = product_id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
    }

    public Product() {}

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStringPrice() {
        return formatter.format(price);
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }



    @Override
    public String toString() {
        return product_id + " " + productName +"\n"
                + "Description: " + description + "\n"
                + "Price: " + getStringPrice() + "\n"
                + "Category: ";
    }
}
