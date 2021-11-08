package safeforhall.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import safeforhall.commons.exceptions.DataConversionException;
import safeforhall.model.AddressBook;
import safeforhall.model.ReadOnlyAddressBook;

/**
 * Represents a storage for {@link AddressBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyAddressBook)
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

    /**
     * Creates the exports folder within data
     * @param filepath The path of the exports folder.
     * @throws IOException if there was any problem writing to the file.
     */
    void createExportsFolder(Path filepath) throws IOException;

}
