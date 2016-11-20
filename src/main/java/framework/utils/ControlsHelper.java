package framework.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * Created by Yulia.Yaremenko on 27.03.2016.
 */
public class ControlsHelper {

    public static void fillTextBox(WebElement textBox, String value) {
        if (textBox.isEnabled()) {
            textBox.clear();
            textBox.sendKeys(value);
        }else {
            throw new UnsupportedOperationException("Text box is not enabled");
        }
    }

    public static void fillTextBoxOneChar(WebElement textBox, Character value) {
        textBox.sendKeys(value.toString());
    }

    //ввод текста посимвольно
    public static void fillTextBoxToChar(WebElement textBox, char[] value) {
            textBox.clear();
            int length = value.length;
            textBox.sendKeys(""+""+"");
            for(int i=0;i<length;i++){
                System.out.println("char[" + i + "] = " +value[i]);
                textBox.sendKeys(value[i] + "");
        }
    }

    //ввод текста посимвольно
    public static void fillTextBoxToChar2(WebElement textBox, String value) {
        if (textBox.isEnabled()) {
            textBox.clear();
            for (Character key : value.toCharArray()) {
                textBox.sendKeys(key + "");
            }
        }
    }

    public static void buttonClick (WebElement button) {
        if (button.isEnabled()) {
            button.click();
        }else {
            throw new UnsupportedOperationException("Button is not enabled");
        }
    }

    public static void buttonClickJs (WebDriver driver, WebElement button){
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", button);
    }


}
