package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAccount;

/**
 * Represents a storage for {@link seedu.address.model.Account}.
 */
public interface AccountStorage {

    /**
     * Returns the file path of the account file.
     */
    Path getAccountFilePath();

    /**
     * Returns Account as a {@link ReadOnlyAccount}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAccount> readAccount() throws DataConversionException, IOException;

    /**
     * @see #getAccountFilePath()
     */
    Optional<ReadOnlyAccount> readAccount(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAccount} to the storage.
     *
     * @param account cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAccount(ReadOnlyAccount account) throws IOException;

    /**
     * @see #saveAccount(ReadOnlyAccount)
     */
    void saveAccount(ReadOnlyAccount account, Path filePath) throws IOException;

}
