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
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.VendorBook;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.storage.archive.ArchiveStorage;
import seedu.address.storage.archive.JsonArchiveStorage;
import seedu.address.storage.guest.GuestBookStorage;
import seedu.address.storage.guest.JsonGuestBookStorage;
import seedu.address.storage.vendor.JsonVendorBookStorage;
import seedu.address.storage.vendor.VendorBookStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Pocket Hotel ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        GuestBookStorage guestBookStorage = new JsonGuestBookStorage(userPrefs.getGuestBookFilePath());
        VendorBookStorage vendorBookStorage = new JsonVendorBookStorage(userPrefs.getVendorBookFilePath());
        ArchiveStorage archiveStorage = new JsonArchiveStorage(userPrefs.getArchiveFilePath());
        storage = new StorageManager(guestBookStorage, vendorBookStorage, userPrefsStorage, archiveStorage);

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
        ReadOnlyGuestBook archive = initArchive(storage);
        ReadOnlyGuestBook guestManager = initGuestBook(storage, archive);
        ReadOnlyVendorBook vendorManager = initVendorBook(storage);
        return new ModelManager(guestManager, vendorManager, userPrefs, archive);
    }

    /**
     * Returns a {@code ReadOnlyGuestBook} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private ReadOnlyGuestBook initGuestBook(Storage storage, ReadOnlyGuestBook archive) {
        ReadOnlyGuestBook initialData;
        try {
            Optional<ReadOnlyGuestBook> guestBookOptional = storage.readGuestBook();

            if (!guestBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample GuestBook");
            }

            initialData = guestBookOptional.orElseGet(() -> SampleDataUtil.getSampleGuestBook());

            ReadOnlyGuestBook verifiedGuestBook = new GuestBook();

            for (Guest guest : initialData.getGuestList()) {
                boolean containPassportNumber = false;
                for (Guest archivedGuest : archive.getGuestList()) {
                    if (archivedGuest.getPassportNumber().equals(guest.getPassportNumber())) {
                        containPassportNumber = true;
                    }
                }
                if (!containPassportNumber) {
                    verifiedGuestBook.getGuestList().add(guest);
                }
            }

            initialData = verifiedGuestBook;

            if (initialData.getGuestList().size() == 0) {
                logger.info("Passport numbers of all sample guests are used in the archive or data file has been "
                        + "corrupted" + "Will be starting with an empty GuestBook");
            }
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty GuestBook");
            initialData = new GuestBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty GuestBook");
            initialData = new GuestBook();
        }

        return initialData;
    }

    private ReadOnlyGuestBook initArchive(Storage storage) {
        ReadOnlyGuestBook initialData = new Archive();
        try {
            Optional<ReadOnlyGuestBook> guestBookOptional = storage.readArchive();

            initialData = guestBookOptional.orElseGet(SampleDataUtil::getSampleArchive);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty archive");
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty archive");
        }

        return initialData;
    }

    /**
     * Returns a {@code ReadOnlyGuestBook} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private ReadOnlyVendorBook initVendorBook(Storage storage) {
        ReadOnlyVendorBook initialData;
        try {
            Optional<ReadOnlyVendorBook> vendorBookOptional = storage.readVendorBook();
            if (!vendorBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample VendorBook");
            }
            initialData = vendorBookOptional.orElseGet(SampleDataUtil::getSampleVendorBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty VendorBook");
            initialData = new VendorBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty VendorBook");
            initialData = new VendorBook();
        }
        return initialData;
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
        logger.info("Starting Pocket Hotel " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Pocket Hotel ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
