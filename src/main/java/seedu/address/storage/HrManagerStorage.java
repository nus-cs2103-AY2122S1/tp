package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;

/**
 * Represents a storage for {@link HrManager}.
 */
public interface HrManagerStorage {

    /**
     * Returns the file path of the data file for candidates.
     */
    Path getHrManagerCandidatesFilePath();

    /**
     * Returns HR Manager data as a {@link ReadOnlyHrManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHrManager> readHrManager() throws DataConversionException, IOException;

    /**
     * @see #getHrManagerCandidatesFilePath()
     */
    Optional<ReadOnlyHrManager> readHrManager(Path candidatesFilePath,
                                              Path positionsFilePath,
                                              Path interviewsFilePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHrManager} to the storage.
     * @param hrManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHrManager(ReadOnlyHrManager hrManager) throws IOException;

    /**
     * @see #saveHrManager(ReadOnlyHrManager)
     */
    void saveHrManager(ReadOnlyHrManager hrManager, Path candidatesFilePath, Path positionsFilePath,
                       Path interviewsFilePath) throws IOException;

    /**
     * Returns the file path of the data file for Positions.
     */
    Path getHrManagerPositionsFilePath();

    /**
     * Returns the file path of the data file for Interviews.
     */
    Path getHrManagerInterviewsFilePath();

}
