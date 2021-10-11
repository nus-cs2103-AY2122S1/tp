package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyStudentBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of StudentBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TutorAidStorage tutorAidStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TutorAidStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TutorAidStorage tutorAidStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.tutorAidStorage = tutorAidStorage;
        this.userPrefsStorage = userPrefsStorage;
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


    // ================ StudentBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return tutorAidStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyStudentBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(tutorAidStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyStudentBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tutorAidStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyStudentBook addressBook) throws IOException {
        saveAddressBook(addressBook, tutorAidStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyStudentBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        tutorAidStorage.saveAddressBook(addressBook, filePath);
    }

}
