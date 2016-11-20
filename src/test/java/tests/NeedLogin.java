package tests;

import framework.dataobjects.TestContentData;
import framework.pageobjects.page.BasePage;
import framework.pageobjects.page.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Step;

import static framework.dataobjects.TestContentData.DEFAULT_PASS;
import static framework.dataobjects.TestContentData.DEFAULT_SMID;

/**
 * Created by yaremenko on 19.07.16.
 */
public class NeedLogin extends BaseTest {

    @Step("Before Class of NeedLogin")
    @BeforeClass
    public void beforeNeedLoginClass() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        sqlManager.setParamsForSuccessLogin(DEFAULT_SMID);
        loginPage.loginToHomePage(DEFAULT_SMID, DEFAULT_PASS);
    }

    @BeforeMethod
    public void beforeNeedLoginMethod() throws Exception {

    }

    @AfterMethod
    public void afterNeedLoginMethod() throws Exception {
    }

    @Step("After Class of NeedLogin")
    @AfterClass
    public void afterNeedLoginClass() {
        loginPage.clearBrowsingData();
        sqlManager.removeOpenSession(DEFAULT_SMID);
    }


}
