package seedu.address.storage.vendor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.vendor.VendorBook;
import seedu.address.testutil.vendor.TypicalVendors;

public class JsonSerializableVendorBookTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "Vendor", "JsonSerializableVendorBookTest");
    private static final Path TYPICAL_VENDOR_FILE = TEST_DATA_FOLDER.resolve("typicalVendorBook.json");
    private static final Path INVALID_VENDOR_FILE = TEST_DATA_FOLDER.resolve("invalidVendorBook.json");
    private static final Path DUPLICATE_VENDOR_FILE = TEST_DATA_FOLDER.resolve("duplicateVendorBook.json");

    @Test
    public void toModelType_typicalVendorsFile_success() throws Exception {
        JsonSerializableVendorBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_VENDOR_FILE,
                JsonSerializableVendorBook.class).get();
        VendorBook vendorBookFromFile = dataFromFile.toModelType();
        VendorBook typicalVendorBook = TypicalVendors.getTypicalVendorBook();
        assertEquals(vendorBookFromFile, typicalVendorBook);
    }

    @Test
    public void toModelType_invalidVendorsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableVendorBook dataFromFile = JsonUtil.readJsonFile(INVALID_VENDOR_FILE,
                JsonSerializableVendorBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateVendors_throwsIllegalValueException() throws Exception {
        JsonSerializableVendorBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_VENDOR_FILE,
                JsonSerializableVendorBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableVendorBook.MESSAGE_DUPLICATE_VENDOR,
                dataFromFile::toModelType);
    }

}
