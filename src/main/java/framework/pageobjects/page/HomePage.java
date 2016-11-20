package framework.pageobjects.page;

import framework.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static framework.dataobjects.LocatorsData.*;
import static framework.dataobjects.TestContentData.*;

/**
 * Created by Yulia.Yaremenko on 16.03.2016.
 */
public class HomePage extends PageObject {

    public HomePage() {
        //wait until content is loaded
        wait.until(titleIs(CONTENT_TITLE_DASHBOARD));
    }


}
