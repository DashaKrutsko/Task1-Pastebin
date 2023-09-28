package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import page.WebDriverPastebinHomePage;

import java.time.Duration;

public class WebDriverPastebinCreatingNewPastTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "It's a first test")
    public void newPasteCreateNewItem() {
        new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverPastebinHomePage homePage = new WebDriverPastebinHomePage(driver)
                .openPage()
                .acceptCookies()
                .enterValues()
                .createNewPaste();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

}

