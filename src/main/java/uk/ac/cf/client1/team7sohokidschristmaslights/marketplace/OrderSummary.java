package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderSummary {
    private String customerName;  // Consistent with 'customerName' in Checkout
    private List<OrderItem> items;
    private Float totalPrice;     // Similar to 'totalPrice' in Checkout

    @Data
    @AllArgsConstructor
    public static class OrderItem {
        private Long productId;    // Mirroring 'product_id' in Product
        private String productName;// Mirroring 'product_name' in Product
        private Float price;       // Same as in Product
        private Integer quantity;  // Additional field, specific to OrderSummary
    }

    // Additional fields and methods as required
}
