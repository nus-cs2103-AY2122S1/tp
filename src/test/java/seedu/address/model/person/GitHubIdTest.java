package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GitHubIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GitHubId(null));
    }

    @Test
    public void constructor_invalidGitHubId_throwsIllegalArgumentException() {
        String invalidGitHubId = "";
        assertThrows(IllegalArgumentException.class, () -> new GitHubId(invalidGitHubId));
    }

    @Test
    public void isValidGitHubId() {
        // null GitHubId
        assertThrows(NullPointerException.class, () -> GitHubId.isValidGitHubId(null));

        // blank GitHubId
        assertFalse(GitHubId.isValidGitHubId("")); // empty string
        assertFalse(GitHubId.isValidGitHubId(" ")); // spaces only

        // invalid GitHubId
        assertFalse(GitHubId.isValidGitHubId("sid1234-")); // invalid because can't end with -
        assertFalse(GitHubId.isValidGitHubId("-sid123")); // invalid because can't start with -
        assertFalse(GitHubId.isValidGitHubId("_s12-id")); // invalid because can't have anything other than
                                                           // alphanumeric characters and single hyphens
        assertFalse(GitHubId.isValidGitHubId("siddharth--Sid")); // invalid because only single hyphens are allowed
        assertFalse(GitHubId.isValidGitHubId("siddharth-@Sid")); // invalid because special characters are not
                                                                      // allowed

        // valid GitHubId
        assertTrue(GitHubId.isValidGitHubId("siddharth"));
        assertTrue(GitHubId.isValidGitHubId("siddharth-Sid"));
        assertTrue(GitHubId.isValidGitHubId("siddharth-Sid-2024"));
    }
}
