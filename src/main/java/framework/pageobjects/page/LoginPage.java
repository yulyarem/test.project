package framework.pageobjects.page;

import framework.pageobjects.PageObject;
import framework.utils.ControlsHelper;
import framework.utils.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashMap;

import static framework.dataobjects.LocatorsData.*;
import static framework.dataobjects.TestContentData.*;


/**
 * Created by Yulia.Yaremenko on 16.03.2016.
 */
public class LoginPage extends PageObject {

    @FindBy(css = CSS_RADIO_SMID)
    private WebElement radioButtonSmid;
    @FindBy(css = CSS_RADIO_PHONE)
    private WebElement radioButtonPhone;
    @FindBy(css = CSS_INPUT_SMID)
    private WebElement inputSmid;
    @FindBy(css = CSS_INPUT_PHONE)
    private WebElement inputPhone;
    @FindBy(css = CSS_INPUT_PASSWORD)
    private WebElement inputPassword;
    @FindBy(css = CSS_BUTTON_ACTION_DEFAULT)
    private WebElement buttonLogin;
    @FindBy(css = ".smid.formRow .required")
    private WebElement titleSmidField;
    @FindBy(css = CSS_INPUT_EMAIL)
    private WebElement inputEmail;

    @Step("Up browser and Get URL")
    public void open() {
        driver.get(PropertyLoader.getInstance().getAppUrl());
    }

    @Step("Login and Get HomePage")
    public HomePage loginToHomePage(String login, String password) {
        PageObject page = tryLoginAs(login, password);
        if (page instanceof HomePage) {
            return (HomePage) page;
        } else {
            throw new IllegalArgumentException(
                    String.format("Couldn't login with valid credentials (%1$s//%2$s)", login, password));
        }
    }

    @Step("Login and Get ErrorPage")
    public ErrorPage loginToErrorPage(String login, String password) {
        PageObject page = tryLoginAs(login, password);

        if (page instanceof ErrorPage) {
            return (ErrorPage) page;
        } else {
            throw new IllegalArgumentException(
                    String.format("ErrorPage not found after login with credentials (%1$s//%2$s)", login, password));
        }
    }

    @Step("Login and Get LoginPage")
    public LoginPage loginToLoginPage(String login, String password) {
        PageObject page = tryLoginAs(login, password);

        if (page instanceof LoginPage) {
            return (LoginPage) page;
        } else {
            throw new IllegalArgumentException(
                    String.format("LoginPage not found after login with credentials (%1$s//%2$s)", login, password));
        }
    }

    @Step("Try to Login and Get One of Page")
    public PageObject tryLoginAs(String login, String password) {
        loginBy(login, password);
        Boolean isLoginPage = false;
        try {
            wait.until(not(presenceOfElementLocated(By.cssSelector(CSS_REGISTERED_LINK))));
        } catch (TimeoutException ex) {
            isLoginPage = true;
            System.out.println("It is still LoginPage. Error = " + ex);
        }
        if(getCurrentUrl().contains(CONTENT_URL_LOGIN)) {
            String title = driver.getTitle();
            System.out.println("Title is = " + title);
            String inputField = driver.findElement(By.cssSelector(CSS_INPUT_FIELD_NAME)).getText();
            switch (inputField) {
                case CONTENT_INPUT_EMAIL_FIELD_NAME:
                    return new SetEmailPage();
                case CONTENT_INPUT_OTP_FIELD:
                    return new LoginOtpPage();
                case CONTENT_INPUT_QUESTION_NAME:
                    return new SetSecurityQuestionPage();
                case CONTENT_INPUT_NEW_PASSWORD_FIELD_NAME:
                    return new SetPasswordPage();
            }
        }else {
            if (getCurrentUrl().contains(CONTENT_URL_ERROR)) {
                String title = driver.getTitle();
                System.out.println("Title is = " + title);
                return new ErrorPage();
            } else {
                if (getCurrentUrl().contains(CONTENT_URL_DASHBOARD)) {
                    String title = driver.getTitle();
                    System.out.println("Title is = " + title);
                    return new HomePage();
                }
            }
        }
        return new LoginPage();
    }

    @Step("Login by SMID or Phone")
    public void loginBy(String login, String password) {
        if (login.contains("+380")) {
            ControlsHelper.fillTextBox(inputPassword, password);
            ControlsHelper.fillTextBox(inputPhone, login.substring(4));
            wait.until(elementToBeClickable(buttonLogin));
            ControlsHelper.buttonClick(buttonLogin);
        }else{
            WebElement element = driver.findElement(By.cssSelector(CSS_RADIO_SMID));
            wait.until(elementToBeClickable(element));
            ControlsHelper.buttonClick(element);
            wait.until(textToBePresentInElement(titleSmidField, CONTENT_RADIO_SMID_NAME));
            ControlsHelper.fillTextBox(inputPassword, password);
            ControlsHelper.fillTextBox(inputSmid, login);
            WebElement buttonNext = driver.findElement(By.cssSelector(CSS_BUTTON_ACTION_DEFAULT));
            wait.until(elementToBeClickable(buttonNext));
            ControlsHelper.buttonClick(buttonNext);
        }
    }

    @Step("Get error message from LoginPage")
    public String getLoginErrorMessage(){
        String actualMessage = getErrorMessage(CSS_ERROR_MESSAGE);
        return actualMessage;
    }

    @Step("Get general RegistrationPage")
    public RegistrationPage getRegistrationPage(){
        wait.until(elementToBeClickable(By.cssSelector(CSS_REGISTERED_LINK))).click();
        return new RegistrationPage();
    }
}

