import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class ElementsInteraction {

    protected static WebDriver driver;
    private static final Long WAIT_TIMEOUT = 12L;

    protected WebElement getVisibilityElement(By locator){
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement getClickableElement(By locator){
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement getPresenceElement(By locator) {
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected Boolean waitInvisibilityElement(By locator){
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
