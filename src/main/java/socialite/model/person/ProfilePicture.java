package socialite.model.person;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a Person's Profile Picture in the contact list.
 * Default value is the default profile picture.
 */
public class ProfilePicture {

    public static final ProfilePicture DEFAULT_PICTURE = new ProfilePicture(
            Paths.get("images", "default_profile_picture.png"));

    public final Path value;

    public ProfilePicture(Path value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfilePicture // instanceof handles nulls
                && value.toString().equals(((ProfilePicture) other).value.toString())); // state check
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
