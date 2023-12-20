package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private Long product_id;
    private String filename;
    private String filepath;
    private String product_name; // Make sure this field is present
    private int price;
}
