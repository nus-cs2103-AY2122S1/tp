package seedu.edrecord;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.edrecord.commons.core.Config;
import seedu.edrecord.commons.core.LogsCenter;
import seedu.edrecord.commons.core.Version;
import seedu.edrecord.commons.exceptions.DataConversionException;
import seedu.edrecord.commons.util.ConfigUtil;
import seedu.edrecord.commons.util.StringUtil;
import seedu.edrecord.logic.Logic;
import seedu.edrecord.logic.LogicManager;
import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.ModelManager;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.ReadOnlyUserPrefs;
import seedu.edrecord.model.UserPrefs;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.model.util.SampleDataUtil;
import seedu.edrecord.storage.EdRecordStorage;
import seedu.edrecord.storage.JsonEdRecordStorage;
import seedu.edrecord.storage.JsonUserPrefsStorage;
import seedu.edrecord.storage.ModuleSystemStorage;
import seedu.edrecord.storage.Storage;
import seedu.edrecord.storage.StorageManager;
import seedu.edrecord.storage.UserPrefsStorage;
import seedu.edrecord.storage.module.JsonModuleSystemStorage;
import seedu.edrecord.ui.Ui;
import seedu.edrecord.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing EdRecord ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        EdRecordStorage edRecordStorage = new JsonEdRecordStorage(userPrefs.getEdRecordFilePath());
        ModuleSystemStorage moduleSystemStorage = new JsonModuleSystemStorage(userPrefs.getModuleSystemFilePath());
        storage = new StorageManager(edRecordStorage, moduleSystemStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s edrecord, module system
     * and {@code userPrefs}. <br>
     * The data from the sample edrecord will be used instead if {@code storage}'s edrecord is not found,
     * or an empty edrecord will be used instead if errors occur when reading {@code storage}'s edrecord.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {

        Optional<ReadOnlyModuleSystem> moduleSystemOptional;
        ReadOnlyModuleSystem initialModuleData;
        try {
            moduleSystemOptional = storage.readModuleSystem();
            if (!moduleSystemOptional.isPresent()) {
                logger.info("Module data file not found. Will be starting with a sample modules");
            }
            initialModuleData = moduleSystemOptional.orElseGet(SampleDataUtil::getSampleModuleSystem);
            initialModuleData = new ModuleSystem(initialModuleData);
        } catch (DataConversionException e) {
            logger.warning("Module data file not in the correct format. Will be starting with "
                    + "an empty ModuleSystem");
            initialModuleData = Module.MODULE_SYSTEM;
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ModuleSystem");
            initialModuleData = Module.MODULE_SYSTEM;
        }

        Optional<ReadOnlyEdRecord> edRecordOptional;
        ReadOnlyEdRecord initialData;
        try {
            edRecordOptional = storage.readEdRecord();
            if (!edRecordOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample instance of EdRecord");
            }
            initialData = edRecordOptional.orElseGet(SampleDataUtil::getSampleEdRecord);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty instance of EdRecord");
            initialData = new EdRecord();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty instance of EdRecord");
            initialData = new EdRecord();
        }


        return new ModelManager(initialData, initialModuleData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty EdRecord");
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
        logger.info("Starting EdRecord " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Ed Record ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
