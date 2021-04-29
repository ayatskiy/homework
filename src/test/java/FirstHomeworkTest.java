import config.WebsiteConfig;
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

class FirstHomeworkTest {

    private WebsiteConfig cfg = ConfigFactory.create(WebsiteConfig.class);
    private final Logger logger = LogManager.getLogger(FirstHomeworkTest.class);
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
    void CheckOtusTitle(){
        driver.get(cfg.otusUrl());
        logger.info("Сайт c URL={} открыт",cfg.otusUrl());
        Assertions.assertEquals(cfg.otusTitle(), driver.getTitle(),"Заголовок страницы не совпал с ожидаемым!!!");
        logger.info("Тест прошёл, заголовок страницы равен: \"{}\", как и ожидалось", cfg.otusTitle());
    }
}
