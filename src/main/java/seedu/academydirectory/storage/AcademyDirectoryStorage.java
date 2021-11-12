package seedu.academydirectory.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.academydirectory.commons.exceptions.DataConversionException;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;

/**
 * Represents a storage for {@link AcademyDirectory}.
 */
public interface AcademyDirectoryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAcademyDirectoryFilePath();

    /**
     * Returns AcademyDirectory data as a {@link ReadOnlyAcademyDirectory}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAcademyDirectory> readAcademyDirectory() throws DataConversionException, IOException;

    /**
     * @see #getAcademyDirectoryFilePath()
     */
    Optional<ReadOnlyAcademyDirectory> readAcademyDirectory(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAcademyDirectory} to the storage.
     * @param academyDirectory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory) throws IOException;

    /**
     * @see #saveAcademyDirectory(ReadOnlyAcademyDirectory)
     */
    void saveAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory, Path filePath) throws IOException;

}
