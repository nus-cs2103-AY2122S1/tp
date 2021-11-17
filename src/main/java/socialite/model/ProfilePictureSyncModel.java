package socialite.model;

import java.io.File;
import java.nio.file.Path;

public class ProfilePictureSyncModel {

    public class ProfilePictureEditDescriptor {
        public final Path toDelete;
        public final String dest;
        public final File source;

        ProfilePictureEditDescriptor(Path toDelete, String dest, File source) {
            this.toDelete = toDelete;
            this.dest = dest;
            this.source = source;
        }
    }
    private Path toDelete;

    private String dest;

    private File source;

    public void deleteProfilePicture(Path name) {
        this.toDelete = name;
    }

    /**
     * adds profile picture to model
     * @param file
     * @param name
     */
    public void saveProfilePicture(File file, String name) {
        this.dest = name;
        this.source = file;
    }

    public ProfilePictureEditDescriptor getProfilePictureEditDescriptor() {
        return new ProfilePictureEditDescriptor(this.toDelete, this.dest, this.source);
    }

    /**
     * Resets variables for next picture command
     */
    public void clear() {
        dest = null;
        source = null;
        toDelete = null;
    }

}
