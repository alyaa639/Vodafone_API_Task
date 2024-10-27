package apitesting.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try {
            // Load the properties file from the resources folder
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file!");
        }
    }

    // Access properties from the config file
    public static String getBaseUri() {
        return properties.getProperty("Base_URI");
    }

    public static String getEndpoint() {
        return properties.getProperty("end_point");
    }

    public static String getqueyparamEndpoint() {
        return properties.getProperty("end_point")+properties.getProperty("_id");
    }

    /*
    public static String getCaptcha() {

        return properties.getProperty("captcha");
    }

     */
/*
    // Method to set the token in the properties file
    public static void setToken(String token) {
        properties.setProperty("access_token", token);
        try (FileOutputStream outputStream = new FileOutputStream("src/main/resources/config.properties")) {
            properties.store(outputStream, null); // Write changes to the properties file
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write to configuration file!");
        }
    }

    public static String getToken() {
        return properties.getProperty("access_token"); // Retrieve the token from the properties file
    }

 */
}
