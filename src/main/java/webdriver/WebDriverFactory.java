package webdriver;


import enums.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class WebDriverFactory {
    private WebDriverFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static WebDriver createDriver(Browsers type) {
        switch (type) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            case EDGE:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                return null;
        }
    }

    public static WebDriver createDriver(Browsers type, MutableCapabilities wdOptions){
        switch (type){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver( (ChromeOptions) wdOptions);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver((FirefoxOptions) wdOptions);
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver((OperaOptions) wdOptions);
            case EDGE:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver((EdgeOptions) wdOptions);
            default:
                return null;
        }
    }
}