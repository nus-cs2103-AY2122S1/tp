package seedu.programmer.logic.commands;

import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.logic.commands.DownloadCommand.SHOWING_DOWNLOAD_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;

public class DownloadCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_download_success() {
        DownloadCommandResult expectedCommandResult = new DownloadCommandResult(SHOWING_DOWNLOAD_MESSAGE);
        assertCommandSuccess(new DownloadCommand(), model, expectedCommandResult, expectedModel);
    }
}
