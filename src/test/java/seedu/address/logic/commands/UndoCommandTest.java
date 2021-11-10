package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDate.getTypicalCountdown;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Shortcut;
import seedu.address.model.UserPrefs;


public class UndoCommandTest {

    private Model model;
    private Model expectedModel;
    private UndoCommand commandToUndo;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(), new UserPrefs(), new Shortcut());
        expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(), new UserPrefs(), new Shortcut());
        commandToUndo = new UndoCommand();
    }

    @Test
    public void execute_cannotUndo() {
        HelpCommand help = new HelpCommand();
        help.execute(model);
        commandToUndo.setPrevCommand(help);
        assertCommandSuccess(commandToUndo, model, UndoCommand.MESSAGE_CANNOT_UNDO, expectedModel);
    }

    @Test
    public void execute_undoNotImplemented() {
        AddShortcutCommand shortcut = new AddShortcutCommand("random", "c/delete 1");
        shortcut.execute(model);
        commandToUndo.setPrevCommand(shortcut);
        assertCommandSuccess(commandToUndo, model, UndoCommand.MESSAGE_UNDO_NOT_IMPLEMENTED, expectedModel);
    }

    @Test
    public void execute_invalidPrevCommand() {
        commandToUndo.setPrevCommand(null);
        assertCommandSuccess(commandToUndo, model, UndoCommand.MESSAGE_INVALID_PREV_COMMAND, expectedModel);
    }

    @Test
    public void execute_undoSuccess() {
        try {
            DeleteCommand delete = new DeleteCommand(INDEX_FIRST_PERSON);
            delete.execute(model);
            commandToUndo.setPrevCommand(delete);
            assertCommandSuccess(commandToUndo, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
