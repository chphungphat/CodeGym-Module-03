package entity;

import lombok.Builder;

@Builder
public class Product {
    private int product_id;
    private String productName;
    private String description;
    private long price;
    private Currency_VND stringPrice;

    public Product(int product_id, String productName, String description, long price) {
        this.product_id = product_id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        stringPrice = new Currency_VND(price);
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
        this.stringPrice.setValue(price);
    }

    public Currency_VND getStringPrice() {
        return stringPrice;
    }

    public void setStringPrice(Currency_VND stringPrice) {
        this.stringPrice = stringPrice;
    }

    @Override
    public String toString() {
        return product_id + " " + productName +"\n"
                + description + "\n"
                + "Price: " + stringPrice.toString();
    }
}
