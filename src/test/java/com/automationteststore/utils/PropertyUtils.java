package com.automationteststore.utils;

import com.automationteststore.constants.FrameworkConstants;
import com.automationteststore.enums.ConfigProperties;
import com.automationteststore.exceptions.PropertyFileUsageException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


/**
 * Read the property file and store it in a HashMap for faster processing.
 * Users can prefer to use json instead of property file based on their requirement.
 *
 * <pre>
 * <b>
 * <a href="https://www.youtube.com/channel/UC6PTXUHb6j4Oxf0ccdRI11A">Testing Mini Bytes Youtube channel</a>
 * </b>
 * </pre>
 */
public final class PropertyUtils {
    private static final Map<String, String> CONFIGMAP = new HashMap<>();
    private static Properties property = new Properties();

    static {
        try (FileInputStream file = new FileInputStream(FrameworkConstants.getConfigFilePath())) {
            property.load(file);
            for (Map.Entry<Object, Object> entry : property.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim()); //remove the trailing and leading spaces
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Properties p = new Properties();

    /**
     * Private constructor to avoid external instantiation
     */
    private PropertyUtils() {
    }

    public static String get(ConfigProperties key) {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new PropertyFileUsageException("Property name " + key + " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }

    public static Properties loadPropertyFile(String pathname) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(pathname);
        prop.load(fis);
        return prop;
    }


}
