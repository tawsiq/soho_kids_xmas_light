package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    // Constructors, getters, and setters
}

