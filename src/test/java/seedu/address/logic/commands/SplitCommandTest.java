package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DAY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonAvailableOnDayPredicate;
import seedu.address.testutil.PersonBuilder;

public class SplitCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_null_exceptionThrown() {
        // todo
        assertTrue(true);
    }

    @Test
    public void execute_validDay_success() {
        // todo
        SplitCommand command = new SplitCommand(4);
        String expectedMessage = String.format(SplitCommand.MESSAGE_SUCCESS, 4);
        Person person = new PersonBuilder().build();
        model.setPerson(ALICE, person);
        expectedModel.setPerson(ALICE, person);
        PersonAvailableOnDayPredicate predicate = new PersonAvailableOnDayPredicate(1);
        expectedModel.split(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDay_exceptionThrown() {
        String invalidDays = "8 9 10";
        // todo
        assertTrue(true);
    }

    @Test
    public void equals() {
        SplitCommand firstCommand = new SplitCommand(1);
        SplitCommand secondCommand = new SplitCommand(2);

        assertTrue(firstCommand.equals(firstCommand));

        SplitCommand firstCommandCopy = new SplitCommand(1);
        assertTrue(firstCommand.equals(firstCommandCopy));

        assertFalse(firstCommand.equals(secondCommand));
        assertFalse(firstCommand.equals(null));
        assertFalse(firstCommand.equals(1));
    }
}
