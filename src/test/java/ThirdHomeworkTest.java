import config.WebsiteConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

class ThirdHomeworkTest extends ElementsInteraction {

    private final WebsiteConfig cfg = ConfigFactory.create(WebsiteConfig.class);
    private final Logger logger = LogManager.getLogger(ThirdHomeworkTest.class);
    private static final Long PAGE_TIMEOUT = 20L;
    private static final String FIRST_BRAND = "Xiaomi";
    private static final String SECOND_BRAND = "Samsung";

    @BeforeEach
    public void StartUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер готов к работе");
        driver.manage().timeouts().pageLoadTimeout(PAGE_TIMEOUT,TimeUnit.SECONDS);
        logger.info("Установлено время ожидания загрузки страницы в {} секунд", PAGE_TIMEOUT);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void End(){
        if (driver!=null)
            driver.quit();
    }

    @Test
    void CheckComparisonTwoProducts() {
        // Step 1 - Откройте сайт Яндекс.Маркет :
        driver.get(cfg.marketUrl());
        logger.info("Сайт c URL={} открыт",cfg.marketUrl());
        // Step 2 - перейти во вкладку "Электроника"-> "Смартфоны"
        getClickableElement(By.xpath("//span[text()='Электроника']")).click();
        logger.info("Перешли на вкладку \"Электроника\"");
        getClickableElement(By.xpath("//a[text()='Смартфоны']")).click();
        logger.info("Перешли на вкладку \"Смартфоны\"");
        // Step 3 - Отфильтровать список товаров: Samsung и Xiaomi
        // Открываем список всех производителей
        getClickableElement(By.xpath("//legend[text()='Производитель']/ancestor::fieldset//button[text()='Показать всё']")).click();
        // Ищем и добавляем в фильтры Xiaomi
        getVisibilityElement(By.cssSelector("input[name='Поле поиска']")).clear();
        getVisibilityElement(By.cssSelector("input[name='Поле поиска']")).sendKeys(FIRST_BRAND);
        getVisibilityElement(By.xpath("//span[text()='" + FIRST_BRAND + "']")).click();
        // Ищем и добавляем в фильтры Samsung
        getVisibilityElement(By.cssSelector("input[name='Поле поиска']")).clear();
        getVisibilityElement(By.cssSelector("input[name='Поле поиска']")).sendKeys(SECOND_BRAND);
        getVisibilityElement(By.xpath("//span[text()='" + SECOND_BRAND + "']")).click();
        logger.info("Отфильтровали список товаров по брендам {} и {}", FIRST_BRAND, SECOND_BRAND);
        // Step 4 - Отсортировать список товаров по цене (от меньшей к большей)
        getClickableElement(By.cssSelector("button[data-autotest-id='dprice']")).click();
        // Ждем исчезновения из DOM элемента, перекрывающего кнопки "добавить к сравнению"
        waitInvisibilityElement(By.cssSelector("div[data-zone-name=\"snippetList\"] + div"));
        // Step 5 - Добавить первый в списке Xiaomi и проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
        addFirstProductOfBrandToCompareListAndCheckMsg(FIRST_BRAND);
        // Закрываем попап с информацией о сравнении
        WebElement closePopup = getPresenceElement(By.cssSelector("._8vSeMOsNI6 button"));
        try{
            closePopup.click();
        }catch (WebDriverException e){
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", closePopup);
        }
        // Step 6 - Добавить первый в списке Samsung и проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
        addFirstProductOfBrandToCompareListAndCheckMsg(SECOND_BRAND);
        // Step 7 - Перейти в раздел Сравнение
        String compareUrl = getPresenceElement(By.xpath("//span[text()='Сравнить']/ancestor::a"))
                .getAttribute("href");
        driver.get(compareUrl);
        // Step 8 - Проверить, что в списке товаров 2 позиции
        List<WebElement> listOfProducts = driver.findElements(By.cssSelector("img[alt^='Смартфон'] + a"));
        Assertions.assertEquals(2, listOfProducts.size(), "В сравнении не 2 товара");
        logger.info("В сравнение добавлено {} смартфона", listOfProducts.size());
    }

    private void addFirstProductOfBrandToCompareListAndCheckMsg(String brand){
        String productName = getVisibilityElement(By.xpath("(//a[contains(@title, 'Смартфон " + brand + "')])[1]"))
                .getAttribute("title");
        logger.info("Название первого товара бренда {}: \"{}\"", brand, productName);
        // добавляем первый товар к сравнению
        String compareButtonLocator = "(//a[contains(@title, 'Смартфон "
                + brand + "')]/ancestor::article//div[contains(@aria-label, 'сравнению')]/div)[1]";
        WebElement compareButton = getPresenceElement(By.xpath(compareButtonLocator));
        // Наводимся на кнопку, чтобы она стала кликабельной и нажимаем
        Actions actions = new Actions(driver);
        actions.moveToElement(compareButton).build().perform();
        getClickableElement(By.xpath(compareButtonLocator)).click();
        // проверяем, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
        String alert = getVisibilityElement(
                By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//div[contains(text(), 'Товар')]"))
                .getText();
        logger.info("При добавлении товара в сравнение получено сообщение:\n{}", alert);
        Assertions.assertEquals("Товар " + productName + " добавлен к сравнению", alert,
                "Всплывающее сообщение некорректно");
    }
}
