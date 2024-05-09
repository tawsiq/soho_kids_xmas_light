package uk.ac.cf.client1.team7sohokidschristmaslights;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.MarketService;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.MarketplaceController;
import uk.ac.cf.client1.team7sohokidschristmaslights.marketplace.Product;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(MarketplaceController.class)
public class LightweightMockMVCTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarketService marketService;

    // Simple Unit tests using a mockMVC - no change here from container testing as no access to database
    @Test
    public void getMarketplaceTest() throws Exception {
        this.mockMvc.perform(get("/home/marketplace")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("PRODUCT LIST")));
    }

    // Component test using a mockMVC but mocking the service response
    // This only tests the controller and the view layers
    @Test
    public void getProductListTest() throws Exception {
        Product product = new Product(1L, "mockProduct.jpg", "/path/to/mockProduct.jpg", "Mock Product", 30.0f);

        // This will return what the marketService would have returned - a List of Products
        given(this.marketService.getProductList()).willReturn(Arrays.asList(product));

        this.mockMvc.perform(get("/home/marketplace")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Mock Product")));
    }
}
