package seedu.track2gather;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.track2gather.commons.core.Config;
import seedu.track2gather.commons.core.LogsCenter;
import seedu.track2gather.commons.core.Version;
import seedu.track2gather.commons.exceptions.DataConversionException;
import seedu.track2gather.commons.util.ConfigUtil;
import seedu.track2gather.commons.util.StringUtil;
import seedu.track2gather.logic.Logic;
import seedu.track2gather.logic.LogicManager;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.ReadOnlyUserPrefs;
import seedu.track2gather.model.Track2Gather;
import seedu.track2gather.model.UserPrefs;
import seedu.track2gather.model.util.SampleDataUtil;
import seedu.track2gather.storage.JsonTrack2GatherStorage;
import seedu.track2gather.storage.JsonUserPrefsStorage;
import seedu.track2gather.storage.Storage;
import seedu.track2gather.storage.StorageManager;
import seedu.track2gather.storage.Track2GatherStorage;
import seedu.track2gather.storage.UserPrefsStorage;
import seedu.track2gather.ui.Ui;
import seedu.track2gather.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Track2Gather ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        Track2GatherStorage track2GatherStorage = new JsonTrack2GatherStorage(userPrefs.getTrack2GatherFilePath());
        storage = new StorageManager(track2GatherStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s contacts list and {@code userPrefs}. <br>
     * The data from the sample contacts list will be used instead if {@code storage}'s contacts list is not found,
     * or an empty contacts list will be used instead if errors occur when reading {@code storage}'s contacts list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTrack2Gather> track2GatherOptional;
        ReadOnlyTrack2Gather initialData;
        try {
            track2GatherOptional = storage.readTrack2Gather();
            if (!track2GatherOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample Track2Gather");
            }
            initialData = track2GatherOptional.orElseGet(SampleDataUtil::getSampleTrack2Gather);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Track2Gather");
            initialData = new Track2Gather();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Track2Gather");
            initialData = new Track2Gather();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Track2Gather");
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
        logger.info("Starting Track2Gather " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Track2Gather ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
