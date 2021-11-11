package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Internship;
import seedu.address.model.ReadOnlyInternship;

/**
 * Represents a storage for {@link Internship}.
 */
public interface InternshipStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternshipFilePath();

    /**
     * Returns Internship data as a {@link ReadOnlyInternship}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInternship> readInternship() throws DataConversionException, IOException;

    /**
     * @see #getInternshipFilePath()
     */
    Optional<ReadOnlyInternship> readInternship(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInternship} to the storage.
     * @param internship cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternship(ReadOnlyInternship internship) throws IOException;

    /**
     * @see #saveInternship(ReadOnlyInternship)
     */
    void saveInternship(ReadOnlyInternship internship, Path filePath) throws IOException;

}
