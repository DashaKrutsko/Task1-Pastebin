package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.PastebinHomePage;

import java.time.Duration;

public class PastebinCreatingNewPastTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "It's a first test")
    public void newPasteCreateNewItem() {
        new WebDriverWait(driver, Duration.ofSeconds(10));
        PastebinHomePage homePage = new PastebinHomePage(driver)
                .openPage()
                .acceptCookies()
                .enterCode()
                .enterName()
                .enterExpiration()
                .createNewPaste();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

}

