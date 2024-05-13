package TestCases;

import manager.ConfigReader;
import manager.DriverManager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

@DisplayName("Test class")
public class TestMyTest extends DriverManager {
    WebDriver driver = getDriver();

    @AfterEach
    @DisplayName("Closing Browser")
    public void tearDown(){
        System.out.println("Before");
        closeDriver();
    }

    @Test
    @DisplayName("Navigate to the google and print the base url")
    public void firstTest(){
        System.out.println(driver);
        driver.navigate().to(ConfigReader.getInstance().getBaseUrl());
        String a = ConfigReader.getInstance().getBaseUrl();
        System.out.println(a);

    }

    @Test
    @DisplayName("Navigate to the google")
    public void lastTest() {
        driver.navigate().to(ConfigReader.getInstance().getBaseUrl());
        System.out.println("Hello test");
    }
}
