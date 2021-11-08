package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Account;
import seedu.address.model.EzFoodie;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AccountStorage;
import seedu.address.storage.EzFoodieStorage;
import seedu.address.storage.JsonAccountStorage;
import seedu.address.storage.JsonEzFoodieStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    /**
     * Creates a version.
     */
    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    /**
     * Overrides and initializes rhe ezFoodie application.
     *
     * @throws Exception if the user input does not conform the expected format.
     */
    @Override
    public void init() throws Exception {
        logger.info("==============================[ Initializing ezFoodie ]=============================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AccountStorage accountStorage = new JsonAccountStorage(userPrefs.getAccountFilePath());
        EzFoodieStorage ezFoodieStorage = new JsonEzFoodieStorage(userPrefs.getEzFoodieFilePath());
        storage = new StorageManager(accountStorage, ezFoodieStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * The data from the sample ezFoodie will be used instead if {@code storage}'s account is not found,
     * or an empty account will be used instead if errors occur when reading {@code storage}'s account.
     */
    private ReadOnlyAccount initAccount(Storage storage) {
        Optional<ReadOnlyAccount> accountOptional;
        ReadOnlyAccount initialData;
        try {
            accountOptional = storage.readAccount();
            if (!accountOptional.isPresent()) {
                logger.info("Account file not found. Will be starting with a sample account");
            }
            initialData = accountOptional.orElseGet(SampleDataUtil::getDefaultPassword);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ezFoodie");
            initialData = new Account();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ezFoodie");
            initialData = new Account();
        }

        //Update account file in case it was missing to begin with or there are any new updates.
        try {
            storage.saveAccount(initialData);
        } catch (IOException e) {
            logger.warning("Failed to save account file : " + StringUtil.getDetails(e));
        }

        return initialData;
    }

    /**
     * The data from the sample ezFoodie will be used instead if {@code storage}'s ezFoodie is not found,
     * or an empty ezFoodie will be used instead if errors occur when reading {@code storage}'s ezFoodie.
     */
    private ReadOnlyEzFoodie initEzFoodie(Storage storage) {
        Optional<ReadOnlyEzFoodie> ezFoodieOptional;
        ReadOnlyEzFoodie initialData;
        try {
            ezFoodieOptional = storage.readEzFoodie();
            if (!ezFoodieOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ezFoodie");
            }
            initialData = ezFoodieOptional.orElseGet(SampleDataUtil::getSampleEzFoodie);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty ezFoodie");
            initialData = new EzFoodie();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty ezFoodie");
            initialData = new EzFoodie();
        }

        return initialData;
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s account and ezFoodie
     * and {@code userPrefs}.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        ReadOnlyAccount initAccount = initAccount(storage);
        ReadOnlyEzFoodie initEzFoodie = initEzFoodie(storage);

        return new ModelManager(initAccount, initEzFoodie, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ezFoodie");
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

    /**
     * Starts main application with input {@code primaryStage}.
     */
    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting ezFoodie " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    /**
     * Stops main application.
     */
    @Override
    public void stop() {
        logger.info("=============================== [ Stopping ezFoodie ] ==============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
