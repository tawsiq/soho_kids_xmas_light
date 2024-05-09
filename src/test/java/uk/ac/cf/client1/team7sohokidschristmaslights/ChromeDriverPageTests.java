//package uk.ac.cf.client1.team7sohokidschristmaslights;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//// @RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ChromeDriverPageTests {
//
//    @Value("${local.server.port}")
//    private int port;
//
//    WebDriver webDriver;
//
//    @BeforeAll
//    static void setupClass() {
//        WebDriverManager.chromedriver().clearDriverCache();
////        WebDriverManager.chromedriver().driverVersion("123.0.6312.106").setup();
//        WebDriverManager.chromedriver().setup();
//    }
//    //Before running chromedriver test, close down chrome in task manager
//    @BeforeEach
//    void setupTest() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-debugging-port=42227");
//        options.addArguments("--headless");
//        webDriver = new ChromeDriver(options);
//    }
//
//    @AfterEach
//    void teardown() {
//        webDriver.quit();
//    }
//
//    @Test
//    public void testMarketplace() {
//        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/home/marketplace");
//        String pageSource = webDriver.getPageSource();
//        assertTrue(pageSource.contains(" Soho Kids Christmas Lights 2023 @Cardiff University\n"));
//
//    }
//
//    @Test
//    public void testCheckout() {
//        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/home/marketplace/checkout");
//        String pageSource = webDriver.getPageSource();
//        assertTrue(pageSource.contains("Checkout"));
//    }
//}
