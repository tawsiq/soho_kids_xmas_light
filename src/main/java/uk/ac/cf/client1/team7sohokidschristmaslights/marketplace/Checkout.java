package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data// annotation from Lombok generates getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor // generating a constructor with one argument for each field in the class


public class Checkout {
    private String checkoutId; // Unique identifier for the checkout process
    private String customerName; // Customer's full name
    private String customerEmail; // Customer's email address
    private String deliveryAddress; // Delivery address for the order
    private Float totalPrice; // Total price of the order
    private String note; // Note of the order
    private String submissionTime; // Time of form submission
}
