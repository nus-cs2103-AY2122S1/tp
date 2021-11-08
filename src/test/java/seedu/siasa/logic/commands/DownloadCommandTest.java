package seedu.siasa.logic.commands;

import static seedu.siasa.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.siasa.logic.commands.DownloadCommand.MESSAGE_DOWNLOAD_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.siasa.model.Model;
import seedu.siasa.model.ModelManager;

public class DownloadCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_DOWNLOAD_SUCCESS, false, false);
        assertCommandSuccess(new DownloadCommand(), model, expectedCommandResult, expectedModel);
    }
}
