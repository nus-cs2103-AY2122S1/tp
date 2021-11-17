package socialite;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import socialite.commons.core.Config;
import socialite.commons.core.LogsCenter;
import socialite.commons.core.Version;
import socialite.commons.exceptions.DataConversionException;
import socialite.commons.util.ConfigUtil;
import socialite.commons.util.StringUtil;
import socialite.logic.Logic;
import socialite.logic.LogicManager;
import socialite.model.CommandHistory;
import socialite.model.ContactList;
import socialite.model.Model;
import socialite.model.ModelManager;
import socialite.model.ReadOnlyCommandHistory;
import socialite.model.ReadOnlyContactList;
import socialite.model.ReadOnlyUserPrefs;
import socialite.model.UserPrefs;
import socialite.model.util.SampleDataUtil;
import socialite.storage.CommandHistoryStorage;
import socialite.storage.ContactListStorage;
import socialite.storage.JsonCommandHistoryStorage;
import socialite.storage.JsonContactListStorage;
import socialite.storage.JsonUserPrefsStorage;
import socialite.storage.ProfilePictureStorage;
import socialite.storage.ProfilePictureStorageManager;
import socialite.storage.Storage;
import socialite.storage.StorageManager;
import socialite.storage.UserPrefsStorage;
import socialite.ui.Ui;
import socialite.ui.UiManager;

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
        logger.info("=============================[ Initializing SociaLite ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ContactListStorage contactListStorage = new JsonContactListStorage(userPrefs.getContactListFilePath());
        CommandHistoryStorage commandHistoryStorage =
                new JsonCommandHistoryStorage(userPrefs.getCommandHistoryFilePath());
        ProfilePictureStorage profilePictureStorage = ProfilePictureStorageManager.getInstance();
        storage = new StorageManager(
                contactListStorage, userPrefsStorage, commandHistoryStorage, profilePictureStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s contact list and {@code userPrefs}. <br>
     * The data from the sample contact list will be used instead if {@code storage}'s contact list is not found,
     * or an empty contact list will be used instead if errors occur when reading {@code storage}'s contact list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyContactList> contactListOptional;
        ReadOnlyContactList initialContactList;
        Optional<ReadOnlyCommandHistory> commandHistoryOptional;
        ReadOnlyCommandHistory initialCommandHistory;
        try {
            contactListOptional = storage.readContactList();
            if (contactListOptional.isEmpty()) {
                logger.info("ContactList file not found. Will be starting with a sample ContactList");
            }
            initialContactList = contactListOptional.orElseGet(SampleDataUtil::getSampleContactList);
        } catch (DataConversionException e) {
            logger.warning("ContactList file not in the correct format. Will be starting with an empty ContactList");
            initialContactList = new ContactList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the ContactList. Will be starting with an empty ContactList");
            initialContactList = new ContactList();
        }

        try {
            commandHistoryOptional = storage.readCommandHistory();
            if (commandHistoryOptional.isEmpty()) {
                logger.info("CommandHistory file not found. Will be starting with a new CommandHistory");
            }
            initialCommandHistory = commandHistoryOptional.orElseGet(SampleDataUtil::getSampleCommandHistory);
        } catch (DataConversionException e) {
            logger.warning(
                    "CommandHistory file not in the correct format. Will be starting with an empty CommandHistory");
            initialCommandHistory = new CommandHistory();
        } catch (IOException e) {
            logger.warning(
                    "Problem while reading from the CommandHistory. Will be starting with an empty CommandHistory");
            initialCommandHistory = new CommandHistory();
        }

        return new ModelManager(initialContactList, userPrefs, initialCommandHistory);
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

        // Update config file in case it was missing to begin with or there are new/unused fields
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ContactList");
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
        logger.info("Starting SociaLite " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping SociaLite ] =============================");
        try {
            storage.saveContactList(model.getContactList());
            storage.saveCommandHistory(model.getCommandHistory());
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
