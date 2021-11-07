package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.storage.archive.ArchiveStorage;
import seedu.address.storage.guest.GuestBookStorage;
import seedu.address.storage.vendor.VendorBookStorage;

/**
 * API of the Storage component
 */
public interface Storage extends GuestBookStorage, VendorBookStorage, UserPrefsStorage, ArchiveStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getGuestBookFilePath();

    @Override
    Optional<ReadOnlyGuestBook> readGuestBook() throws DataConversionException, IOException;

    @Override
    void saveGuestBook(ReadOnlyGuestBook guestManager) throws IOException;

}
