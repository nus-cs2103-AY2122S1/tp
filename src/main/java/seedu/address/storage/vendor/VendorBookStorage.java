package seedu.address.storage.vendor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.vendor.ReadOnlyVendorBook;

public interface VendorBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getVendorBookFilePath();

    /**
     * Returns vendor data as a {@link ReadOnlyVendorBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyVendorBook> readVendorBook() throws DataConversionException, IOException;

    /**
     * @see #getVendorBookFilePath()
     */
    Optional<ReadOnlyVendorBook> readVendorBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyVendorBook} to the storage.
     *
     * @param vendorBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveVendorBook(ReadOnlyVendorBook vendorBook) throws IOException;

    /**
     * @see #saveVendorBook(ReadOnlyVendorBook)
     */
    void saveVendorBook(ReadOnlyVendorBook vendorBook, Path filePath) throws IOException;


}
