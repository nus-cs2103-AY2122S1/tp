package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.HOON;
import static seedu.address.testutil.TypicalFriends.IDA;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendsList;
import seedu.address.model.ReadOnlyFriendsList;

public class JsonFriendsListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonFriendsListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFriendsList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFriendsList(null));
    }

    private java.util.Optional<ReadOnlyFriendsList> readFriendsList(String filePath) throws Exception {
        return new JsonFriendsListStorage(Paths.get(filePath)).readFriendsList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFriendsList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFriendsList("notJsonFormatFriendsList.json"));
    }

    @Test
    public void readFriendsList_invalidFriendsList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFriendsList("invalidFriendsList.json"));
    }

    @Test
    public void readFriendsList_invalidAndValidFriendsList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFriendsList("invalidAndValidFriendsList.json"));
    }

    @Test
    public void readAndSaveFriendsList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFriendsList.json");
        FriendsList original = getTypicalFriendsList();
        JsonFriendsListStorage jsonFriendsListStorage = new JsonFriendsListStorage(filePath);

        // Save in new file and read back
        jsonFriendsListStorage.saveFriendsList(original, filePath);
        ReadOnlyFriendsList readBack = jsonFriendsListStorage.readFriendsList(filePath).get();
        assertEquals(original, new FriendsList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFriend(HOON);
        original.removeFriend(ALICE);
        jsonFriendsListStorage.saveFriendsList(original, filePath);
        readBack = jsonFriendsListStorage.readFriendsList(filePath).get();
        assertEquals(original, new FriendsList(readBack));

        // Save and read without specifying file path
        original.addFriend(IDA);
        jsonFriendsListStorage.saveFriendsList(original); // file path not specified
        readBack = jsonFriendsListStorage.readFriendsList().get(); // file path not specified
        assertEquals(original, new FriendsList(readBack));

    }

    @Test
    public void saveFriendsList_nullFriendsList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFriendsList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code friendsList} at the specified {@code filePath}.
     */
    private void saveFriendsList(ReadOnlyFriendsList friendsList, String filePath) {
        try {
            new JsonFriendsListStorage(Paths.get(filePath))
                    .saveFriendsList(friendsList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFriendsList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFriendsList(new FriendsList(), null));
    }
}
