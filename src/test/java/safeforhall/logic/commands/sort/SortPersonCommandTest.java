package safeforhall.logic.commands.sort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;
import static safeforhall.logic.commands.sort.SortPersonCommand.ASCENDING;
import static safeforhall.logic.commands.sort.SortPersonCommand.DESCENDING;
import static safeforhall.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.testutil.TypicalPersons;

public class SortPersonCommandTest {

    @Test
    public void constructor_nullField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortPersonCommand(null, ASCENDING));
    }

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortPersonCommand(Name.FIELD, null));
    }

    @Test
    public void getComparator_invalidField_throwsCommandException() {
        SortPersonCommand invalidFieldCommand = new SortPersonCommand("z", ASCENDING);
        assertThrows(CommandException.class, SortPersonCommand.ALLOWED_FIELDS, () ->
                invalidFieldCommand.getComparator());
    }

    @Test
    public void getComparator_invalidOrder_throwsCommandException() {
        SortPersonCommand invalidFieldCommand = new SortPersonCommand(Name.FIELD, "c");
        assertThrows(CommandException.class, SortPersonCommand.ALLOWED_ORDER, () ->
                invalidFieldCommand.getComparator());
    }

    @Test
    public void execute_listIsNotSorted_showsSortedList() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Comparator<Person> nameComparator = Comparator.comparing(Person::getName);
        expectedModel.updateSortedPersonList(nameComparator);

        assertCommandSuccess(new SortPersonCommand(Name.FIELD, ASCENDING), model,
                SortPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSorted_showsSameList() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Comparator<Person> nameComparator = Comparator.comparing(Person::getName);
        expectedModel.updateSortedPersonList(nameComparator);
        model.updateSortedPersonList(nameComparator);

        assertCommandSuccess(new SortPersonCommand(Name.FIELD, ASCENDING), model,
                SortPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        SortPersonCommand sortEmailAscendingCommand = new SortPersonCommand(Email.FIELD, ASCENDING);
        SortPersonCommand sortEmailDescendingCommand = new SortPersonCommand(Email.FIELD, DESCENDING);
        SortPersonCommand sortFacultyDescendingCommand = new SortPersonCommand(Faculty.FIELD, DESCENDING);

        // same object -> returns true
        assertTrue(sortEmailAscendingCommand.equals(sortEmailAscendingCommand));

        // same values -> returns true
        SortPersonCommand sortEmailAscendingCommandCopy = new SortPersonCommand(Email.FIELD, ASCENDING);
        assertTrue(sortEmailAscendingCommand.equals(sortEmailAscendingCommandCopy));

        // different types -> returns false
        assertFalse(sortEmailAscendingCommand.equals(1));

        // null -> returns false
        assertFalse(sortEmailAscendingCommand.equals(null));

        // different order -> returns false
        assertFalse(sortEmailAscendingCommand.equals(sortEmailDescendingCommand));

        // different field -> returns false
        assertFalse(sortEmailAscendingCommand.equals(sortFacultyDescendingCommand));
    }

}
