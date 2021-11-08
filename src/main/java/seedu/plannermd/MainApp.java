package seedu.plannermd;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.plannermd.commons.core.Config;
import seedu.plannermd.commons.core.LogsCenter;
import seedu.plannermd.commons.core.Version;
import seedu.plannermd.commons.exceptions.DataConversionException;
import seedu.plannermd.commons.util.ConfigUtil;
import seedu.plannermd.commons.util.StringUtil;
import seedu.plannermd.logic.Logic;
import seedu.plannermd.logic.LogicManager;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.ReadOnlyUserPrefs;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.util.SampleDataUtil;
import seedu.plannermd.storage.JsonPlannerMdStorage;
import seedu.plannermd.storage.JsonUserPrefsStorage;
import seedu.plannermd.storage.PlannerMdStorage;
import seedu.plannermd.storage.Storage;
import seedu.plannermd.storage.StorageManager;
import seedu.plannermd.storage.UserPrefsStorage;
import seedu.plannermd.ui.Ui;
import seedu.plannermd.ui.UiManager;

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
        logger.info("=============================[ Initializing PlannerMD ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        PlannerMdStorage plannerMdStorage = new JsonPlannerMdStorage(userPrefs.getPlannerMdFilePath());
        storage = new StorageManager(plannerMdStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s plannermd and {@code userPrefs}. <br>
     * The data from the sample plannermd will be used instead if {@code storage}'s plannermd is not found,
     * or an empty plannermd will be used instead if errors occur when reading {@code storage}'s plannermd.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyPlannerMd> plannerMdOptional;
        ReadOnlyPlannerMd initialData;
        try {
            plannerMdOptional = storage.readPlannerMd();
            if (!plannerMdOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample PlannerMd");
            }
            initialData = plannerMdOptional.orElseGet(SampleDataUtil::getSamplePlannerMd);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty PlannerMd");
            initialData = new PlannerMd();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PlannerMd");
            initialData = new PlannerMd();
        }

        Model model = new ModelManager(initialData, userPrefs);
        model.updateFilteredAppointmentList(Model.PREDICATE_SHOW_TODAY_APPOINTMENT);
        return model;
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
            logger.warning("Problem while reading from the file. Will be starting with an empty PlannerMd");
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
        logger.info("Starting PlannerMd " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping PlannerMD ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
