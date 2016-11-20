package framework.pageobjects;

import framework.pageobjects.page.BasePage;
import framework.utils.DriverManager;
import framework.utils.SQLManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Yulia.Yaremenko on 16.03.2016.
 */
public class PageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageObject() {
        driver = DriverManager.getInstance().getDriver();
        System.out.println("............................ getDriver ............................");
        this.wait = new WebDriverWait(driver, 3, 250);
        PageFactory.initElements(driver, this);
    }

    @Step("Get current URL.")
    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }

    @Step("Get Title")
    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    @Step("Refresh current page.")
    public void refreshPage() {
        driver.navigate().refresh();
    }

    @Step("Get error message from page.")
    public String getErrorMessage(String selector) {
        WebElement errorMessage = wait.until(presenceOfElementLocated(By.cssSelector(selector)));
        return errorMessage.getText();
    }

    @Step("Clear browsing data")
    public void clearBrowsingData(){
        DriverManager.getInstance().getDriver().manage().deleteAllCookies();
    }
}
