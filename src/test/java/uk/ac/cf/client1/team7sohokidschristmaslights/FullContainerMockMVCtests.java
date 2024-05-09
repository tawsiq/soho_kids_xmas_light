package uk.ac.cf.client1.team7sohokidschristmaslights;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FullContainerMockMVCtests {

    @Autowired
    private MockMvc mockMvc;

    // Test accessing the marketplace page
    @Test
    public void getMarketplaceTest() throws Exception {
        this.mockMvc.perform(get("/home/marketplace"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(containsString("marketplace/marketplace")));
    }

    // Test accessing the checkout page
    @Test
    public void hostCheckoutTest() throws Exception {
        this.mockMvc.perform(get("/home/marketplace/checkout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("checkout/checkout"));
    }


}
