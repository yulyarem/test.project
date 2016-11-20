package framework.pageobjects.page;

import framework.pageobjects.PageObject;
import framework.utils.ControlsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import org.testng.Assert;
import static framework.dataobjects.LocatorsData.*;
import static framework.dataobjects.TestContentData.*;
/**
 * Created by yaremenko on 19.07.16.
 */
public class ErrorPage extends PageObject {

    public ErrorPage() {
        //wait until content is loaded
        wait.until(titleIs(CONTENT_TITLE_ERROR));
    }

    public String getErrorMessage() {
        String errorMessage = getErrorMessage(CSS_ERROR_PAGE_MESSAGE);
        return errorMessage;
    }

    public LoginPage clickErrorPageButtonBack(){
        WebElement buttonBack = driver.findElement(By.cssSelector(CSS_ERROR_PAGE_BUTTON_BACK));
        ControlsHelper.buttonClick(buttonBack);

        return new LoginPage();
    }

}
