package manager;

import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverManager {
    static WebDriver driver = null;
    final DriverType driverType = ConfigReader.getInstance().getDriverType();

    public WebDriver getDriver(){
        driver = createDriver();
        return driver;
    }

    private WebDriver createDriver(){
        boolean isHeadless = ConfigReader.getInstance().isHeadless();

        switch (driverType){
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless){
                        chromeOptions.addArguments("--headless");
                    }
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--disable-web-security");
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    chromeOptions.addArguments("--disable-cache");

                    driver = new ChromeDriver(chromeOptions);

                    break;

            case EDGE:
                driver = new EdgeDriver();
                break;

            case FIREFOX:
                driver = new FirefoxDriver();
                break;

            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                return null;
            }

        if (ConfigReader.getInstance().isFullScreen()){
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInstance().implicitWait()));
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
    }
}


/*
Certainly! ChromeOptions in Selenium allows you to configure various settings and behaviors of the Chrome browser. Here are some important addArguments values you might find useful:

--start-maximized: Maximizes the Chrome window when it opens.
--headless: Runs Chrome in headless mode, without opening a visible browser window.
--disable-infobars: Disables the "Chrome is being controlled by automated test software" info bar.
--disable-popup-blocking: Disables popup blocking.
--disable-notifications: Disables browser notifications.
--disable-gpu: Disables GPU hardware acceleration.
--disable-dev-shm-usage: Disables the use of /dev/shm in shared memory.
--no-sandbox: Disables the sandbox mode.
--disable-extensions: Disables extensions.
--disable-web-security: Disables web security, allowing you to bypass CORS (Cross-Origin Resource Sharing) restrictions.
--ignore-certificate-errors: Ignores SSL certificate errors.
--disable-popup-blocking: Disables popup blocking.
--proxy-server=<proxy_server_address>: Configures a proxy server.
--user-agent=<user_agent_string>: Sets the user agent string.
--lang=<language_code>: Sets the browser language.
--window-size=<width>,<height>: Sets the initial window size.
--disable-extensions: Disables Chrome extensions.
--disable-cache: Disables the browser cache.
* */