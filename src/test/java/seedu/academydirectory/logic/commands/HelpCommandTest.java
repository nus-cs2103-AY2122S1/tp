package seedu.academydirectory.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.model.VersionedModelManager;

public class HelpCommandTest {
    private final VersionedModel model = new VersionedModelManager();

    @Test
    public void valid_help_commands() {
        // two general help commands are equal to each other
        HelpCommand helpCommand1 = new HelpCommand();
        assertEquals(helpCommand1, new HelpCommand());

        // a general help command is not equal to a specific help command
        assertNotEquals(helpCommand1, new HelpCommand("add", AddCommand.HELP_MESSAGE));

        // a help command is not equal to another type
        assertNotEquals(helpCommand1, "Life");

        HelpCommand helpCommand2 = new HelpCommand("grade", GradeCommand.HELP_MESSAGE);

        // a help command is equal if the specific type is equal
        assertEquals(helpCommand2, new HelpCommand("grade", GradeCommand.HELP_MESSAGE));
        assertNotEquals(helpCommand2, helpCommand1);

        // a help command is not equal even if the type is equal, if the message are different
        assertNotEquals(helpCommand2, new HelpCommand("grade", AddCommand.HELP_MESSAGE));

        // a help command is not equal if type and help message are different
        assertNotEquals(helpCommand2, new HelpCommand("clear", ClearCommand.HELP_MESSAGE));
    }

    @Test
    public void execute_valid_help() {
        HelpCommand helpCommand1 = new HelpCommand();
        CommandResult commandResult1 = new CommandResult(HelpCommand.MESSAGE_HELP_SUCCESS_GENERAL, true, false);
        // assert that executing a general help command is successful
        assertCommandSuccess(helpCommand1, model, commandResult1, model);

        HelpCommand helpCommand2 = new HelpCommand("grade", GradeCommand.HELP_MESSAGE);
        CommandResult commandResult2 = new CommandResult(String.format(HelpCommand.MESSAGE_HELP_SUCCESS_SPECIFIC,
                "grade"), true, false);
        // asserting that executing a specific help command is successful
        assertCommandSuccess(helpCommand2, model, commandResult2, model);

        HelpCommand helpCommand3 = new HelpCommand("exit", GradeCommand.HELP_MESSAGE);
        CommandResult commandResult3 = new CommandResult(String.format(HelpCommand.MESSAGE_HELP_SUCCESS_SPECIFIC,
                "exit"), true, false);
        // asserting that executing a non-viable help command is successful
        assertCommandSuccess(helpCommand3, model, commandResult3, model);
    }

    @Test
    public void executeNonValidHelp() {
        // help command throws exception if inputs are null
        assertThrows(NullPointerException.class, () -> new HelpCommand(null, null)
                .execute(model));
    }
}
