package seedu.address.model.commons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RepoNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RepoName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "re!po";
        assertThrows(IllegalArgumentException.class, () -> new RepoName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> RepoName.isValidRepoName(null));

        // invalid name
        assertFalse(RepoName.isValidRepoName("")); // empty string
        assertFalse(RepoName.isValidRepoName(" ")); // space only
        assertFalse(RepoName.isValidRepoName("peter repo")); // contains space
        assertFalse(RepoName.isValidRepoName("^")); // only non-alphanumeric characters
        assertFalse(RepoName.isValidRepoName("peter*")); // contains non-alphanumeric characters


        // valid name
        assertTrue(RepoName.isValidRepoName("w_1~4-4.ds")); // all no

    }
}
