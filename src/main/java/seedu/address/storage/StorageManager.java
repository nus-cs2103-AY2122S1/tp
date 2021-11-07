package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.storage.archive.ArchiveStorage;
import seedu.address.storage.guest.GuestBookStorage;
import seedu.address.storage.vendor.VendorBookStorage;

/**
 * Manages storage of PH data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private GuestBookStorage guestBookStorage;
    private VendorBookStorage vendorBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private ArchiveStorage archiveStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(GuestBookStorage guestBookStorage, VendorBookStorage vendorBookStorage,
                          UserPrefsStorage userPrefsStorage, ArchiveStorage archiveStorage) {
        super();
        this.guestBookStorage = guestBookStorage;
        this.vendorBookStorage = vendorBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.archiveStorage = archiveStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ GuestBook methods ==============================

    @Override
    public Path getGuestBookFilePath() {
        return guestBookStorage.getGuestBookFilePath();
    }

    @Override
    public Optional<ReadOnlyGuestBook> readGuestBook() throws DataConversionException, IOException {
        return readGuestBook(guestBookStorage.getGuestBookFilePath());
    }

    @Override
    public Optional<ReadOnlyGuestBook> readGuestBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return guestBookStorage.readGuestBook(filePath);
    }

    @Override
    public void saveGuestBook(ReadOnlyGuestBook guestManager) throws IOException {
        saveGuestBook(guestManager, guestBookStorage.getGuestBookFilePath());
    }

    @Override
    public void saveGuestBook(ReadOnlyGuestBook guestManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        guestBookStorage.saveGuestBook(guestManager, filePath);
    }

    // ================ ArchiveStorage methods ==============================

    @Override
    public Path getArchiveFilePath() {
        return archiveStorage.getArchiveFilePath();
    }

    @Override
    public Optional<ReadOnlyGuestBook> readArchive() throws DataConversionException, IOException {
        return readArchive(archiveStorage.getArchiveFilePath());
    }

    @Override
    public Optional<ReadOnlyGuestBook> readArchive(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return archiveStorage.readArchive(filePath);
    }

    @Override
    public void saveArchive(ReadOnlyGuestBook guestManager) throws IOException {
        saveArchive(guestManager, archiveStorage.getArchiveFilePath());
    }

    @Override
    public void saveArchive(ReadOnlyGuestBook guestManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        archiveStorage.saveArchive(guestManager, filePath);
    }

    // ================ VendorBook methods ==============================

    @Override
    public Path getVendorBookFilePath() {
        return vendorBookStorage.getVendorBookFilePath();
    }

    @Override
    public Optional<ReadOnlyVendorBook> readVendorBook() throws DataConversionException, IOException {
        return readVendorBook(vendorBookStorage.getVendorBookFilePath());
    }

    @Override
    public Optional<ReadOnlyVendorBook> readVendorBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return vendorBookStorage.readVendorBook(filePath);
    }

    @Override
    public void saveVendorBook(ReadOnlyVendorBook vendorManager) throws IOException {
        saveVendorBook(vendorManager, vendorBookStorage.getVendorBookFilePath());
    }

    @Override
    public void saveVendorBook(ReadOnlyVendorBook vendorManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        vendorBookStorage.saveVendorBook(vendorManager, filePath);
    }

}
