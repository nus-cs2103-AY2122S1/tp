package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_keyWordsAbsent_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_validKeyWordsPresent_success() {
        CommandResult expectedCommandResult = new CommandResult(AddCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("add"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void parse_keywords() {
        HelpCommand helpCommand = new HelpCommand();
        assertEquals(new CommandResult(AddCommand.MESSAGE_USAGE), helpCommand.parseAdd());
        assertEquals(new CommandResult(ClearCommand.MESSAGE_USAGE), helpCommand.parseClear());
        assertEquals(new CommandResult(DeleteCommand.MESSAGE_USAGE), helpCommand.parseDelete());
        assertEquals(new CommandResult(DoneCommand.MESSAGE_USAGE), helpCommand.parseDone());
        assertEquals(new CommandResult(EditCommand.MESSAGE_USAGE), helpCommand.parseEdit());
        assertEquals(new CommandResult(ExitCommand.MESSAGE_USAGE), helpCommand.parseExit());
        assertEquals(new CommandResult(FindCommand.MESSAGE_USAGE), helpCommand.parseFind());
        assertEquals(new CommandResult(HelpCommand.MESSAGE_USAGE), helpCommand.parseHelp());
        assertEquals(new CommandResult(ListCommand.MESSAGE_USAGE), helpCommand.parseList());
        assertEquals(new CommandResult(ReminderCommand.MESSAGE_USAGE), helpCommand.parseReminder());
        assertEquals(new CommandResult(SortCommand.MESSAGE_USAGE), helpCommand.parseSort());
        assertEquals(new CommandResult(UndoCommand.MESSAGE_USAGE), helpCommand.parseUndo());
        assertEquals(new CommandResult(ViewTaskListCommand.MESSAGE_USAGE), helpCommand.parseViewTaskList());
        assertEquals(new CommandResult(HelpCommand.DESCRIPTION), helpCommand.parseCommandWord(""));
    }

    @Test
    public void equals() {
        HelpCommand helpCommandOne = new HelpCommand();
        HelpCommand helpCommandTwo = new HelpCommand("add");
        HelpCommand helpCommandThree = new HelpCommand("ls");

        // same object -> returns true
        assertEquals(helpCommandOne, helpCommandOne);
        assertEquals(helpCommandTwo, helpCommandTwo);

        // same values -> return true
        HelpCommand helpCommandOneCopy = new HelpCommand();
        HelpCommand helpCommandTwoCopy = new HelpCommand("add");
        assertEquals(helpCommandOneCopy, helpCommandOne);
        assertEquals(helpCommandTwoCopy, helpCommandTwo);

        // null -> return false;
        assertNotEquals(null, helpCommandOne);
        assertNotEquals(null, helpCommandTwo);

        // different command -> return false
        Command otherCommand = new ClearCommand();
        assertNotEquals(helpCommandOne, otherCommand);
        assertNotEquals(helpCommandTwo, otherCommand);

        // different command words -> return false
        assertNotEquals(helpCommandThree, helpCommandTwo);

        // different show help -> returns false
        assertNotEquals(helpCommandOne, helpCommandTwo);
    }
}
