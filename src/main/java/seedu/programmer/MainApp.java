package seedu.programmer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.programmer.commons.core.Config;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.commons.core.Version;
import seedu.programmer.commons.exceptions.DataConversionException;
import seedu.programmer.commons.util.ConfigUtil;
import seedu.programmer.commons.util.StringUtil;
import seedu.programmer.logic.Logic;
import seedu.programmer.logic.LogicManager;
import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.ReadOnlyUserPrefs;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.util.SampleDataUtil;
import seedu.programmer.storage.JsonProgrammerErrorStorage;
import seedu.programmer.storage.JsonUserPrefsStorage;
import seedu.programmer.storage.ProgrammerErrorStorage;
import seedu.programmer.storage.Storage;
import seedu.programmer.storage.StorageManager;
import seedu.programmer.storage.UserPrefsStorage;
import seedu.programmer.ui.Ui;
import seedu.programmer.ui.UiManager;

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
        logger.info("=============================[ Initializing ProgrammerError ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ProgrammerErrorStorage programmerErrorStorage = new JsonProgrammerErrorStorage(
                userPrefs.getProgrammerErrorFilePath());
        storage = new StorageManager(programmerErrorStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s ProgrammerError and {@code userPrefs}. <br>
     * The data from the sample ProgrammerError will be used instead if {@code storage}'s ProgrammerError is not found,
     * or an empty ProgrammerError will be used instead if errors occur when reading {@code storage}'s ProgrammerError.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyProgrammerError> programmerErrorOptional;
        ReadOnlyProgrammerError initialData;
        try {
            programmerErrorOptional = storage.readProgrammerError();
            if (programmerErrorOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample ProgrammerError");
            }
            initialData = programmerErrorOptional.orElseGet(SampleDataUtil::getSampleProgrammerError);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ProgrammerError");
            initialData = new ProgrammerError();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ProgrammerError");
            initialData = new ProgrammerError();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ProgrammerError");
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
        logger.info("Starting ProgrammerError " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping ProgrammerError ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
