package pages.otus;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import pages.AbstractPage;

public class MainPage extends AbstractPage {
    private final WebsiteConfig cfg = ConfigFactory.create(WebsiteConfig.class);

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get(cfg.otusUrl());
        return this;
    }

    public HeaderBar openHeaderMenu() {
        return new HeaderBar(driver).openMenu();
    }
}