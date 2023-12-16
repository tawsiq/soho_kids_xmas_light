package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String filename;
    private String filepath;

    // Getters and setters
}
