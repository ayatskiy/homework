import config.WebsiteConfig;
import hooks.BaseHooks;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

class SecondHomeworkTests extends BaseHooks {

    private final WebsiteConfig cfg = ConfigFactory.create(WebsiteConfig.class);
    private final Logger logger = LogManager.getLogger(SecondHomeworkTests.class);

    @Test
    void CheckOtusContactInfoTest(){
        // Step 1 - Откройте сайт https://otus.ru :
        driver.get(cfg.otusUrl());
        logger.info("Сайт c URL={} открыт",cfg.otusUrl());
        // Step 2 - перейти во вкладку "Контакты" и проверить адрес
        getClickableElement(By.cssSelector("a.header2_subheader-link[title='Контакты']")).click();
        logger.info("Перешли на вкладку Контакты");
        // Step 3 - проверить адрес
        String address = getElement(By.xpath("//div[contains(text(), 'Адрес')]/following-sibling::div[1]")).getText();
        Assertions.assertEquals(cfg.otusContactInfo(), address,"Контактная информация неверна");
        logger.info("Адрес и телефон OTUS: {}", address);
    }

    @Test
    void CheckOtusWebsiteTitle() {
        // Step 1 - разверните окно браузера на полный экран(не киоск);
        driver.get(cfg.otusUrl());
        driver.manage().window().maximize();
        logger.info("Сайт c URL={} открыт",cfg.otusUrl());
        // Step 2 - проверьте title страницы
        Assertions.assertEquals(cfg.otusTitle(), driver.getTitle(),"Заголовок страницы не совпал с ожидаемым!!!");
        logger.info("Тест прошёл, заголовок страницы равен: \"{}\", как и ожидалось", cfg.otusTitle());
    }

    @Test
    void CheckTele2Search() {
        // Step 1 - Перейти на сайт теле2 страница https://msk.tele2.ru/shop/number
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(cfg.tele2ShopNumberUrl());
        logger.info("Сайт c URL={} открыт",cfg.tele2ShopNumberUrl());
        // Step 2 - ввести в поле "поиск номера" 97 и начать поиск;
        driver.findElement(By.id("searchNumber")).sendKeys("97");
        // Step 3 - дождаться появления номеров
        List<WebElement> listOfElements = driver.findElements(By.cssSelector("div.number-box span.area-code"));
        Assertions.assertFalse(listOfElements.isEmpty(),
                "На странице не найдены номера телефонов");
        logger.info("На странице показно {} найденных номеров телефонов", listOfElements.size());
    }

    @Test
    void checkOtusFAQText() {
        // Step 1 - Перейдите на сайт https://otus.ru
        driver.get(cfg.otusUrl());
        logger.info("Сайт c URL={} открыт",cfg.otusUrl());
        // Step 2 - перейдите на F.A.Q, нажмите на вопрос: "Где посмотреть программу интересующего курса?";
        getClickableElement(By.cssSelector("a.header2_subheader-link[title='FAQ']")).click();
        // Step 3 - нажмите на вопрос: "Где посмотреть программу интересующего курса?";
        getClickableElement(By.xpath("//div[text()='Где посмотреть программу интересующего курса?']")).click();
        // Step 4 - проверьте, что текст соответствует ожидаемому
        String answer = getElement(By.xpath("//div[text()='Где посмотреть программу интересующего курса?']/following-sibling::div")).getText();
        Assertions.assertEquals(cfg.otusAnswer(), answer,"Ответ не совпал с ожидаемым");
        logger.info("Получен ответ: {}", answer);
    }

    @Test
    void checkOtusSubscription() {
        // Step 1 - Перейдите на сайт https://otus.ru
        driver.get(cfg.otusUrl());
        logger.info("Сайт c URL={} открыт",cfg.otusUrl());
        // Step 2 - заполните тестовый почтовый ящик в поле "Подпишитесь на наши новости";
        getElement(By.cssSelector("input.footer2__subscribe-input")).clear();
        getElement(By.cssSelector("input.footer2__subscribe-input")).sendKeys(cfg.otusEmail());
        logger.info("Ввели email {} для подписки", cfg.otusEmail());
        // Step 3 - нажмите кнопку "Подписаться";
        getClickableElement(By.xpath("//button[contains(@class, 'footer2__subscribe-button')]")).click();
        // Step 4 - проверьте, что появилось сообщение: "Вы успешно подписались".
        String answer = getElement(By.cssSelector(".subscribe-modal__success")).getText();
        Assertions.assertEquals("Вы успешно подписались", answer,"Ответ не совпал с ожидаемым");
        logger.info("Подписка оформлена на email: {}", cfg.otusEmail());
    }

    private WebElement getElement(By locator){
        return new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement getClickableElement(By locator){
        return new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
