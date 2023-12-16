package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String filename;
    private String filepath;

    // Getters and setters
    // ...
}
