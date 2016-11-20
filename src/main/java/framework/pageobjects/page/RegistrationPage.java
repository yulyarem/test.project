package framework.pageobjects.page;

import framework.pageobjects.PageObject;
import framework.utils.ControlsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static framework.dataobjects.LocatorsData.*;
import static framework.dataobjects.TestContentData.*;

/**
 * Created by yaremenko on 29.09.16.
 */
public class RegistrationPage extends PageObject {

    @FindBy(css = CSS_REGISTERED_INPUT_SMID )
    private WebElement inputSmid;
    @FindBy(css = CSS_REGISTERED_INPUT_PHONE)
    private WebElement inputPhone;
    @FindBy(css = CSS_INPUT_CAPTCHA)
    private WebElement inputCaptha;
    @FindBy(css =  CSS_BUTTON_ACTION_DEFAULT)
    private WebElement buttonNext;
    @FindBy(css =  CSS_INPUT_IPASS)
    private WebElement inputIpass;
    @FindBy(css =  CSS_INPUT_FIELD_NAME)
    private WebElement inputFieldName;
    @FindBy(css =  CSS_INPUT_NEW_PASSWORD)
    private WebElement inputNewPassword;
    @FindBy(css =  CSS_INPUT_CONFIRM_NEW_PASSWORD)
    private WebElement inputConfirmNewPassword;
    @FindBy(css =  CSS_INPUT_EMAIL)
    private WebElement inputEmail;


    public RegistrationPage(){
        //wait until content is loaded
        wait.until(titleIs(CONTENT_TITLE_REGISTERED));
        wait.until(presenceOfElementLocated(By.cssSelector(CSS_INPUT_SQ_FIELD_NAME)));
        wait.until(presenceOfElementLocated(By.cssSelector(CSS_REGISTERED_INPUT_SMID)));
        wait.until(presenceOfElementLocated(By.cssSelector(CSS_REGISTERED_INPUT_PHONE)));
        wait.until(presenceOfElementLocated(By.cssSelector(CSS_BUTTON_ACTION_DEFAULT)));
        wait.until(presenceOfElementLocated(By.cssSelector(CSS_BUTTON_ACTION_BACK)));
        wait.until(presenceOfElementLocated(By.cssSelector(CSS_INPUT_CAPTCHA)));
    }


    @Step("Get Content from RegistPage")
    public List getContentRegistrationPage(){
        ArrayList<String> actualContentList= new ArrayList<String>();
        List<WebElement> elements = driver.findElements(By.cssSelector(CSS_INPUT_SQ_FIELD_NAME));
        for (WebElement list: elements){
            actualContentList.add(list.getText());
        }
        actualContentList.add(driver.findElement(By.cssSelector(CSS_BUTTON_ACTION_DEFAULT)).getText());
        actualContentList.add(wait.until(presenceOfElementLocated(By.cssSelector(CSS_BUTTON_ACTION_BACK))).getText());
        return actualContentList;
    }

    @Step("Get Error Message from RegistPage")
    public String getRegistrationErrorMessage(){
        String actualMessage = getErrorMessage(CSS_ERROR_MESSAGE);
        return actualMessage;
    }

    @Step("Input data on RegistPage (Step1 - input SMID= {0}, Phone= {1}, Captcha)")
    public void registeredStepFirst(String smid, String phone, String captcha){
        ControlsHelper.fillTextBox(inputSmid, smid);
        ControlsHelper.fillTextBox(inputPhone, phone.substring(4));
        ControlsHelper.fillTextBox(inputCaptha, captcha);
        ControlsHelper.buttonClickJs(driver, buttonNext);
    }
}
