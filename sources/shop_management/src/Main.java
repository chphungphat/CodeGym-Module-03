import entity.Address;
import entity.Product;
import entity.User;

public class Main {
    public static void main(String[] args) {
        Product newProduct = Product.builder()
                .product_id(1)
                .productName("Nintendo Switch")
                .price(350000000)
                .build();
        System.out.println(newProduct.toString());
    }
}