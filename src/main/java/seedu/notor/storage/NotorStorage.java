package seedu.notor.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.notor.commons.exceptions.DataConversionException;
import seedu.notor.model.Notor;
import seedu.notor.model.ReadOnlyNotor;

/**
 * Represents a storage for {@link Notor}.
 */
public interface NotorStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getNotorFilePath();

    /**
     * Returns Notor data as a {@link ReadOnlyNotor}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyNotor> readNotor() throws DataConversionException, IOException;

    /**
     * @see #getNotorFilePath()
     */
    Optional<ReadOnlyNotor> readNotor(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyNotor} to the storage.
     *
     * @param notor cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNotor(ReadOnlyNotor notor) throws IOException;

    /**
     * @see #saveNotor(ReadOnlyNotor)
     */
    void saveNotor(ReadOnlyNotor notor, Path filePath) throws IOException;

}
