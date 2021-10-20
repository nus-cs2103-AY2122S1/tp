package seedu.address.commons.util;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;


public class GitHubUtilTest {

    @Test
    public void getProfilePicture_invalidUserName_runtimeException() {
        boolean runtimeExceptionThrown = false;
        try {
            Image image = GitHubUtil.getProfilePicture("/");
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
    public void getContributionsCount_invalidUserName_runtimeException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getContributionsCount("/");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertTrue(runtimeExceptionThrown);
    }

    @Test
    public void getContributionsCount_validUserName_noException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getContributionsCount("jai2501");
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
            GitHubUtil.getLanguageStats("/");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertTrue(runtimeExceptionThrown);
    }

    @Test
    public void getLanguageStats_validUserName_noException() {
        boolean runtimeExceptionThrown = false;
        try {
            GitHubUtil.getLanguageStats("jai2501");
        } catch (RuntimeException e) {
            runtimeExceptionThrown = true;
        }
        Assertions.assertFalse(runtimeExceptionThrown);
    }

}
