package tests;

import static framework.dataobjects.TestContentData.*;

import framework.pageobjects.page.*;
import framework.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;
import ru.yandex.qatools.allure.annotations.Step;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yulia.Yaremenko on 16.03.2016.
 */
@Listeners({RunListener.class})
public class BaseTest {

    protected LoginPage loginPage;
    protected ErrorPage errorPage;
    protected RegistrationPage registrationPage;
    protected SQLManager sqlManager;
    protected String DEFAULT_USER_NAME;

    @Step("Before Class of BaseTest")
    @BeforeClass
    public void beforeBaseClass() {
        WebDriver driver = DriverManager.getInstance().createDriver(PropertyLoader.getInstance().getBrowserName());
        System.out.println("............................ createDriver ............................");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        sqlManager = new SQLManager();
        sqlManager.setUserLocal(DEFAULT_LOCAL, DEFAULT_SMID);
        DEFAULT_USER_NAME = sqlManager.getUserName(DEFAULT_SMID);
    }

    @Step("Before Method of BaseTest")
    @BeforeMethod
    public void beforeBaseMethod(Method method) throws Exception {
         Logger.getLogger(this.getClass()).info("Started BeforeBaseMethod of - " + method.getName());
    }

    @Step("After Method of BaseTest")
    @AfterMethod
    public void afterBaseMethod(Method method) throws Exception {
        Logger.getLogger(this.getClass()).info("Started AfterBaseMethod of - " + method.getName());
    }

    @Step("After Class of BaseTest")
    @AfterClass
    public void afterBaseClass() {
        DriverManager.getInstance().getDriver().quit();
        sqlManager.closeConnect();
    }
}
