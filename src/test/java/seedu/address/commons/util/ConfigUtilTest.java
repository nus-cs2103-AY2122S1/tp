package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataConversionException;

/**
 * Tests functionalities of ConfigUtil.
 */
public class ConfigUtilTest {

    /**
     * Stands for the path of test data.
     */
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ConfigUtilTest");

    /**
     * Stands for temporary directory that will be used here.
     */
    @TempDir
    public Path tempDir;

    /**
     * Checks whether null read will throw {@code NullPointerException}.
     */
    @Test
    public void read_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> read(null));
    }

    /**
     * Checks whether reading missing file will throw {@code DataConversionException} and have an empty result.
     */
    @Test
    public void read_missingFile_emptyResult() throws DataConversionException {
        assertFalse(read("NonExistentFile.json").isPresent());
    }

    /**
     * Checks whether reading a non-JSON file will throw {@code DataConversionException}.
     */
    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> read("NotJsonFormatConfig.json"));
    }

    /**
     * Checks whether file in order is successfully read.
     *
     * @throws DataConversionException
     */
    @Test
    public void read_fileInOrder_successfullyRead() throws DataConversionException {

        Config expected = getTypicalConfig();

        Config actual = read("TypicalConfig.json").get();
        assertEquals(expected, actual);
    }

    /**
     * Checks whether an empty config file will use default values.
     *
     * @throws DataConversionException
     */
    @Test
    public void read_valuesMissingFromFile_defaultValuesUsed() throws DataConversionException {
        Config actual = read("EmptyConfig.json").get();
        assertEquals(new Config(), actual);
    }

    /**
     * Checks extra values in the file read.
     *
     * @throws DataConversionException
     */
    @Test
    public void read_extraValuesInFile_extraValuesIgnored() throws DataConversionException {
        Config expected = getTypicalConfig();
        Config actual = read("ExtraValuesConfig.json").get();

        assertEquals(expected, actual);
    }

    /**
     * Gets rhe typical config required.
     * @return Config object.
     */
    private Config getTypicalConfig() {
        Config config = new Config();
        config.setLogLevel(Level.INFO);
        config.setUserPrefsFilePath(Paths.get("preferences.json"));
        return config;
    }

    /**
     * Reads the config file in test date folder.
     *
     * @param configFileInTestDataFolder String representation of the config file in test date folder.
     * @return Optional Config object.
     * @throws DataConversionException
     */
    private Optional<Config> read(String configFileInTestDataFolder) throws DataConversionException {
        Path configFilePath = addToTestDataPathIfNotNull(configFileInTestDataFolder);
        return ConfigUtil.readConfig(configFilePath);
    }

    /**
     * Checks whether save a null config will throw NullPointerException.
     */
    @Test
    public void save_nullConfig_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> save(null, "SomeFile.json"));
    }

    /**
     * Checks whether save a null file will throw NullPointerException.
     */
    @Test
    public void save_nullFile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> save(new Config(), null));
    }

    /**
     * Checks whether saving a config all in order will succeed.
     *
     * @throws DataConversionException
     * @throws IOException
     */
    @Test
    public void saveConfig_allInOrder_success() throws DataConversionException, IOException {
        Config original = getTypicalConfig();

        Path configFilePath = tempDir.resolve("TempConfig.json");

        //Try writing when the file doesn't exist
        ConfigUtil.saveConfig(original, configFilePath);
        Config readBack = ConfigUtil.readConfig(configFilePath).get();
        assertEquals(original, readBack);

        //Try saving when the file exists
        original.setLogLevel(Level.FINE);
        ConfigUtil.saveConfig(original, configFilePath);
        readBack = ConfigUtil.readConfig(configFilePath).get();
        assertEquals(original, readBack);
    }

    /**
     * Saves config.
     *
     * @param config String representation.
     * @param configFileInTestDataFolder String represents file location.
     * @throws IOException
     */
    private void save(Config config, String configFileInTestDataFolder) throws IOException {
        Path configFilePath = addToTestDataPathIfNotNull(configFileInTestDataFolder);
        ConfigUtil.saveConfig(config, configFilePath);
    }

    /**
     * Adds to test data path if not null.
     *
     * @param configFileInTestDataFolder String representation.
     * @return Path.
     */
    private Path addToTestDataPathIfNotNull(String configFileInTestDataFolder) {
        return configFileInTestDataFolder != null
                                  ? TEST_DATA_FOLDER.resolve(configFileInTestDataFolder)
                                  : null;
    }
}
