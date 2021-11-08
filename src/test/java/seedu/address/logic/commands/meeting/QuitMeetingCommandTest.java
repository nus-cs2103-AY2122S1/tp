package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.meeting.QuitMeetingCommand.MESSAGE_QUIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class QuitMeetingCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_QUIT_ACKNOWLEDGEMENT, false, false, false,
                false, true);
        CommandTestUtil.assertCommandSuccess(new QuitMeetingCommand(), model, expectedCommandResult, expectedModel);
    }
}
