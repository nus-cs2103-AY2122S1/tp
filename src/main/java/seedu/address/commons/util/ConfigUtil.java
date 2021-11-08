package seedu.address.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataConversionException;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {

    /**
     * This method reads the configuration file path and returns
     * an optional object containing a Config.
     *
     * @param configFilePath Path of the configuration file.
     * @return Optional containing a Config.
     * @throws DataConversionException if there is error in reading the
     * Json file at the provided configuration file path.
     */
    public static Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    /**
     * This method saves the current configuration to the provided
     * file at the path provided for the configuration file.
     *
     * @param config is the current configuration file.
     * @param configFilePath Path of the configuration file.
     * @throws IOException if there is error in saving the
     * Json file at the provided configuration file path.
     */
    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}
