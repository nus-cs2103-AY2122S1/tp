package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class UploadCommandResultTest {

    @Test
    public void test_equals_returnsCorrectly() {
        UploadCommandResult uploadCommandResult = new UploadCommandResult("upload");

        // same values -> returns true
        assertEquals(uploadCommandResult, new UploadCommandResult("upload"));

        // same object -> returns true
        assertEquals(uploadCommandResult, uploadCommandResult);

        // null -> returns false
        assertNotEquals(null, uploadCommandResult);

        // different CommandResult type -> returns false
        assertNotEquals(uploadCommandResult, new HelpCommandResult("help"));

        // different feedbackToUser value -> returns false
        assertNotEquals(uploadCommandResult, new UploadCommandResult("different"));
    }
}
