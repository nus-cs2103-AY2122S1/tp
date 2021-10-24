package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DownloadCommand.SHOWING_DOWNLOAD_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class DownloadCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_download_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_DOWNLOAD_MESSAGE, false, false, true, false);
        assertCommandSuccess(new DownloadCommand(), model, expectedCommandResult, expectedModel);
    }
}
