package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FriendsList;
import seedu.address.testutil.TypicalFriends;

public class JsonSerializableFriendsListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableFriendsListTest");
    private static final Path TYPICAL_FRIENDS_LIST_FILE = TEST_DATA_FOLDER.resolve("typicalFriendsList.json");
    private static final Path INVALID_FRIENDS_LIST_FILE = TEST_DATA_FOLDER.resolve("invalidFriendsList.json");
    private static final Path DUPLICATE_FRIENDS_IN_FRIENDS_LIST_FILE =
            TEST_DATA_FOLDER.resolve("duplicateFriendsInFriendsList.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableFriendsList dataFromFile = JsonUtil.readJsonFile(TYPICAL_FRIENDS_LIST_FILE,
                JsonSerializableFriendsList.class).get();
        FriendsList friendsListFromFile = dataFromFile.toModelType();
        FriendsList typicalPersonsFriendsList = TypicalFriends.getTypicalFriendsList();
        assertEquals(friendsListFromFile, typicalPersonsFriendsList);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFriendsList dataFromFile = JsonUtil.readJsonFile(INVALID_FRIENDS_LIST_FILE,
                JsonSerializableFriendsList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableFriendsList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FRIENDS_IN_FRIENDS_LIST_FILE,
                JsonSerializableFriendsList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFriendsList.MESSAGE_DUPLICATE_FRIEND_ID,
                dataFromFile::toModelType);
    }

}
