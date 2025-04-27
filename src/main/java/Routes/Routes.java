package Routes;

import Utils.PropertiesUtil;

import java.util.Properties;

public class Routes {

    private static final Properties properties = PropertiesUtil.loadProperties();

    private static void validateProperties() {
        if (properties == null) {
            throw new RuntimeException("Properties file not loaded! Check src/main/resources/config.properties.");
        }
    }

    public static String getBaseUrl() {
        validateProperties();
    return PropertiesUtil.getPropertyValue("base_url", PropertiesUtil.getPropertyValue("BASE_URL"));
    }

    public static String getApiKey() {
        validateProperties();
        return PropertiesUtil.getPropertyValue("api_key", PropertiesUtil.getPropertyValue("API_KEY"));
    }

    public static String getToken() {
        validateProperties();
        return PropertiesUtil.getPropertyValue("token", PropertiesUtil.getPropertyValue("TOKEN"));
    }

}