package TestCases;

import manager.ConfigReader;
import manager.DriverManager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

@DisplayName("Test class")
public class TestMyTest extends DriverManager {
    WebDriver driver = getDriver();
    HomePage homePage = new HomePage(driver);

    @AfterEach
    @DisplayName("Closing Browser")
    public void tearDown(){
        closeDriver();
    }

    @Test
    @DisplayName("Navigate to the google and print the base url")
    public void firstTest(){
        homePage.navigateToHome();
        String a = homePage.getPageTitle();
        System.out.println(a);

    }

    @Test
    @DisplayName("Navigate to the google")
    public void lastTest() {
        driver.navigate().to(ConfigReader.getInstance().getBaseUrl());
        System.out.println("Hello test");
    }
}
