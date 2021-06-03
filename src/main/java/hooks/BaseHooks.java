package hooks;

import config.WebsiteConfig;
import enums.Browsers;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import webdriver.WebDriverFactory;

import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseHooks {
    protected static WebDriver driver;

    private static final Logger logger = LogManager.getLogger(BaseHooks.class);
    private static final String DEFINING_BROWSER=System.getProperty("browser");

    @BeforeAll
    public static void setup() {
        var props = new Properties();
        if (DEFINING_BROWSER != null) {
            props.setProperty("browser", DEFINING_BROWSER);
        }
        WebsiteConfig cfg = ConfigFactory.create(WebsiteConfig.class, props);

        logger.info("Инициализация драйвера");
        if (cfg.browser() != null && browsersContain(cfg.browser().toUpperCase(Locale.ROOT))) {
            driver = WebDriverFactory.createDriver(Browsers.valueOf(cfg.browser().toUpperCase(Locale.ROOT)));
        } else {
            driver = WebDriverFactory.createDriver(Browsers.CHROME);
        }
        logger.info("Запущен webdriver {}", driver);
        if (driver != null) {
            driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
    }

    @AfterAll
    public static void teardown() {
        logger.info("Закрытие браузера");
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterEach
    public void cleanUp() {
        logger.info("Очистка печенек после теста");
        driver.manage().deleteAllCookies();
    }

    private static boolean browsersContain(String browserName) {
        for (Browsers c : Browsers.values()) {
            if (c.name().equals(browserName)) {
                return true;
            }
        }
        return false;
    }
}