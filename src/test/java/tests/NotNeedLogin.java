package tests;

import framework.dataobjects.TestContentData;
import framework.pageobjects.page.BasePage;
import framework.pageobjects.page.ErrorPage;
import framework.pageobjects.page.LoginPage;
import framework.utils.PropertyLoader;
import framework.utils.Screenshooter;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Step;

import static framework.dataobjects.TestContentData.*;

/**
 * Created by yaremenko on 19.07.16.
 */
public class NotNeedLogin extends BaseTest {

    @Step("Before Method of NoNeedLogin")
    @BeforeMethod
    public void beforeNotNeedLoginMethod() throws Exception {
            loginPage = new LoginPage();
            loginPage.open();
    }

    @Step("After Method of NoNeedLogin")
    @AfterMethod
    public void afterNotNeedLoginMethod() {
        loginPage.clearBrowsingData();
    }

}
