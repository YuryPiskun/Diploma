package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertiesLoader.class.getClassLoader().
                    getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static String getUrl() {
        return properties.getProperty("url");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}
