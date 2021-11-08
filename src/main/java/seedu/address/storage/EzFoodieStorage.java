package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyEzFoodie;

/**
 * Represents a storage for {@link seedu.address.model.EzFoodie}.
 */
public interface EzFoodieStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEzFoodieFilePath();

    /**
     * Returns ezFoodie data as a {@link ReadOnlyEzFoodie}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEzFoodie> readEzFoodie() throws DataConversionException, IOException;

    /**
     * @see #getEzFoodieFilePath()
     */
    Optional<ReadOnlyEzFoodie> readEzFoodie(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEzFoodie} to the storage.
     *
     * @param ezFoodie cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEzFoodie(ReadOnlyEzFoodie ezFoodie) throws IOException;

    /**
     * @see #saveEzFoodie(ReadOnlyEzFoodie)
     */
    void saveEzFoodie(ReadOnlyEzFoodie ezFoodie, Path filePath) throws IOException;

}
