package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import org.junit.jupiter.api.Test;

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
    public void execute_validDay_success() {
        SplitCommand command = new SplitCommand(4);
        String expectedMessage = String.format(SplitCommand.MESSAGE_SUCCESS,
                DayOfWeek.of(4).getDisplayName(TextStyle.FULL, Locale.getDefault()));
        Person person = new PersonBuilder().build();
        model.setPerson(ALICE, person);
        expectedModel.setPerson(ALICE, person);
        PersonAvailableOnDayPredicate predicate = new PersonAvailableOnDayPredicate(1);
        expectedModel.split(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
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
