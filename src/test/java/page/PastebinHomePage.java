package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinHomePage {
    private static final String HOME_PAGE_URL = "https://pastebin.com/";
    private static final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(20);

    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement searchCode;

    @FindBy(id = "postform-expiration")
    private WebElement searchExpiration;

    @FindBy(id = "postform-name")
    private WebElement searchName;

    @FindBy(xpath = "//button[@mode='primary']")
    private WebElement searchAcceptCookies;

    @FindBy(xpath = "//button[@type='submit'][@class='btn -big']")
    private WebElement searchButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOME_PAGE_URL);
        return this;
    }

    public PastebinHomePage acceptCookies() {
        waitForVisibilityOfElement(searchAcceptCookies).click();
        return this;
    }

    public PastebinHomePage enterCode() {
        waitForVisibilityOfElement(searchCode).sendKeys("Hello from WebDriver");
        return this;
    }

    public PastebinHomePage enterName() {
        waitForVisibilityOfElement(searchName).sendKeys("helloweb");
        return this;
    }

    public PastebinHomePage enterExpiration() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].style.visibility='visible';", searchExpiration);
        Select selectExpiration = new Select(waitForVisibilityOfElement(searchExpiration));
        selectExpiration.selectByVisibleText("10 Minutes");
        return this;
    }

    public PastebinHomePage createNewPaste() {
        waitForVisibilityOfElement(searchButton).click();
        return this;
    }

    private WebElement waitForVisibilityOfElement(WebElement webElement) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(webElement));
    }
}

