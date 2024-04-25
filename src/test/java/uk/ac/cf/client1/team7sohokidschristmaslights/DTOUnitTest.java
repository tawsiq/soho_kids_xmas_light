package uk.ac.cf.client1.team7sohokidschristmaslights.AppTesting;

import org.junit.jupiter.api.Test;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.CartItemDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOUnitTest {

    @Test
    void testGettersAndSetters() {
        // Create an instance of CartItemDTO
        CartItemDTO cartItem = new CartItemDTO();

        // Set values using setters
        cartItem.setProductId(1L);
        cartItem.setName("Product");
        cartItem.setPrice(10.0);
        cartItem.setQuantity(2);

        // Verify values using getters
        assertEquals(1L, cartItem.getProductId());
        assertEquals("Product", cartItem.getName());
        assertEquals(10.0, cartItem.getPrice());
        assertEquals(2, cartItem.getQuantity());
    }
}
