package seedu.siasa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.siasa.commons.exceptions.DataConversionException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.Siasa;

/**
 * Represents a storage for {@link Siasa}.
 */
public interface SiasaStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSiasaFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlySiasa}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySiasa> readSiasa() throws DataConversionException, IOException;

    /**
     * @see #getSiasaFilePath()
     */
    Optional<ReadOnlySiasa> readSiasa(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySiasa} to the storage.
     * @param siasa cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSiasa(ReadOnlySiasa siasa) throws IOException;

    /**
     * @see #saveSiasa(ReadOnlySiasa)
     */
    void saveSiasa(ReadOnlySiasa siasa, Path filePath) throws IOException;

}
