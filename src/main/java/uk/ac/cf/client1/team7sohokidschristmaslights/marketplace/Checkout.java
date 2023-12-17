package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Checkout {
    private String checkoutId;
    private String customerName;
    private String customerEmail;
    private String deliveryAddress;
    private Float totalPrice;
    private String submissionTime;
}
