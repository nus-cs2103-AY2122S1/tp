package seedu.siasa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.siasa.commons.exceptions.DataConversionException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.Siasa;

/**
 * Represents a policy storage for {@link Siasa}.
 */

public interface PolicyBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPolicyBookFilePath();

    /**
     * Returns PolicyBook data as a {@link ReadOnlySiasa}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySiasa> readPolicyBook() throws DataConversionException, IOException;

    /**
     * @see #getPolicyBookFilePath()
     */
    Optional<ReadOnlySiasa> readPolicyBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySiasa} to the storage.
     *
     * @param policyBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePolicyBook(ReadOnlySiasa policyBook) throws IOException;

    /**
     * @see #savePolicyBook(ReadOnlySiasa)
     */
    void savePolicyBook(ReadOnlySiasa policyBook, Path filePath) throws IOException;
}
