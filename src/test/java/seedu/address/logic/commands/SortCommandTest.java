package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code SortCommand}.
 */
public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_emptyAddressBook_success() {
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "[" + PREFIX_NAME + "]");

        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SortCommand(List.of(PREFIX_NAME)), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_singlePrefix_success() {
        // name
        SortCommand sortCommand = new SortCommand(List.of(PREFIX_NAME));

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "[" + PREFIX_NAME + "]");

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(Comparator.comparing(Person::getName));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // case number
        sortCommand = new SortCommand(List.of(PREFIX_CASE_NUMBER));

        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "[" + PREFIX_CASE_NUMBER + "]");

        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(Comparator.comparing(Person::getCaseNumber));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multiplePrefix_success() {
        // name then case number
        SortCommand sortCommand = new SortCommand(List.of(PREFIX_NAME, PREFIX_CASE_NUMBER));

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "[" + PREFIX_NAME + ", "
                + PREFIX_CASE_NUMBER + "]");

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(Comparator.comparing(Person::getCaseNumber)
                .thenComparing(Person::getName));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        // case number then name
        sortCommand = new SortCommand(List.of(PREFIX_CASE_NUMBER, PREFIX_NAME));

        expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "[" + PREFIX_CASE_NUMBER + ", " + PREFIX_NAME
                + "]");

        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(Comparator.comparing(Person::getName)
                .thenComparing(Person::getCaseNumber));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        List<Prefix> firstPrefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        SortCommand sortFirstCommand = new SortCommand(firstPrefixes);

        List<Prefix> secondPrefixes = List.of(PREFIX_CASE_NUMBER, PREFIX_NAME);
        SortCommand sortSecondCommand = new SortCommand(secondPrefixes);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(firstPrefixes);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

}
