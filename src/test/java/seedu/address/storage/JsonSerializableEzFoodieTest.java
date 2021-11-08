package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.EzFoodie;
import seedu.address.testutil.TypicalMembers;

/**
 * Tests the functionalities of
 * {@code JsonSerializableEzFoodie}.
 */
public class JsonSerializableEzFoodieTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEzFoodieTest");
    private static final Path TYPICAL_MEMBERS_FILE = TEST_DATA_FOLDER.resolve("typicalMembersEzFoodie.json");
    private static final Path INVALID_MEMBER_FILE = TEST_DATA_FOLDER.resolve("invalidMemberEzFoodie.json");
    private static final Path DUPLICATE_MEMBER_FILE = TEST_DATA_FOLDER.resolve("duplicateMemberEzFoodie.json");

    @Test
    public void toModelType_typicalMembersFile_success() throws Exception {
        JsonSerializableEzFoodie dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEMBERS_FILE,
                JsonSerializableEzFoodie.class).get();
        EzFoodie ezFoodieFromFile = dataFromFile.toModelType();
        EzFoodie typicalMembersEzFoodie = TypicalMembers.getTypicalEzFoodie();
        assertEquals(ezFoodieFromFile, typicalMembersEzFoodie);
    }

    @Test
    public void toModelType_invalidMemberFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEzFoodie dataFromFile = JsonUtil.readJsonFile(INVALID_MEMBER_FILE,
                JsonSerializableEzFoodie.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMembers_throwsIllegalValueException() throws Exception {
        JsonSerializableEzFoodie dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEMBER_FILE,
                JsonSerializableEzFoodie.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEzFoodie.MESSAGE_DUPLICATE_MEMBER,
                dataFromFile::toModelType);
    }

}
