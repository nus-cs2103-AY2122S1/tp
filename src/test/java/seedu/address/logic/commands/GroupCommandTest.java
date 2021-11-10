package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class GroupCommandTest {
    private Model model = new ModelManager();

    @Test
    public void group_nonRecordedName_messageFail() throws CommandException {
        assertEquals(new GroupCommand("test", "shui jie, test1").execute(model),
                new CommandResult(GroupCommand.MESSAGE_FAILURE));
    }

}
