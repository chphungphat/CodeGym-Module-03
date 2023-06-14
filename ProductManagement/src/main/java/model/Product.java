package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Product {
    private int id;
    private String name;
    private float price;
    private String description;
    private String image_url;
}
