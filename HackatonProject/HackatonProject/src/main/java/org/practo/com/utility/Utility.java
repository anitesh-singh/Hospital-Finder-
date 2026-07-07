package org.practo.com.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utility {
    public static String fetchPropertyValue(String key) throws IOException {
        Properties prop = new Properties();
        InputStream input = Utility.class.getClassLoader().getResourceAsStream("config.properties");
        if (input != null) prop.load(input);
        String value = prop.getProperty(key);
        if (value != null && !value.trim().isEmpty()) return value.trim();
        if (key.equalsIgnoreCase("browser")) return "chrome";
        if (key.equalsIgnoreCase("baseUrl")) return "https://www.practo.com/";
        return "";
    }
}
