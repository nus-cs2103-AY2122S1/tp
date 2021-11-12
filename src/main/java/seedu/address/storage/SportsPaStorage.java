package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySportsPa;
import seedu.address.model.SportsPa;

/**
 * Represents a storage for {@link SportsPa}.
 */
public interface SportsPaStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSportsPaFilePath();

    /**
     * Returns SportsPa data as a {@link ReadOnlySportsPa}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySportsPa> readSportsPa() throws DataConversionException, IOException;

    /**
     * @see #getSportsPaFilePath()
     */
    Optional<ReadOnlySportsPa> readSportsPa(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySportsPa} to the storage.
     * @param sportsPa cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSportsPa(ReadOnlySportsPa sportsPa) throws IOException;

    /**
     * @see #saveSportsPa(ReadOnlySportsPa)
     */
    void saveSportsPa(ReadOnlySportsPa sportsPa, Path filePath) throws IOException;

}
