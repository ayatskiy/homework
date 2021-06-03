package pages.otus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.AbstractPage;

import static webdriver.WebDriverExplicitWaitElements.*;

public class HeaderBar extends AbstractPage {
    private final By headerMenu = By.cssSelector(".ic-blog-default-avatar");
    private final By header2UserName = By.cssSelector(".header2-menu__dropdown-text");

    protected HeaderBar(WebDriver driver) {
        super(driver);
    }

    public HeaderBar openMenu() {
        WebElement menu = getClickableElement(headerMenu);
        var actions = new Actions(driver);
        actions.moveToElement(menu).click().build().perform();
        return new HeaderBar(driver);
    }

    public PersonalPage openPersonalPage() {
        openMenu();
        getClickableElement(header2UserName).click();
        return new PersonalPage(driver);
    }
}