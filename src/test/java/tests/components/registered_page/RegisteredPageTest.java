package tests.components.registered_page;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import tests.NotNeedLogin;

import java.util.*;

import static framework.dataobjects.TestContentData.*;
import static framework.dataobjects.ErrorMessageData.*;
import static framework.dataobjects.SystemPropertyData.*;

/**
 * Created by yaremenko on 29.09.16.
 */
@Title("This is test suite of Registration")
@Description("There is a description of test suite")
public class RegisteredPageTest extends NotNeedLogin {

    @BeforeMethod
    public void beforeMethod() {
        registrationPage = loginPage.getRegistrationPage();
    }

    @Features("Self-Registration")
    @Stories("Check content of RegistrationPage")
    @Test
    public void registeredCheckContent() {
        List<String> actualContent = registrationPage.getContentRegistrationPage();
        ArrayList<String> expContent = new ArrayList<String>();
        expContent.add(CONTENT_INPUT_SMID);
        expContent.add(CONTENT_INPUT_PHONE_FIELD);
        expContent.add(CONTENT_RESEND_PASS_FIELD_CAPTCHA);
        expContent.add(CONTENT_RESET_CAPTCHA);
        expContent.add(CONTENT_BUTTON_NEXT);
        expContent.add(CONTENT_BUTTON_BACK);
        Assert.assertEquals(actualContent, expContent);
    }

    @Features("Self-Registration")
    @Stories("Input wrong Captcha")
    @Test(dataProvider = "defaultData")
    public void registeredWrongCaptcha(String smid, String phone, String captcha){
        registrationPage.registeredStepFirst(smid, phone, "000000");
        String actualMessage = registrationPage.getRegistrationErrorMessage();
        Assert.assertEquals(actualMessage, ERROR_WRONG_CAPTCHA);
    }

    @Features("Self-Registration")
    @Stories("Resend IPASS, resend limit")
    @Test(dataProvider = "defaultData")
    public void registeredResendIpass(String smid, String phone, String captcha) {
        sqlManager.setSystemProperty(PROPERTY_RESEND_IPASS_TIMEOUT, 0);
        String count = sqlManager.getSystemProperty(PROPERTY_RESEND_IPASS_COUNT);
        registrationPage.registeredStepFirst(smid, phone, captcha);
        for (int i = 1; i <= Integer.parseInt(count) - 1; i++) {
            registrationPage.registResendIpass();
        }
        String actualMessage = registrationPage.getRegistrationErrorMessage();
        Assert.assertEquals(actualMessage, ERROR_RESEND_IPASS_LIMIT);
    }

    @DataProvider
    public Object[][] defaultData() {
        return new Object[][]{ //
                {DEFAULT_SMID, DEFAULT_PHONE, CAPTCHA}
        };
    }



}
