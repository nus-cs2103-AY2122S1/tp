package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_ID_BOB;
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
        assertFalse(GitHubId.isValidGitHubId("_s12-id")); // invalid because underscore not allowed
        assertFalse(GitHubId.isValidGitHubId("siddharth--Sid")); // invalid because only single hyphens are allowed
        assertFalse(GitHubId.isValidGitHubId("@Sid")); // invalid because special characters are not allowed

        // valid GitHubId
        assertTrue(GitHubId.isValidGitHubId("siddharth"));
        assertTrue(GitHubId.isValidGitHubId("Siddharth-Sid"));
        assertTrue(GitHubId.isValidGitHubId("siddharth-Sid-2024"));
    }

    @Test
    public void isEqualGitHubId() {
        GitHubId gitHubId = new GitHubId("Siddharth-Sid");
        GitHubId different_gitHubId = new GitHubId("siddharth");
        GitHubId same_gitHubId = new GitHubId("Siddharth-Sid");

        // Different GitHubId
        assertFalse(gitHubId.equals(different_gitHubId));

        // Same Object
        assertTrue(gitHubId.equals(gitHubId));

        // Different Objects Same GitHubId
        assertTrue(gitHubId.equals(same_gitHubId));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(VALID_GITHUB_ID_AMY.hashCode(), VALID_GITHUB_ID_AMY.hashCode());
        assertNotEquals(VALID_GITHUB_ID_AMY.hashCode(), VALID_GITHUB_ID_BOB.hashCode());
    }
}
