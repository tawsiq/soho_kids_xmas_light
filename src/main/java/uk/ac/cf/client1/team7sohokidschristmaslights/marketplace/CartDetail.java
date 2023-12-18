package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import lombok.Value;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class CartDetail {
    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
