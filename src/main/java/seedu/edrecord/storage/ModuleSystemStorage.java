package seedu.edrecord.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.edrecord.commons.exceptions.DataConversionException;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;

/**
 * Represents a storage for {@link ModuleSystem}.
 */
public interface ModuleSystemStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getModuleSystemFilePath();

    /**
     * Returns ModuleSystem data as a {@link ReadOnlyModuleSystem}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModuleSystem> readModuleSystem() throws DataConversionException, IOException;

    /**
     * @see #getModuleSystemFilePath()
     */
    Optional<ReadOnlyModuleSystem> readModuleSystem(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyModuleSystem} to the storage.
     * @param moduleSystem cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveModuleSystem(ReadOnlyModuleSystem moduleSystem) throws IOException;

    /**
     * @see #saveModuleSystem(ReadOnlyModuleSystem)
     */
    void saveModuleSystem(ReadOnlyModuleSystem moduleSystem, Path filePath) throws IOException;

}
