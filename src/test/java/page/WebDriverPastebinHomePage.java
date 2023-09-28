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

public class WebDriverPastebinHomePage {
    private static final String HOME_PAGE_URL = "https://pastebin.com/";
    private static final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(10);

    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement searchCode;

    @FindBy(id = "postform-expiration")
    private WebElement searchExpiration;

    @FindBy(id = "postform-name")
    private WebElement searchName;

    @FindBy(xpath = "//button[@mode='primary']")
    private WebElement searchAcceptCookies;

    @FindBy(xpath = "//button[contains(text(), 'Create New Paste')]")
    private WebElement searchButton;

    public WebDriverPastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriverPastebinHomePage openPage() {
        driver.get(HOME_PAGE_URL);
        return this;
    }

    public WebDriverPastebinHomePage acceptCookies() {
        waitForVisibilityOfElement(searchAcceptCookies).click();
        return this;
    }

    public WebDriverPastebinHomePage enterValues() {

        waitForVisibilityOfElement(searchCode).sendKeys("Hello from WebDriver");

        waitForVisibilityOfElement(searchName).sendKeys("helloweb");

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].style.visibility='visible';", searchExpiration);
        Select selectExpiration = new Select(waitForVisibilityOfElement(searchExpiration));
        selectExpiration.selectByVisibleText("10 Minutes");

        return this;
    }

    public WebDriverPastebinHomePage createNewPaste() {
        waitForVisibilityOfElement(searchButton).click();
        return this;
    }

    private WebElement waitForVisibilityOfElement(WebElement webElement) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(webElement));
    }
}

