package framework.dataobjects;

import framework.utils.PropertyLoader;

/**
 * Created by yaremenko on 27.05.16.
 */
public class TestContentData {

    public static final String DEFAULT_SMID = PropertyLoader.getInstance().getSMID();
    public static final String DEFAULT_PASS = PropertyLoader.getInstance().getPassword();
    public static final String DEFAULT_PHONE = PropertyLoader.getInstance().getPhone();
    public static final String DEFAULT_LOCAL = "UK";
    public static final String CAPTCHA = "123456";
    public static final String CONTENT_TITLE_REGISTERED = "Реєстрація";
    public static final String CONTENT_TITLE_DASHBOARD = "Дашборд";
    public static final String CONTENT_TITLE_ERROR = "Помилка";
    public static final String CONTENT_URL_ERROR = "error";
    public static final String CONTENT_URL_LOGIN = "login";
    public static final String CONTENT_URL_DASHBOARD = "dashboard";
    public static final String CONTENT_RADIO_SMID_NAME = "Особистий ключ";



}
