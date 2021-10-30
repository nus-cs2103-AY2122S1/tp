package seedu.address.model.util;

/**
 * API for Watching the User Profile.
 */
public interface UserProfileWatcher {

    /**
     * Executes the method, depending on how the
     * implementing classes wish the changes are to
     * be reflected.
     */
    public void updateUserProfile();
}
