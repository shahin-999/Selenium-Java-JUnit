package pages;

import io.qameta.allure.Step;
import manager.ConfigReader;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Navigate to the homepage")
    public void navigateToHome(){
        String baseUrl = ConfigReader.getInstance().getBaseUrl();
        driver.navigate().to(baseUrl);
    }

    @Step("Get page title")
    public String getPageTitle(){
        return driver.getTitle();
    }
}
