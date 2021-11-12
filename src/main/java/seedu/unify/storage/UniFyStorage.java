package seedu.unify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.unify.commons.exceptions.DataConversionException;
import seedu.unify.model.ReadOnlyUniFy;

/**
 * Represents a storage for {@link seedu.unify.model.UniFy}.
 */
public interface UniFyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUniFyFilePath();

    /**
     * Returns UniFy data as a {@link seedu.unify.model.ReadOnlyUniFy}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUniFy> readUniFy() throws DataConversionException, IOException;

    /**
     * @see #getUniFyFilePath()
     */
    Optional<ReadOnlyUniFy> readUniFy(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.unify.model.ReadOnlyUniFy} to the storage.
     * @param uniFy cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUniFy(ReadOnlyUniFy uniFy) throws IOException;

    /**
     * @see #saveUniFy(seedu.unify.model.ReadOnlyUniFy)
     */
    void saveUniFy(ReadOnlyUniFy uniFy, Path filePath) throws IOException;

}
