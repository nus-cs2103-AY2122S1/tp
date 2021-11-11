package seedu.intern.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.intern.commons.exceptions.DataConversionException;
import seedu.intern.model.InternWatcher;
import seedu.intern.model.ReadOnlyInternWatcher;

/**
 * Represents a storage for {@link InternWatcher}.
 */
public interface InternWatcherStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternWatcherFilePath();

    /**
     * Returns InternWatcher data as a {@link ReadOnlyInternWatcher}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInternWatcher> readInternWatcher() throws DataConversionException, IOException;

    /**
     * @see #getInternWatcherFilePath()
     */
    Optional<ReadOnlyInternWatcher> readInternWatcher(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInternWatcher} to the storage.
     * @param internWatcher cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternWatcher(ReadOnlyInternWatcher internWatcher) throws IOException;

    /**
     * @see #saveInternWatcher(ReadOnlyInternWatcher)
     */
    void saveInternWatcher(ReadOnlyInternWatcher internWatcher, Path filePath) throws IOException;

}
