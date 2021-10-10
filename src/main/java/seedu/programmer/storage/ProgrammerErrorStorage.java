package seedu.programmer.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.programmer.commons.exceptions.DataConversionException;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.ReadOnlyProgrammerError;

/**x
 * Represents a storage for {@link ProgrammerError}.
 */
public interface ProgrammerErrorStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getProgrammerErrorFilePath();

    /**
     * Returns ProgrammerError data as a {@link ReadOnlyProgrammerError}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyProgrammerError> readProgrammerError() throws DataConversionException, IOException;

    /**
     * @see #getProgrammerErrorFilePath()
     */
    Optional<ReadOnlyProgrammerError> readProgrammerError(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyProgrammerError} to the storage.
     * @param programmerError cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveProgrammerError(ReadOnlyProgrammerError programmerError) throws IOException;

    /**
     * @see #saveProgrammerError(ReadOnlyProgrammerError)
     */
    void saveProgrammerError(ReadOnlyProgrammerError addressBook, Path filePath) throws IOException;

}
