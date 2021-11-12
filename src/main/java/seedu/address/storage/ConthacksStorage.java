package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Conthacks;
import seedu.address.model.ReadOnlyConthacks;

/**
 * Represents a storage for {@link Conthacks}.
 */
public interface ConthacksStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getConthacksFilePath();

    /**
     * Returns Conthacks data as a {@link ReadOnlyConthacks}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyConthacks> readConthacks() throws DataConversionException, IOException;

    /**
     * @see #getConthacksFilePath()
     */
    Optional<ReadOnlyConthacks> readConthacks(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyConthacks} to the storage.
     * @param conthacks cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveConthacks(ReadOnlyConthacks conthacks) throws IOException;

    /**
     * @see #saveConthacks(ReadOnlyConthacks)
     */
    void saveConthacks(ReadOnlyConthacks conthacks, Path filePath) throws IOException;

}
