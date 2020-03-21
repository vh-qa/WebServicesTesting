package in.co.gorest.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {
    private Map<String, PropertyWrapper> properties = new HashMap<>();
    private static PropertyReader instance;

    private PropertyReader() {
    }

    private static PropertyReader getInstance() {
        if (instance == null) {
            instance = new PropertyReader();
        }
        return instance;
    }

    private void loadProperties(String propertyFileName, String propertyName) {
        if (properties.containsKey(propertyName))
            return;

        properties.put(propertyName, new PropertyWrapper());
//      String path = System.getProperty(propertyName + ".cfg");

        try {
//          InputStream is = new FileInputStream(path);
//          properties.get(propertyName).load(new InputStreamReader(is, "UTF-8"));
            properties.get(propertyName)
                    .load(new InputStreamReader(this.getClass().getResourceAsStream(propertyFileName), "UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyWrapper from(String propertyFileName, String propertyName) {
        getInstance().loadProperties(propertyFileName, propertyName);
        return getInstance().properties.get(propertyName);
    }

    public class PropertyWrapper {
        private Properties properties = new Properties();

        private void load(InputStreamReader isr) throws IOException {
            properties.load(isr);
        }

        public String getProperty(String key) {
            return properties.getProperty(key);
        }
    }
}