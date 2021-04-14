import config.OtusWebsiteConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstHomework {

    private OtusWebsiteConfig otusWebsite = ConfigFactory.create(OtusWebsiteConfig.class);
    private Logger logger = LogManager.getLogger(FirstHomework.class);
    private static WebDriver driver;

    @Before
    public void StartUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер готов к работе");
    }

    @After
    public void End(){
        if (driver!=null)
            driver.quit();
    }

    @Test
    public void CheckOtusWebsiteTitle(){
        driver.get(otusWebsite.url());
        logger.info("Сайт c URL=" + otusWebsite.url()+ " открыт");
        Assert.assertEquals("Заголовок страницы не совпал с ожидаемым!", otusWebsite.title(), driver.getTitle());
        logger.info("Тест прошёл, заголовок страницы равен: \"" + otusWebsite.title() + "\",как и ожидалось");
    }
}
