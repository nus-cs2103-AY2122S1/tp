package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ReminderCommand.MESSAGE_SET_REMINDER_SUCCESS;
import static seedu.address.logic.commands.ReminderCommand.MESSAGE_SHOW_REMINDER_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

public class ReminderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_showDaysPrior_success() {
        ReminderCommand reminderCommand = new ReminderCommand();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_SHOW_REMINDER_SUCCESS, Task.getDaysPriorToTaskDate());
        assertCommandSuccess(reminderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDaysPrior_success() {
        ReminderCommand reminderCommand = new ReminderCommand(1);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_SET_REMINDER_SUCCESS, 1);
        assertCommandSuccess(reminderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDaysPrior_failure() {
        ReminderCommand reminderCommand = new ReminderCommand(-1);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        String expectedMessage = Messages.MESSAGE_INVALID_REMINDER_DAY_VALUE;
        assertCommandFailure(reminderCommand, expectedModel, expectedMessage);
    }

    @Test
    public void equals() {
        ReminderCommand reminderCommandZeroDaysPrior = new ReminderCommand();
        ReminderCommand reminderCommandNonZeroDaysPrior = new ReminderCommand(1);
        ReminderCommand reminderCommandZeroDaysPriorIsSet = new ReminderCommand(0);

        // same object -> returns true
        assertEquals(reminderCommandZeroDaysPrior, reminderCommandZeroDaysPrior);

        // same values -> returns true
        ReminderCommand reminderCommandZeroDaysPriorCopy = new ReminderCommand();
        ReminderCommand reminderCommandNonZeroDaysPriorCopy = new ReminderCommand(1);
        assertEquals(reminderCommandZeroDaysPrior, reminderCommandZeroDaysPriorCopy);
        assertEquals(reminderCommandNonZeroDaysPrior, reminderCommandNonZeroDaysPriorCopy);

        // different types -> returns false
        Command otherCommand = new DeleteCommand();
        assertNotEquals(otherCommand, reminderCommandNonZeroDaysPriorCopy);

        // null -> returns false
        assertNotEquals(null, reminderCommandNonZeroDaysPriorCopy);

        // different values -> returns false
        assertNotEquals(reminderCommandZeroDaysPrior, reminderCommandNonZeroDaysPrior);
        assertNotEquals(reminderCommandZeroDaysPrior, reminderCommandZeroDaysPriorIsSet);
    }
}
