package tutoraid.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.Assert;

public class ProgressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Progress(null));
    }

    @Test
    public void constructor_invalidProgress_throwsIllegalArgumentException() {
        String invalidProgress = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Progress(invalidProgress));
    }

    @Test
    public void isValidProgress() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Progress.isValidProgress(null));

        // invalid name
        assertFalse(Progress.isValidProgress("")); // empty string
        assertFalse(Progress.isValidProgress(" ")); // spaces only


        // valid name
        assertTrue(Progress.isValidProgress("did homework")); // alphabets only
        assertTrue(Progress.isValidProgress("12345")); // numbers only
        assertTrue(Progress.isValidProgress("did 2 assignments")); // alphanumeric characters
        assertTrue(Progress.isValidProgress("Capital Tan")); // with capital letters
        assertTrue(Progress.isValidProgress("Still required to study 2103T")); // long progress
        assertTrue(Progress.isValidProgress("exam*")); // contains non-alphanumeric characters
    }

    @Test
    public void equal() {
        String progressOne = "Did Homework";
        String progressTwo = "Finished Exam";
        Progress amyProgress = new Progress(progressOne);
        Progress bobProgress = new Progress(progressOne); // same as amy
        Progress charlesProgress = new Progress(progressTwo); // different from amy

        // same object -> returns true
        assertTrue(amyProgress.equals(amyProgress));

        // same values -> returns true
        assertTrue(amyProgress.equals(bobProgress));

        // different types -> returns false
        assertFalse(amyProgress.equals(true));

        // null -> returns false
        assertFalse(amyProgress.equals(null));

        // different progress -> returns false
        assertFalse(amyProgress.equals(charlesProgress));
    }
}
