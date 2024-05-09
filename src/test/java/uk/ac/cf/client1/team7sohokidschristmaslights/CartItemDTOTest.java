package uk.ac.cf.client1.team7sohokidschristmaslights;

import org.junit.jupiter.api.Test;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.CartItemDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartItemDTOTest {
    @Test
    public void testCartItemDTO() {
        // Create an instance of CartItemDTO using the setter methods or directly setting fields
        CartItemDTO cartItem = new CartItemDTO();
        cartItem.setProductId(1L);
        cartItem.setName("Test Product");
        cartItem.setPrice(10.0);
        cartItem.setQuantity(2);

        // Validate each field using assertions
        assertEquals(1L, cartItem.getProductId());
        assertEquals("Test Product", cartItem.getName());
        assertEquals(10.0, cartItem.getPrice());
        assertEquals(2, cartItem.getQuantity());
    }
}

