package seedu.fast.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fast.commons.exceptions.DataConversionException;
import seedu.fast.model.Fast;
import seedu.fast.model.ReadOnlyFast;

/**
 * Represents a storage for {@link Fast}.
 */
public interface FastStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFastFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyFast}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFast> readFast() throws DataConversionException, IOException;

    /**
     * @see #getFastFilePath()
     */
    Optional<ReadOnlyFast> readFast(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFast} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFast(ReadOnlyFast addressBook) throws IOException;

    /**
     * @see #saveFast(ReadOnlyFast)
     */
    void saveFast(ReadOnlyFast addressBook, Path filePath) throws IOException;

}
