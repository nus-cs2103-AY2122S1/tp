package seedu.address.storage.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.guest.GuestBook;
import seedu.address.testutil.guest.TypicalGuests;

public class JsonSerializableGuestBookTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "Guest", "JsonSerializableGuestBookTest");
    private static final Path TYPICAL_GUESTS_FILE = TEST_DATA_FOLDER.resolve("typicalGuestBook.json");
    private static final Path INVALID_GUESTS_FILE = TEST_DATA_FOLDER.resolve("invalidGuestBook.json");
    private static final Path DUPLICATE_GUEST_FILE = TEST_DATA_FOLDER.resolve("duplicateGuestBook.json");

    @Test
    public void toModelType_typicalGuestsFile_success() throws Exception {
        JsonSerializableGuestBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_GUESTS_FILE,
                JsonSerializableGuestBook.class).get();
        GuestBook guestBookFromFile = dataFromFile.toModelType();
        GuestBook typicalGuestsBook = TypicalGuests.getTypicalGuestBook();
        assertEquals(guestBookFromFile, typicalGuestsBook);
    }

    @Test
    public void toModelType_invalidGuestsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGuestBook dataFromFile = JsonUtil.readJsonFile(INVALID_GUESTS_FILE,
                JsonSerializableGuestBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGuests_throwsIllegalValueException() throws Exception {
        JsonSerializableGuestBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GUEST_FILE,
                JsonSerializableGuestBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableGuestBook.MESSAGE_DUPLICATE_GUEST,
                dataFromFile::toModelType);
    }

}
