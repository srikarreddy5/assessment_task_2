package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Dynamically setting the ChromeDriver path using environment variable
        String chromeDriverPath = System.getenv("CHROME_DRIVER_PATH");
        if (chromeDriverPath == null || chromeDriverPath.isEmpty()) {
            System.err.println("ChromeDriver path is not set in environment variables.");
            return;
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // ChromeOptions to disable cache and logging
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-logging");
        options.addArguments("--disable-cache");
        options.addArguments("--disk-cache-size=0");
        options.addArguments("--remote-debugging-port=9223");
        options.addArguments("--remote-allow-origins=*");

        // Initialize WebDriver with options
        WebDriver driver = new ChromeDriver(options);

        // Login credentials and URL
        String baseUrl = "https://www.saucedemo.com/";
        String username = "standard_user";
        String password = "secret_sauce";

        try {
            // Navigate to the login page
            driver.get(baseUrl);

            // Locate elements
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            // Perform login
            usernameField.sendKeys(username);
            passwordField.sendKeys(password);

            // Wait for the login button to be clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
            loginButton.click();

            // Validate successful login by checking page title
            String expectedTitle = "Swag Labs";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
