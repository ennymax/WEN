package utility;

import Base.TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility extends TestBase {

    private static final String DATA_CONFIG = "./src/test/resources/config.properties";
    private static Properties properties;

    public static String getValue(String value) {
        try {
            if (properties == null) {
                Utility.properties = new Properties();
                Utility.properties.load(new FileInputStream(DATA_CONFIG));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Utility.properties.getProperty(value);
    }

    public static Object fetchService(String key) throws IOException {

        FileInputStream file = new FileInputStream("./Config/Service.properties");
        Properties property = new Properties();
        property.load(file);
        return property.get(key);
    }

    public static Object fetchProperty(String key) throws IOException {

        FileInputStream file = new FileInputStream("./Config/config.properties");
        Properties property = new Properties();
        property.load(file);
        return property.get(key);
    }

    public static String fetchLocator(String key) throws IOException {

        FileInputStream file = new FileInputStream("./Config/Locators.properties");
        Properties property = new Properties();
        property.load(file);
        return property.get(key).toString();
    }
}
