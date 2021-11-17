package socialite.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import socialite.testutil.Assert;

public class ProfilePictureStorageManagerTest {

    private static final String TEST_FILE_NAME = "TEST_FILE_NAME";
    private static final Path FOLDER_PATH = ProfilePictureStorageManager.PROFILE_PIC_FOLDER_PATH;

    private File invalidFile = Paths.get("some", "random", "nonexistent", "file").toFile();
    private File validFile = Paths.get("src", "test", "data", "profilepictures", "simu.jpeg").toFile();
    private ProfilePictureStorageManager manager = ProfilePictureStorageManager.getInstance();

    @Test
    public void saveInvalidFile_throwsIoexception() {
        Assert.assertThrows(IOException.class, () -> manager.saveProfilePicture(invalidFile, "testName"));
    }

    @Test
    public void saveNullFile_notSaved() {
        assertDoesNotThrow(() -> manager.saveProfilePicture(null, TEST_FILE_NAME));
        assertTrue(Arrays.stream(FOLDER_PATH.toFile().list()).noneMatch(x -> x.equals(TEST_FILE_NAME)));
    }

    @Test
    public void saveNullName_notSaved() {
        assertDoesNotThrow(() -> manager.saveProfilePicture(validFile, null));
        assertTrue(Arrays.stream(FOLDER_PATH.toFile().list()).noneMatch(x -> x.equals(validFile.toString())));
    }

    @Test
    public void deleteInvalidFile_throwsIoexception() {
        Assert.assertThrows(IOException.class, () -> manager.deleteProfilePicture(invalidFile.toPath()));
    }
}
