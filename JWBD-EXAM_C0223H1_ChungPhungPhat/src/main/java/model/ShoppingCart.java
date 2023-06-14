package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ShoppingCart {
    private int id;
    private int product_id;
    private int quantity;
}
