package framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.testng.AllureTestListener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yaremenko on 05.10.16.
 */
public class RunListener extends AllureTestListener { //implements ITestListener

    private Logger log = LoggerFactory.getLogger(RunListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test SUCCESS: " + result.getName());
        saveTextLog("Result is...", "<<<<<<< Good job! >>>>>>");
        saveHtmlAttach("You are like...", true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Screenshooter.getInstance().takeScreenshot();
        log.error("Test FAILED: " + result.getName());
        if (result.getThrowable()!=null) {
            result.getThrowable().printStackTrace();
        }
        saveTextLog("Result is...", "<<<<<<< Oh, no.... Fail>>>>>>");
        saveHtmlAttach("You are like...", false);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.error("Test SKIPPED: " + result.getName());
        saveTextLog("Result is...", "<<<<<<< Oh, no.... Skip>>>>>>");
        saveHtmlAttach("You are like...", false);
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test finished");
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String attachName, String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static byte[] saveHtmlAttach(String attachName, Boolean res) {
        try {
            Path path;
            if(res)
                path = Paths.get("src/test/resources/successAttach.html");
            else
                path = Paths.get("src/test/resources/failAttach.html");

            byte[] data = Files.readAllBytes(path);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
