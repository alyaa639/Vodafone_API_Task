package apitesting.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file!");
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("Base_URI");
    }
    public static String getEndpoint() {
        return properties.getProperty("end_point");
    }

}
