package seedu.address.storage.vendor;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.vendor.TypicalVendors.DANIEL_VENDOR;
import static seedu.address.testutil.vendor.TypicalVendors.getTypicalVendorBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.VendorBook;
import seedu.address.testutil.vendor.VendorBuilder;

public class JsonVendorBookStorageTest {

    private static final Path
            TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Vendor", "JsonVendorBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readVendorBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readVendorBook(null));
    }

    private java.util.Optional<ReadOnlyVendorBook> readVendorBook(String filePath) throws Exception {
        return new JsonVendorBookStorage(Paths.get(filePath)).readVendorBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readVendorBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readVendorBook("notJsonFormatVendorBook.json"));
    }

    @Test
    public void readVendorBook_invalidVendorBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readVendorBook("invalidVendorBook.json"));
    }

    @Test
    public void readVendorBook_invalidAndValidVendorBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readVendorBook("invalidAndValidVendorBook.json"));
    }

    @Test
    public void readAndSaveVendorBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempVendorBook.json");
        VendorBook original = getTypicalVendorBook();
        JsonVendorBookStorage jsonVendorBookStorage = new JsonVendorBookStorage(filePath);

        // Save in new file and read back
        jsonVendorBookStorage.saveVendorBook(original, filePath);
        ReadOnlyVendorBook readBack = jsonVendorBookStorage.readVendorBook(filePath).get();
        assertEquals(original, new VendorBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addVendor(new VendorBuilder(DANIEL_VENDOR).withVendorId("1231801").build());
        original.removeVendor(DANIEL_VENDOR);
        jsonVendorBookStorage.saveVendorBook(original, filePath);
        readBack = jsonVendorBookStorage.readVendorBook(filePath).get();
        assertEquals(original, new VendorBook(readBack));

        // Save and read without specifying file path
        original.addVendor(new VendorBuilder(DANIEL_VENDOR).withVendorId("12801").build());
        jsonVendorBookStorage.saveVendorBook(original); // file path not specified
        readBack = jsonVendorBookStorage.readVendorBook().get(); // file path not specified
        assertEquals(original, new VendorBook(readBack));
    }

    @Test
    public void saveVendorBook_nullVendorBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveVendorBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code guestBook} at the specified {@code filePath}.
     */
    private void saveVendorBook(ReadOnlyVendorBook guestBook, String filePath) {
        try {
            new JsonVendorBookStorage(Paths.get(filePath))
                    .saveVendorBook(guestBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveVendorBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveVendorBook(new VendorBook(), null));
    }


}
