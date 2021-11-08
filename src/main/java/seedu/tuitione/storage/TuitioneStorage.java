package seedu.tuitione.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.tuitione.commons.exceptions.DataConversionException;
import seedu.tuitione.model.ReadOnlyTuitione;

/**
 * Represents a storage for {@link seedu.tuitione.model.Tuitione}.
 */
public interface TuitioneStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTuitioneFilePath();

    /**
     * Returns Tuitione data as a {@link ReadOnlyTuitione}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTuitione> readTuitione() throws DataConversionException, IOException;

    /**
     * @see #getTuitioneFilePath()
     */
    Optional<ReadOnlyTuitione> readTuitione(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTuitione} to the storage.
     * @param tuitione cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTuitione(ReadOnlyTuitione tuitione) throws IOException;

    /**
     * @see #saveTuitione(ReadOnlyTuitione)
     */
    void saveTuitione(ReadOnlyTuitione tuitione, Path filePath) throws IOException;

}
