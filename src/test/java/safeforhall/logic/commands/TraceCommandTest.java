package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandFailure;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.testutil.TypicalAddressBook;
import safeforhall.testutil.TypicalPersons;

public class TraceCommandTest {
    private Model model = new ModelManager(TypicalAddressBook.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalAddressBook.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TraceCommand command1 = new TraceCommand("safeforhall");
        TraceCommand command2 = new TraceCommand("residents");
        TraceCommand commandWithDepth = new TraceCommand("residents", 1);
        TraceCommand commandWithDepthWithDuration = new TraceCommand("residents", 1, 7);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        TraceCommand command3 = new TraceCommand("safeforhall");
        assertTrue(command1.equals(command3));

        assertTrue(commandWithDepth.equals(commandWithDepthWithDuration));
        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different person -> returns false
        assertFalse(command1.equals(command2));
    }

    @Test
    public void execute_command_success() throws CommandException {
        String expectedMessage = String.format(TraceCommand.MESSAGE_FOUND_CONTACTS, 0);
        TraceCommand command = new TraceCommand(TypicalPersons.GEORGE.getRoom().toString(), 1);
        expectedModel.updateFilteredPersonList(e -> false);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidPersonInfo_fail() {
        TraceCommand command = new TraceCommand("E123");
        assertCommandFailure(command, model, "No resident with this information 'E123' could be found");
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_depthCheck_success() throws CommandException {
        String expectedMessage = String.format(TraceCommand.MESSAGE_FOUND_CONTACTS, 4);
        TraceCommand command = new TraceCommand(TypicalPersons.GEORGE.getRoom().toString(), 2, 1000);
        expectedModel.updateFilteredPersonList(p -> p.equals(TypicalPersons.BENSON)
                || p.equals(TypicalPersons.CARL)
                || p.equals(TypicalPersons.ELLE)
                || p.equals(TypicalPersons.FIONA));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.BENSON, TypicalPersons.CARL,
                TypicalPersons.ELLE, TypicalPersons.FIONA), model.getFilteredPersonList());
    }
}
