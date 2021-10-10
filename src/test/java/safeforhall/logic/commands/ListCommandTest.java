package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.NameNearLastDatePredicate;
import safeforhall.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ListCommand firstListCommand = new ListCommand("f", new LastDate("10-10-2021"));
        ListCommand secondListCommand = new ListCommand("c", new LastDate("12-10-2021"));

        // same object -> returns true
        assertTrue(firstListCommand.equals(firstListCommand));

        // same values -> returns true
        ListCommand findFirstCommandCopy = new ListCommand("f", new LastDate("10-10-2021"));
        assertTrue(firstListCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(firstListCommand.equals(1));

        // null -> returns false
        assertFalse(firstListCommand.equals(null));

        // different person -> returns false
        assertFalse(firstListCommand.equals(secondListCommand));
    }

    @Test
    public void execute_oneLastDateFet() {
        String expectedMessage = ListCommand.MESSAGE_SUCCESS_FET;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("f", new LastDate("10-10-2021"));
        ListCommand command = new ListCommand("f", new LastDate("10-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneLastDateCollection() {
        String expectedMessage = ListCommand.MESSAGE_SUCCESS_ART;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("c", new LastDate("10-10-2021"));
        ListCommand command = new ListCommand("c", new LastDate("10-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_twoLastDateFet() {
        String expectedMessage = ListCommand.MESSAGE_SUCCESS_FET;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("f", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        ListCommand command = new ListCommand("f", new LastDate("10-10-2021"), new LastDate("15-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_twoLastDateCollection() {
        String expectedMessage = ListCommand.MESSAGE_SUCCESS_ART;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("c", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        ListCommand command = new ListCommand("c", new LastDate("10-10-2021"), new LastDate("15-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
}
