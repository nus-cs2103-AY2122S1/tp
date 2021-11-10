package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDate.getTypicalCountdown;
import static seedu.address.testutil.TypicalPersons.VALID_COMMAND;
import static seedu.address.testutil.TypicalPersons.VALID_KEY;
import static seedu.address.testutil.TypicalPersons.getRemovedShortcut;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalShortcut;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RemoveShortcutCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), getTypicalShortcut());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), getRemovedShortcut());
    @Test
    public void constructor_nullKey_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveShortcutCommand(null));
    }

    @Test
    public void execute_removeValid_success() {
        String expectedOutput = String.format(RemoveShortcutCommand.SHORTCUT_REMOVE_SUCCESS, VALID_KEY, VALID_COMMAND);
        assertCommandSuccess(new RemoveShortcutCommand(VALID_KEY), model, expectedOutput, expectedModel);
    }

    @Test
    public void execute_shortcutDoesNotExist_failure() {
        String expectedError = String.format(ShortcutCommand.SHORTCUT_NOT_FOUND, "9");
        assertCommandFailure(new RemoveShortcutCommand("9"), model, expectedError);
    }

    @Test
    public void equals() {
        RemoveShortcutCommand removescA = new RemoveShortcutCommand("A");
        RemoveShortcutCommand removescB = new RemoveShortcutCommand("B");

        // same object -> returns true
        assertTrue(removescA.equals(removescA));

        // same values -> returns true
        RemoveShortcutCommand removescACopy = new RemoveShortcutCommand("A");
        assertTrue(removescA.equals(removescACopy));

        // different types -> returns false
        assertFalse(removescA.equals(1));

        // null -> returns false
        assertFalse(removescA.equals(null));

        // different person -> returns false
        assertFalse(removescA.equals(removescB));
    }
}
