package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SummaryCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.summary.Summary;

public class SummaryCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_summary_success() {
        Summary summary = new Summary(model.getAddressBook());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, summary);
        assertCommandSuccess(new SummaryCommand(), model, expectedCommandResult, expectedModel);
    }
}
