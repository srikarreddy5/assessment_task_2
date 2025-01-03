package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAutomationTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set the ChromeDriver path (Jenkins will use the environment variable)
        String chromeDriverPath = System.getenv("CHROME_DRIVER_PATH");
        if (chromeDriverPath != null) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        } else {
            // In case the environment variable isn't set, you can add a fallback
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode

        // Initialize the WebDriver
        driver = new ChromeDriver(options);
    }

    @Test
    public void testLogin() {
        driver.get("https://www.selenium.dev/");

        // Verify that the page has loaded correctly
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Selenium"));

        // Further actions can be added based on your test cases
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
