package webdriver;

import hooks.BaseHooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebDriverExplicitWaitElements extends BaseHooks {

    private static final Long WAIT_TIMEOUT = 12L;

    public static WebElement getVisibilityElement(By locator){
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement getClickableElement(By locator){
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement getPresenceElement(By locator) {
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static Boolean waitInvisibilityElement(By locator){
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}