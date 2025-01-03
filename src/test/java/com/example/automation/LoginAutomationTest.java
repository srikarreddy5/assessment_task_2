package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAutomationTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set the ChromeDriver path (update this to match your local setup)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode to avoid UI popping up

        // Initialize the WebDriver
        driver = new ChromeDriver(options);
    }

    @Test
    public void testLogin() {
        driver.get("http://example.com/login");

        // Assuming the login form has fields with id 'username' and 'password'
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("loginButton")).click();

        // Verify login success, e.g., check if redirected to a dashboard page
        String pageTitle = driver.getTitle();
        assertEquals("Dashboard", pageTitle);

        // Add more assertions based on the expected behavior after login
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
