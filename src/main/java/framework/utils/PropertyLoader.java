package framework.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Yulia.Yaremenko on 27.03.2016.
 */
public class PropertyLoader {
    private static final String PROPERTIES_FILE = "/config.properties";
    private static final PropertyLoader INSTANCE = new PropertyLoader();

    private final Properties properties;

    private PropertyLoader() {
        properties = new Properties();

        loadPropertiesFromFile();
    }

    public static PropertyLoader getInstance() {
        return INSTANCE;
    }

    public String getSMID() {
        return properties.getProperty("SMID");
    }

    public String getPhone() {
        return properties.getProperty("PHONE");
    }

    public String getPassword() {
        return properties.getProperty("PASSWORD");
    }

    public String getEnv() {
        return properties.getProperty("environment");
    }

    public String getDBUrl() {
        String env = getEnv();
        return properties.getProperty("DB_URL_" + env);
    }

    public String getDBLogin() {
        String env = getEnv();
        return properties.getProperty("DB_LOGIN_" + env);
    }

    public String getDBPassword() {
        String env = getEnv();
        return properties.getProperty("DB_PASSWORD_" + env);
    }

    public String getAppUrl() {
        String env = getEnv();
        return properties.getProperty("site.url." + env);
    }

    public String getBrowserName() {
        return System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
    }

    private void loadPropertiesFromFile() {
        try {
            properties.load(PropertyLoader.class.getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get properties from " + PROPERTIES_FILE, e);
        }
    }
}
