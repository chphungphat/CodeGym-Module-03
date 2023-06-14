package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
public class CartProductDTO {
    private int cart_id;
    private int product_id;
    private int quantity;
    private String productName;
    private float price;

    public static List<CartProductDTO> getList(List<ShoppingCart> cartList, List<Product> productList) {
        List<CartProductDTO> list = new ArrayList<>();
        for (Product element : productList) {
            for (ShoppingCart cart : cartList) {
                if (element.getId() == cart.getProduct_id()) {
                    CartProductDTO newObject = CartProductDTO.builder()
                            .cart_id(cart.getId())
                            .product_id(element.getId())
                            .quantity(cart.getQuantity())
                            .productName(element.getName())
                            .price(element.getPrice() * cart.getQuantity())
                            .build();
                    list.add(newObject);
                }
            }
        }
        return list;
    }
}
