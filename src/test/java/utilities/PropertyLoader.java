package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private static final String CONFIG_FILE_PATH = "/config/test-config.properties";
    private static final Logger logger = LogManager.getLogger(PropertyLoader.class);

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = PropertyLoader.class.getResourceAsStream(CONFIG_FILE_PATH)) {
            if (input == null) {
                logger.error("Unable to find the configuration file: " + CONFIG_FILE_PATH);
            } else {
                logger.info("Loading properties...");
                properties.load(input);
                logger.info("Properties successfully loaded");
            }
        } catch (IOException e) {
            logger.error("Failed to load properties: " + e.getMessage());
        }
        return properties;
    }
}
