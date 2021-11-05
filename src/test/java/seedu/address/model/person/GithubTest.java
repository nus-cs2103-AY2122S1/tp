package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GithubTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Github(null));
    }

    @Test
    public void constructor_invalidGithub_throwsIllegalArgumentException() {
        String invalidGithub = "";
        assertThrows(IllegalArgumentException.class, () -> new Github(invalidGithub));
    }

    @Test
    public void isValidGithub() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Github.isValidGithub(null));

        // invalid Github usernames
        assertFalse(Github.isValidGithub("")); // empty string
        assertFalse(Github.isValidGithub(" ")); // spaces only
        assertFalse(Github.isValidGithub("a_b")); // underscore
        assertFalse(Github.isValidGithub("a--b")); // double hyphen
        assertFalse(Github.isValidGithub("-a-b")); // start with hyphen
        assertFalse(Github.isValidGithub("a-b-")); // end with hyphen
        assertFalse(Github.isValidGithub("a".repeat(40))); // exceed 39 characters

        // valid Github usernames
        assertTrue(Github.isValidGithub("a")); // one alphabet character
        assertTrue(Github.isValidGithub("0")); // one numeric character
        assertTrue(Github.isValidGithub("a-b")); // one hyphen
        assertTrue(Github.isValidGithub("a-B")); // capital
        assertTrue(Github.isValidGithub("a-b-123")); // two separate hyphen
        assertTrue(Github.isValidGithub("a".repeat(39))); // 39 characters
    }

    @Test
    public void equals_twoSameObjects_success() {
        Github github = new Github("Jai2501");
        assertTrue(github.equals(github));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        Github github = new Github("Jai2501");
        Email email = new Email("jay@gmail.com");
        assertFalse(github.equals(email));
    }

    @Test
    public void equals_twoDifferentObjectsWithSameGitHubUsernames_success() {
        Github github1 = new Github("Jai2501");
        Github github2 = new Github("Jai2501");
        assertTrue(github1.equals(github2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentGitHubUsernames_falseOutput() {
        Github github1 = new Github("Jai2501");
        Github github2 = new Github("Atin123");
        assertFalse(github1.equals(github2));
    }

    @Test
    public void toString_aValidInput_success() {
        Github github = new Github("Jai2501");
        assertTrue(github.toString().equals("Jai2501"));
    }

    @Test
    public void hashCode_validInput_correctOutput() {
        Github github = new Github("Jai2501");
        assertTrue(github.hashCode() == -168664970);
    }
}
