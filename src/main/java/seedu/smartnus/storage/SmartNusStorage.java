package seedu.smartnus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.smartnus.commons.exceptions.DataConversionException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.SmartNus;

/**
 * Represents a storage for {@link SmartNus}.
 */
public interface SmartNusStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSmartNusFilePath();

    /**
     * Returns SmartNus data as a {@link ReadOnlySmartNus}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySmartNus> readSmartNus() throws DataConversionException, IOException;

    /**
     * @see #getSmartNusFilePath()
     */
    Optional<ReadOnlySmartNus> readSmartNus(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySmartNus} to the storage.
     * @param smartNus cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSmartNus(ReadOnlySmartNus smartNus) throws IOException;

    /**
     * @see #saveSmartNus(ReadOnlySmartNus)
     */
    void saveSmartNus(ReadOnlySmartNus smartNus, Path filePath) throws IOException;

}
