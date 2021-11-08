package seedu.programmer.logic.commands;

import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.logic.commands.UploadCommand.SHOWING_UPLOAD_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code UploadCommand}.
 */
public class UploadCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute_upload_success() {
        UploadCommandResult expectedCommandResult = new UploadCommandResult(SHOWING_UPLOAD_MESSAGE);
        assertCommandSuccess(new UploadCommand(), model, expectedCommandResult, model);
    }
}
