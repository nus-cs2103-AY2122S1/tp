package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyShortcut;

/**
 * Represents a storage for {@link seedu.address.model.Shortcut}.
 */
public interface ShortcutStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getShortcutFilePath();

    /**
     * Returns Shortcut data as a {@link ReadOnlyShortcut}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyShortcut> readShortcut() throws DataConversionException, IOException;

    /**
     * @see #getShortcutFilePath()
     */
    Optional<ReadOnlyShortcut> readShortcut(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyShortcut} to the storage.
     * @param shortcut cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveShortcut(ReadOnlyShortcut shortcut) throws IOException;

    /**
     * @see #saveShortcut(ReadOnlyShortcut)
     */
    void saveShortcut(ReadOnlyShortcut shortcut, Path filePath) throws IOException;

}
