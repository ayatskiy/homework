import config.OtusWebsiteConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;

public class FirstHomework {

    private OtusWebsiteConfig otusWebsite = ConfigFactory.create(OtusWebsiteConfig.class);
    private Logger logger = LogManager.getLogger(FirstHomework.class);
    private static WebDriver driver;

    @BeforeEach
    public void StartUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер готов к работе");
    }

    @AfterEach
    public void End(){
        if (driver!=null)
            driver.quit();
    }

    @Test
    public void CheckOtusWebsiteTitle(){
        driver.get(otusWebsite.url());
        logger.info("Сайт c URL={} открыт",otusWebsite.url());
        Assertions.assertEquals(otusWebsite.title(), driver.getTitle(),"Заголовок страницы не совпал с ожидаемым!!!");
        logger.info("Тест прошёл, заголовок страницы равен: \"{}\", как и ожидалось", otusWebsite.title());
    }
}
