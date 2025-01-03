import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAutomationTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // Set up WebDriverManager to manage browser drivers
        WebDriverManager.chromedriver().setup();

        // Initialize ChromeDriver (you can replace this with any other browser)
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() {
        // Open a website (you can replace this with any URL you need)
        driver.get("http://www.example.com");

        // Example assertion to verify if the page title contains "Example"
        assertTrue(driver.getTitle().contains("Example"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Close the browser after the test
        }
    }
}
