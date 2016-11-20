package tests.components.login_page;

/**
 * Created by Yulia.Yaremenko on 09.03.2016.
 */

import static framework.dataobjects.TestContentData.*;
import static framework.dataobjects.ErrorMessageData.*;
import org.testng.annotations.*;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import tests.NotNeedLogin;

@Title("This is test suite of Login")
@Description("There is a description of test suite")
public class LoginPageTest extends NotNeedLogin {

    @Features("Login")
    @Stories("Login by PHONE is Forbidden")
    @Test(dataProvider = "defaultData")
    public void loginByPhoneFailed(String smid, String password, String phone, String name) {
        sqlManager.setLoginByPhone(0, smid);
        loginPage.loginToLoginPage(phone, password);
        String actualErrorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(actualErrorMessage, ERROR_NOT_ALLOWED_LOGIN_BY_PHONE);
    }

    @Features("Login")
    @Stories("Login with not valid Credentials")
    @Test(dataProvider = "wrongCredentials")
    public void loginWrongCredentials(String smid, String password, String expMessage) {
        loginPage.loginToLoginPage(smid, password);
        String actualErrorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(actualErrorMessage, expMessage);
    }

    @Features("Login")
    @Stories("Login with Phone = null")
    @Test(dataProvider = "defaultData")
    public void loginWithPhoneNull(String smid, String password, String phone, String name){
        sqlManager.removePhone(smid);
        loginPage.loginToLoginPage(smid, password);
        String actualErrorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(actualErrorMessage, ERROR_YOUR_PHONE_IS_EMPTY);
    }

    @DataProvider
    public Object[][] defaultData() {
        return new Object[][]{
                {DEFAULT_SMID, DEFAULT_PASS, DEFAULT_PHONE, DEFAULT_USER_NAME}
        };
    }

    @DataProvider
    public Object[][] wrongCredentials() {
        return new Object[][]{ //
                {DEFAULT_SMID, "0000", ERROR_WRONG_PASS}, //
                {"xxxxxx", DEFAULT_PASS, ERROR_USER_NOT_FOUND},
                {"", DEFAULT_PASS, ERROR_INPUT_IS_EMPTY},
                {DEFAULT_SMID, "", ERROR_INPUT_IS_EMPTY},
                {"000", DEFAULT_PASS, ERROR_INPUT_IS_WRONG_MASK},
                {"zzzz", DEFAULT_PASS, ERROR_INPUT_IS_WRONG_MASK}
        };
    }
}
