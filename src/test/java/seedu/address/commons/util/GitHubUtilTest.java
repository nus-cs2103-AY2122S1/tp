package seedu.address.commons.util;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GitHubUtilTest {
    @Test
    public void getProfilePicture_validUserName_success() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getProfilePicture("jai2501");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertFalse(runtimeExceptionThrown);
    }

    @Test
    public void getProfilePicture_invalidUserName_runtimeException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getProfilePicture("/");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertTrue(runtimeExceptionThrown);
    }

    @Test
    public void getRepoNames_invalidUserName_runtimeException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getRepoNames("/");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertTrue(runtimeExceptionThrown);
    }

    @Test
    public void getRepoNames_validUserName_noException() {
        boolean runtimeExceptionThrown = false;
        try {
            ArrayList<String> repos = GitHubUtil.getRepoNames("jai2501");
            Assertions.assertTrue(repos.size() > 0);
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertFalse(runtimeExceptionThrown);
    }

    @Test
    public void getRepoCount_invalidUserName_runtimeException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getRepoCount("/");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertTrue(runtimeExceptionThrown);
    }

    @Test
    public void getRepoCount_validUserName_noException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getRepoCount("jai2501");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertFalse(runtimeExceptionThrown);
    }

    @Test
    public void getLanguageStats_invalidUserName_runtimeException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getLanguageStats("/", new ArrayList<>());
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertTrue(runtimeExceptionThrown);
    }

    @Test
    public void getLanguageStats_validUserName_noException() {
        boolean runtimeExceptionThrown = false;
        String expectedResult = "{Java=0.98, Shell=0.012, Batchfile=0.008}";
        try {
            String actualResult = GitHubUtil.getLanguageStats("jai2501",
                    new ArrayList<>(Collections.singleton("ip"))).toString();
            Assertions.assertEquals(expectedResult, actualResult);
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertFalse(runtimeExceptionThrown);
    }

    @Test
    public void getUserStats_invalidUserName_runtimeException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getUserStats("/");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertTrue(runtimeExceptionThrown);
    }

    @Test
    public void getUserStats_validUserName_noException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getUserStats("jai2501");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertFalse(runtimeExceptionThrown);
    }
}
