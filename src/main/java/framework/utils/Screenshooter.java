package framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;

/**
 * Created by Yulia.Yaremenko on 27.03.2016.
 */
public class Screenshooter {
    private final static Screenshooter INSTANCE = new Screenshooter();

    private Screenshooter() {
    }

    public static Screenshooter getInstance() {
        return INSTANCE;
    }

    @Attachment(value = "ScreenshotAttachment", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
