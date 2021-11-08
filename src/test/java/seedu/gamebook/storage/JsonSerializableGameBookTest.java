package seedu.gamebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.gamebook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.gamebook.commons.exceptions.IllegalValueException;
import seedu.gamebook.commons.util.JsonUtil;
import seedu.gamebook.model.GameBook;
import seedu.gamebook.testutil.TypicalGameEntries;

public class JsonSerializableGameBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableGameBookTest");
    private static final Path TYPICAL_GAME_ENTRIES_FILE = TEST_DATA_FOLDER.resolve("typicalGameEntriesGameBook.json");
    private static final Path INVALID_GAME_ENTRY_FILE = TEST_DATA_FOLDER.resolve("invalidGameEntryGameBook.json");
    private static final Path DUPLICATE_GAME_ENTRY_FILE = TEST_DATA_FOLDER.resolve("duplicateGameEntryGameBook.json");

    // todo: to change duplicate handling before running this test
    public void toModelType_typicalGameEntriesFile_success() throws Exception {
        JsonSerializableGameBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_GAME_ENTRIES_FILE,
                JsonSerializableGameBook.class).get();
        GameBook addressBookFromFile = dataFromFile.toModelType();
        GameBook typicalGameEntriesAddressBook = TypicalGameEntries.getTypicalGameBook();
        assertEquals(addressBookFromFile, typicalGameEntriesAddressBook);
    }

    @Test
    public void toModelType_invalidGameEntryFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGameBook dataFromFile = JsonUtil.readJsonFile(INVALID_GAME_ENTRY_FILE,
                JsonSerializableGameBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
