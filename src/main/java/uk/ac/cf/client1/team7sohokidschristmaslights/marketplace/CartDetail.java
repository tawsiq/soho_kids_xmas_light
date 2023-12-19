//package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.entity;



//import lombok.Value;
//import lombok.AllArgsConstructor;
//import java.math.BigDecimal;

//@Value
//@AllArgsConstructor
//public class CartDetail {
  //  private Long id;
  //  private Long productId;
   // private String productName;
   // private Integer quantity;
  //  private BigDecimal price;
//}
//package uk.ac.cf.client1.team7sohokidschristmaslights.marketplace;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Column;
import java.math.BigDecimal;

//@Entity
//public class CartDetail {
 //   @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
 //   private Long id;

 //   @Column
 //   private Long productId;

 //   @Column(length = 255)
  //  private String productName;

  //  @Column
 //   private Integer quantity;

 //   @Column
  //  private BigDecimal price;

    // JPA requires a no-arg constructor
  //  protected CartDetail() {
 //   }

    // All-args constructor
 //   public CartDetail(Long id, Long productId, String productName, Integer quantity, BigDecimal price) {
  //      this.id = id;
  //      this.productId = productId;
  //      this.productName = productName;
  //      this.quantity = quantity;
  //      this.price = price;
 //   }

    // Getters (Setters are optional based on whether you want the class to be immutable)
  //  public Long getId() {
  //      return id;
  //  }

  //  public Long getProductId() {
  //      return productId;
   // }

  //  public String getProductName() {
  //      return productName;
  //  }

  //  public Integer getQuantity() {
  //      return quantity;
 //   }

 //   public BigDecimal getPrice() {
  //      return price;
  //  }

    // Optionally include setters, equals, hashCode, and toString methods
//}
