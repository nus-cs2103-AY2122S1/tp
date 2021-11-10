package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyCountdown;

/**
 * Represents a storage for {@link seedu.address.model.Countdown}.
 */
public interface CountdownStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCountdownFilePath();

    /**
     * Returns Countdown data as a {@link ReadOnlyCountdown}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCountdown> readCountdown() throws DataConversionException, IOException;

    /**
     * @see #getCountdownFilePath()
     */
    Optional<ReadOnlyCountdown> readCountdown(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCountdown} to the storage.
     * @param countdown cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCountdown(ReadOnlyCountdown countdown) throws IOException;

    /**
     * @see #saveCountdown(ReadOnlyCountdown)
     */
    void saveCountdown(ReadOnlyCountdown countdown, Path filePath) throws IOException;

}
