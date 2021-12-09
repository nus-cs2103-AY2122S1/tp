package seedu.times.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.times.commons.exceptions.DataConversionException;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.TimesTable;

/**
 * Represents a storage for {@link TimesTable}.
 */
public interface TimesTableStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTimesTableFilePath();

    /**
     * Returns TimeTable data as a {@link ReadOnlyTimesTable}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTimesTable> readTimesTable() throws DataConversionException, IOException;

    /**
     * @see #getTimesTableFilePath()
     */
    Optional<ReadOnlyTimesTable> readTimesTable(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTimesTable} to the storage.
     * @param timesTable cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTimesTable(ReadOnlyTimesTable timesTable) throws IOException;

    /**
     * @see #saveTimesTable(ReadOnlyTimesTable)
     */
    void saveTimesTable(ReadOnlyTimesTable timesTable, Path filePath) throws IOException;

}
