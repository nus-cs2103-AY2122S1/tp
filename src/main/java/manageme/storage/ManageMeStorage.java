package manageme.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import manageme.commons.exceptions.DataConversionException;
import manageme.model.ManageMe;
import manageme.model.ReadOnlyManageMe;

/**
 * Represents a storage for {@link ManageMe}.
 */
public interface ManageMeStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getManageMeFilePath();

    /**
     * Returns ManageMe data as a {@link ReadOnlyManageMe}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyManageMe> readManageMe() throws DataConversionException, IOException;

    /**
     * @see #getManageMeFilePath()
     */
    Optional<ReadOnlyManageMe> readManageMe(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyManageMe} to the storage.
     * @param manageMe cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveManageMe(ReadOnlyManageMe manageMe) throws IOException;

    /**
     * @see #saveManageMe(ReadOnlyManageMe)
     */
    void saveManageMe(ReadOnlyManageMe manageMe, Path filePath) throws IOException;

}
