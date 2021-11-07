package seedu.address.storage.archive;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.guest.ReadOnlyGuestBook;

public interface ArchiveStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getArchiveFilePath();

    /**
     * Returns guest data as a {@link ReadOnlyGuestBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyGuestBook> readArchive() throws DataConversionException, IOException;

    /**
     * @see #getArchiveFilePath()
     */
    Optional<ReadOnlyGuestBook> readArchive(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyGuestBook} to the storage.
     * @param archive cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveArchive(ReadOnlyGuestBook archive) throws IOException;

    /**
     * @see #saveArchive(ReadOnlyGuestBook)
     */
    void saveArchive(ReadOnlyGuestBook guestBook, Path filePath) throws IOException;


}
