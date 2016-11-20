package framework.dataobjects;

/**
 * Created by yaremenko on 20.07.16.
 */
public class LocatorsData {

    public static final String CSS_BUTTON_ACTION_DEFAULT = ".button.actionDefault";
    public static final String CSS_BUTTON_ACTION_BACK = ".button.back";
    public static final String CSS_INPUT_CAPTCHA = "input[name*='captcha']";
    public static final String CSS_ERROR_MESSAGE = ".feedbackPanelERROR .feedbackPanelERROR";

    //LoginPage
    public static final String CSS_RADIO_SMID = "label[for*='SMID']";
    public static final String CSS_RADIO_PHONE = "label[for*='PHONE']";
    public static final String CSS_INPUT_SMID = "div[class='smid formRow'] input";
    public static final String CSS_INPUT_PHONE = "div[class='phone formRow'] input";
    public static final String CSS_INPUT_PASSWORD = "div[class='password formRow'] input";

    //RegistrationPage
    //step 1
    public static final String CSS_REGISTERED_LINK = ".actionRegister";
    public static final String CSS_REGISTERED_INPUT_SMID = "input[name*='smid']";
    public static final String CSS_REGISTERED_INPUT_PHONE = "input[name*='phone']";
    //step 2
    public static final String CSS_INPUT_SQ_FIELD_NAME = ".label";
    public static final String CSS_INPUT_FIELD_NAME = "label[class='required']";
    public static final String CSS_INPUT_IPASS = "input[name*='password']";

    //Set new password
    public static final String CSS_INPUT_NEW_PASSWORD = "input[name*='newPassword']";
    public static final String CSS_INPUT_CONFIRM_NEW_PASSWORD = "input[name*='confirmNewPassword']";
    public static final String CSS_INPUT_EMAIL = "input[name*='email']";

    //ErrorPage
    public static final String CSS_ERROR_PAGE_MESSAGE = ".message>p";
    public static final String CSS_ERROR_PAGE_BUTTON_BACK = ".actionGoToMainPage";
    public static final String CSS_ERROR_PAGE_BUTTON_CLOSE = ".actionClose";




}
