package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRhrh;
import seedu.address.model.Rhrh;

/**
 * Represents a storage for {@link Rhrh}.
 */
public interface RhrhStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRhrhFilePath();

    /**
     * Returns Rhrh data as a {@link ReadOnlyRhrh}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyRhrh> readRhrh() throws DataConversionException, IOException;

    /**
     * @see #getRhrhFilePath()
     */
    Optional<ReadOnlyRhrh> readRhrh(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRhrh} to the storage.
     * @param rhrh cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRhrh(ReadOnlyRhrh rhrh) throws IOException;

    /**
     * @see #saveRhrh(ReadOnlyRhrh)
     */
    void saveRhrh(ReadOnlyRhrh rhrh, Path filePath) throws IOException;

}
