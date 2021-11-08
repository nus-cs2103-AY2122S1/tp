package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component.
 */
public interface Storage extends EzFoodieStorage, AccountStorage, UserPrefsStorage {

    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyUserPrefs} to the storage.
     *
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    /**
     * Returns Account as a {@link ReadOnlyAccount}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    Optional<ReadOnlyAccount> readAccount() throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAccount} to the storage.
     *
     * @param account cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    void saveAccount(ReadOnlyAccount account) throws IOException;

    /**
     * Returns the file path of the data file.
     */
    @Override
    Path getEzFoodieFilePath();

    /**
     * Returns ezFoodie data as a {@link ReadOnlyEzFoodie}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    Optional<ReadOnlyEzFoodie> readEzFoodie() throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEzFoodie} to the storage.
     *
     * @param ezFoodie cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    void saveEzFoodie(ReadOnlyEzFoodie ezFoodie) throws IOException;

}
