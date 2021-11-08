package seedu.smartnus;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.smartnus.commons.core.Config;
import seedu.smartnus.commons.core.LogsCenter;
import seedu.smartnus.commons.core.Version;
import seedu.smartnus.commons.exceptions.DataConversionException;
import seedu.smartnus.commons.util.ConfigUtil;
import seedu.smartnus.commons.util.StringUtil;
import seedu.smartnus.logic.Logic;
import seedu.smartnus.logic.LogicManager;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.ReadOnlyUserPrefs;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.util.SampleDataUtil;
import seedu.smartnus.storage.JsonSmartNusStorage;
import seedu.smartnus.storage.JsonUserPrefsStorage;
import seedu.smartnus.storage.SmartNusStorage;
import seedu.smartnus.storage.Storage;
import seedu.smartnus.storage.StorageManager;
import seedu.smartnus.storage.UserPrefsStorage;
import seedu.smartnus.ui.Ui;
import seedu.smartnus.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, false);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing SmartNus ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        SmartNusStorage smartNusStorage = new JsonSmartNusStorage(userPrefs.getSmartNusFilePath());
        storage = new StorageManager(smartNusStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s SmartNus and {@code userPrefs}. <br>
     * The data from the sample SmartNus will be used instead if {@code storage}'s SmartNus is not found,
     * or an empty SmartNus will be used instead if errors occur when reading {@code storage}'s SmartNus.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlySmartNus> smartNusOptional;
        ReadOnlySmartNus initialData;
        try {
            smartNusOptional = storage.readSmartNus();
            if (!smartNusOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample SmartNus");
            }
            initialData = smartNusOptional.orElseGet(SampleDataUtil::getSampleSmartNus);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty SmartNus");
            initialData = new SmartNus();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty SmartNus");
            initialData = new SmartNus();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty SmartNus");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting SmartNus " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping SmartNus ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
