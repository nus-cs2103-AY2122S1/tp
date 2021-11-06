package seedu.notor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.notor.commons.core.Config;
import seedu.notor.commons.core.LogsCenter;
import seedu.notor.commons.core.Version;
import seedu.notor.commons.core.trie.Trie;
import seedu.notor.commons.exceptions.DataConversionException;
import seedu.notor.commons.util.ConfigUtil;
import seedu.notor.commons.util.StringUtil;
import seedu.notor.logic.Logic;
import seedu.notor.logic.LogicManager;
import seedu.notor.logic.executors.Executor;
import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.Notor;
import seedu.notor.model.ReadOnlyNotor;
import seedu.notor.model.ReadOnlyUserPrefs;
import seedu.notor.model.UserPrefs;
import seedu.notor.model.util.SampleDataUtil;
import seedu.notor.storage.JsonNotorStorage;
import seedu.notor.storage.JsonUserPrefsStorage;
import seedu.notor.storage.NotorStorage;
import seedu.notor.storage.Storage;
import seedu.notor.storage.StorageManager;
import seedu.notor.storage.UserPrefsStorage;
import seedu.notor.ui.Ui;
import seedu.notor.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;


    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Notor ]===========================");
        super.init();

        Trie.setup();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        NotorStorage notorStorage = new JsonNotorStorage(userPrefs.getNotorFilePath());
        storage = new StorageManager(notorStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic, model);

        Executor.setup(model);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Notor and {@code userPrefs}. <br>
     * The data from the sample Notor will be used instead if {@code storage}'s notor is not found,
     * or an empty Notor will be used instead if errors occur when reading {@code storage}'s notor.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyNotor> notorOptional;
        ReadOnlyNotor initialData;
        try {
            notorOptional = storage.readNotor();
            if (notorOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample Notor");
            }
            initialData = notorOptional.orElseGet(SampleDataUtil::getSampleNotor);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Notor.");
            initialData = new Notor();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Notor.");
            initialData = new Notor();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Notor");
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
        logger.info("Starting Notor " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Notor ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
