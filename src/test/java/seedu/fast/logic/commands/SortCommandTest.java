package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fast.testutil.TypicalPersons.CARL;
import static seedu.fast.testutil.TypicalPersons.ELLE;
import static seedu.fast.testutil.TypicalPersons.FIONA;
import static seedu.fast.testutil.TypicalPersons.getTypicalFast;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.util.sort.SortByAppointment;
import seedu.fast.commons.util.sort.SortByName;
import seedu.fast.commons.util.sort.SortByPriority;
import seedu.fast.model.Model;
import seedu.fast.model.ModelManager;
import seedu.fast.model.UserPrefs;
import seedu.fast.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalFast(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFast(), new UserPrefs());

    @Test
    public void equals() {
        SortByName sortByName = new SortByName();
        SortByPriority sortByPriority = new SortByPriority();
        SortByAppointment sortByAppointment = new SortByAppointment();

        SortCommand sortNameCommand = new SortCommand(sortByName, SortByName.KEYWORD);
        SortCommand sortPriorityCommand = new SortCommand(sortByPriority, SortByPriority.KEYWORD);
        SortCommand sortAppointmentCommand = new SortCommand(sortByAppointment, sortByAppointment.KEYWORD);

        // same object -> returns true
        assertTrue(sortNameCommand.equals(sortNameCommand));

        // same values -> returns true
        SortCommand sortPriorityCommandCopy = new SortCommand(sortByPriority, SortByPriority.KEYWORD);
        assertTrue(sortPriorityCommand.equals(sortPriorityCommandCopy));

        // different types -> returns false
        assertFalse(sortAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(sortAppointmentCommand.equals(null));

        // different person -> returns false
        assertFalse(sortPriorityCommand.equals(sortNameCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindCommand command = new FindCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
//        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
//        FindCommand command = new FindCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }
}
