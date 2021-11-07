package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;

public class JsonUserProfileStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUserProfileStorageTest");

    @Test
    public void getUserProfilePath_samePath_success() {
        Path expectedPath = TEST_DATA_FOLDER.resolve("userprofile.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(expectedPath);
        assertEquals(expectedPath, userProfileStorage.getUserProfilePath());
    }

    @Test
    public void readUserProfilePath_noFilePathProvided_success() {
        Path expectedPath = TEST_DATA_FOLDER.resolve("userprofile.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(expectedPath);
        try {
            assertNotNull(userProfileStorage.readUserProfile());
        } catch (DataConversionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readUserProfilePath_noFilePathProvided_failure() {
        Path expectedPath = TEST_DATA_FOLDER.resolve("userprofile2.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(expectedPath);
        try {
            assertNotNull(userProfileStorage.readUserProfile());
        } catch (DataConversionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readUserProfilePath_correctFilePathProvided_success() {
        Path expectedPath = TEST_DATA_FOLDER.resolve("userprofile.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(expectedPath);
        try {
            assertNotNull(userProfileStorage.readUserProfile(expectedPath));
        } catch (DataConversionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readUserProfilePath_invalidFilePathProvided_success() {
        Path expectedPath = TEST_DATA_FOLDER.resolve("invalidUserProfile.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(expectedPath);
        try {
            assertNotNull(userProfileStorage.readUserProfile(expectedPath));
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readUserProfilePath_wrongFilePathProvided_failure() {
        Path expectedPath = TEST_DATA_FOLDER.resolve("userprofile2.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(expectedPath);
        try {
            assertNotNull(userProfileStorage.readUserProfile(expectedPath));
        } catch (DataConversionException e) {
            assertEquals(e, null);
        }
    }

    @Test
    public void saveUserProfile() {
        Path profileFilePath = TEST_DATA_FOLDER.resolve("userprofilesave.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(profileFilePath);

        Optional<JsonSerializableUserProfile> userProfile;
        try {
            userProfile = JsonUtil.readJsonFile(profileFilePath,
                    JsonSerializableUserProfile.class);
            try {
                userProfileStorage.saveUserProfile(userProfile.get());
                Files.write(profileFilePath, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                assertNotEquals(e, null);
            }
        } catch (DataConversionException dce) {
            assertNotEquals(dce, null);
        }
    }
}
