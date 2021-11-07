package seedu.modulink.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulink.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GitHubUsernameTest {

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidGitHubUsername = "";
        assertThrows(IllegalArgumentException.class, () -> new GitHubUsername(invalidGitHubUsername));
    }

    @Test
    public void isValidGitHubUsername() {
        // invalid GitHub username
        assertFalse(GitHubUsername.isValidUsername("")); // empty string
        assertFalse(GitHubUsername.isValidUsername(" ")); // spaces only
        assertFalse(GitHubUsername.isValidUsername("^")); // only non-alphanumeric characters
        assertFalse(GitHubUsername.isValidUsername("@peter")); // contains non-alphanumeric characters
        assertFalse(GitHubUsername.isValidUsername("alex yeoh")); // contains spaces
        assertFalse(GitHubUsername.isValidUsername("alex_yeoh")); // contains underscore

        // valid GitHub username
        assertTrue(GitHubUsername.isValidUsername("peterjack")); // alphabets only
        assertTrue(GitHubUsername.isValidUsername("12345")); // numbers only
        assertTrue(GitHubUsername.isValidUsername("peterthe2nd")); // alphanumeric characters
        assertTrue(GitHubUsername.isValidUsername("CapitalTan")); // with capital letters
        assertTrue(GitHubUsername.isValidUsername("alexa-tan-test")); // with hyphens

        // valid null GitHub username
        assertTrue(GitHubUsername.isValidUsername(null));
    }
}
