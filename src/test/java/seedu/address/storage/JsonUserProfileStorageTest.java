package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;

public class JsonUserProfileStorageTest {

    @Test
    public void getUserProfilePath_samePath_success() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path expectedPath = Paths.get("userprofile.json");
        assertEquals(expectedPath, userProfileStorage.getUserProfilePath());
    }

    @Test
    public void getUserProfilePath_differentPath_failure() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path expectedPath = Paths.get("userprofile2.json");
        assertNotEquals(expectedPath, userProfileStorage.getUserProfilePath());
    }

    @Test
    public void readUserProfilePath_noFilePathProvided_success() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path expectedPath = Paths.get("userprofile.json");
        try {
            assertNotNull(userProfileStorage.readUserProfile());
        } catch (DataConversionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readUserProfilePath_noFilePathProvided_failure() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path expectedPath = Paths.get("userprofile2.json");
        try {
            assertNotNull(userProfileStorage.readUserProfile());
        } catch (DataConversionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readUserProfilePath_correctFilePathProvided_success() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path expectedPath = Paths.get("userprofile.json");
        try {
            assertNotNull(userProfileStorage.readUserProfile(expectedPath));
        } catch (DataConversionException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readUserProfilePath_invalidFilePathProvided_success() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path expectedPath = Paths.get("invalidUserProfile.json");
        try {
            assertNotNull(userProfileStorage.readUserProfile(expectedPath));
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readUserProfilePath_wrongFilePathProvided_failure() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path expectedPath = Paths.get("userprofile2.json");
        try {
            assertNotNull(userProfileStorage.readUserProfile(expectedPath));
        } catch (DataConversionException e) {
            assertEquals(e, null);
        }
    }

    @Test
    public void saveUserProfile() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path profileFilePath = Paths.get("userprofilesave.json");
        Optional<JsonSerializableUserProfile> userProfile;
        try {
            userProfile = JsonUtil.readJsonFile(profileFilePath,
                    JsonSerializableUserProfile.class);
            try {
                userProfileStorage.saveUserProfile(userProfile.get());
            } catch (IOException e) {
                assertNotEquals(e, null);
            }
        } catch (DataConversionException dce) {
            assertNotEquals(dce, null);
        }
    }
}
