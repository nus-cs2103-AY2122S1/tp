package seedu.modulink;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import seedu.modulink.commons.core.Config;
import seedu.modulink.commons.core.LogsCenter;
import seedu.modulink.commons.core.Version;
import seedu.modulink.commons.exceptions.DataConversionException;
import seedu.modulink.commons.util.ConfigUtil;
import seedu.modulink.commons.util.StringUtil;
import seedu.modulink.logic.Logic;
import seedu.modulink.logic.LogicManager;
import seedu.modulink.model.AddressBook;
import seedu.modulink.model.Model;
import seedu.modulink.model.ModelManager;
import seedu.modulink.model.ReadOnlyAddressBook;
import seedu.modulink.model.ReadOnlyUserPrefs;
import seedu.modulink.model.UserPrefs;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.exceptions.DuplicatePersonException;
import seedu.modulink.model.person.exceptions.UserProfileIsFavouriteException;
import seedu.modulink.model.util.SampleDataUtil;
import seedu.modulink.storage.AddressBookStorage;
import seedu.modulink.storage.JsonAddressBookStorage;
import seedu.modulink.storage.JsonUserPrefsStorage;
import seedu.modulink.storage.Storage;
import seedu.modulink.storage.StorageManager;
import seedu.modulink.storage.UserPrefsStorage;
import seedu.modulink.ui.Ui;
import seedu.modulink.ui.UiManager;

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

        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        storage = new StorageManager(addressBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        try {
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
            Model currentData = new ModelManager(initialData, userPrefs);
            ObservableList<Person> allPersons = currentData.getFilteredPersonList();
            int noOfPeople = allPersons.size();
            for (int i = 0; i < noOfPeople; i++) {
                StudentId currentId = allPersons.get(i).getStudentId();
                if (allPersons.get(i).getIsMyProfile() && allPersons.get(i).getIsFavourite()) {
                    throw new UserProfileIsFavouriteException();
                }
                for (int j = i + 1; j < noOfPeople; j++) {
                    StudentId comparingId = allPersons.get(j).getStudentId();
                    if (currentId.equals(comparingId)) {
                        throw new DuplicatePersonException();
                    }
                }
            }
        } catch (UserProfileIsFavouriteException err) {
            logger.warning("Data file has a profile that is both the User Profile and a favourited profile.");
            initialData = new AddressBook();
        }
        catch (DuplicatePersonException err) {
            logger.warning("Data file consists of duplicate StudentIds. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
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
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
