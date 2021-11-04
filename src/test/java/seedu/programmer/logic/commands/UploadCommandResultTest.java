package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.programmer.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;

public class UploadCommandResultTest {
    private Model model = new ModelManager();

    @Test
    public void test_equals_returnsCorrectly() {
        UploadCommandResult uploadCommandResult = new UploadCommandResult("upload", model);

        // same values -> returns true
        assertEquals(uploadCommandResult, new UploadCommandResult("upload", model));

        // same object -> returns true
        assertEquals(uploadCommandResult, uploadCommandResult);

        // null -> returns false
        assertNotEquals(null, uploadCommandResult);

        // different model -> returns false
        Model otherModel = new ModelManager();
        otherModel.addStudent(ALICE);
        assertNotEquals(new UploadCommandResult("upload", otherModel), uploadCommandResult);

        // different CommandResult type -> returns false
        assertNotEquals(uploadCommandResult, new HelpCommandResult("help"));

        // different feedbackToUser value -> returns false
        assertNotEquals(uploadCommandResult, new UploadCommandResult("different", model));
    }
}
