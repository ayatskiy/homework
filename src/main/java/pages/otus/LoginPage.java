package pages.otus;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.AbstractPage;

import static webdriver.WebDriverExplicitWaitElements.getClickableElement;

public class LoginPage extends AbstractPage {
    private final WebsiteConfig cfg = ConfigFactory.create(WebsiteConfig.class);
    private final By emailField = By.xpath("//input[@placeholder='Электронная почта'][contains(@class, 'email-input')]");
    private final By passwordField = By.xpath("//input[contains(@class, 'psw-input')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(cfg.otusLoginPageUrl());
        return this;
    }

    public void loginToOtus(String username, String password) {
        getClickableElement(emailField).sendKeys(username);
        getClickableElement(passwordField).sendKeys(password, Keys.ENTER);
    }

}