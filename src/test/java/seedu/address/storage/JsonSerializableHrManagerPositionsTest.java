package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.HrManager;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

public class JsonSerializableHrManagerPositionsTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableHrManagerPositionsTest");
    private static final Path TYPICAL_POSITIONS_FILE = TEST_DATA_FOLDER.resolve("typicalPositionsHrManager.json");
    private static final Path INVALID_POSITION_FILE = TEST_DATA_FOLDER.resolve("invalidPositionHrManager.json");
    private static final Path DUPLICATE_POSITION_FILE = TEST_DATA_FOLDER.resolve("duplicatePositionHrManager.json");

    @Test
    public void toModelType_typicalPositionsFile_success() throws Exception {
        JsonSerializableHrManagerPositions dataFromFile = JsonUtil.readJsonFile(TYPICAL_POSITIONS_FILE,
                JsonSerializableHrManagerPositions.class).get();
        HrManager addressBookFromFile = dataFromFile.toModelType();
        HrManager typicalPersonsAddressBook = new HrManager();
        typicalPersonsAddressBook.addPosition(new Position(new Title("Bookkeeper")));
        typicalPersonsAddressBook.addPosition(new Position(new Title("Administrative Assistant")));
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPositionFile_throwsIllegalValueException() throws Exception {
        JsonSerializableHrManagerPositions dataFromFile = JsonUtil.readJsonFile(INVALID_POSITION_FILE,
                JsonSerializableHrManagerPositions.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePositions_throwsIllegalValueException() throws Exception {
        JsonSerializableHrManagerPositions dataFromFile = JsonUtil.readJsonFile(DUPLICATE_POSITION_FILE,
                JsonSerializableHrManagerPositions.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableHrManagerPositions.MESSAGE_DUPLICATE_POSITIONS,
                dataFromFile::toModelType);
    }
}
