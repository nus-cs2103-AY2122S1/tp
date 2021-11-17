package socialite.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import socialite.model.ProfilePictureSyncModel;

public interface ProfilePictureStorage {

    /**
     * Returns the folder path containing all profile pictures
     *
     * @return path to folder containing profile pictures
     */
    Path getProfilePictureFolderPath();

    void deleteProfilePicture(Path name) throws IOException;

    void saveProfilePicture(File profilePicture, String prefix) throws IOException;

    void syncProfilePictures(
            ProfilePictureSyncModel.ProfilePictureEditDescriptor profilePictureEditDescriptor) throws IOException;

}
