package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.personcommand.FindPersonCommand;
import seedu.address.logic.commands.personcommand.FindPersonCommand.FindPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.task.TaskList;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPersonDescriptor firstFindPersonDescriptor = new FindPersonDescriptor();
        firstFindPersonDescriptor.setName(Collections.singletonList("first"));

        FindPersonDescriptor secondFindPersonDescriptor = new FindPersonDescriptor();
        secondFindPersonDescriptor.setName(Collections.singletonList("second"));

        // same object -> returns true
        assertTrue(firstFindPersonDescriptor.equals(firstFindPersonDescriptor));

        // same values -> returns true
        FindPersonDescriptor findFirstFindPersonDescriptorCopy = new FindPersonDescriptor();
        findFirstFindPersonDescriptorCopy.setName(Collections.singletonList("first"));
        assertTrue(firstFindPersonDescriptor.equals(findFirstFindPersonDescriptorCopy));

        // different types -> returns false
        assertFalse(firstFindPersonDescriptor.equals(1));

        // null -> returns false
        assertFalse(firstFindPersonDescriptor.equals(null));

        // different person -> returns false
        assertFalse(firstFindPersonDescriptor.equals(secondFindPersonDescriptor));
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPersonDescriptor predicate = preparePredicate("Kurz");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate.combinePredicates());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FindPersonDescriptor preparePredicate(String userInput) {
        FindPersonDescriptor findPersonDescriptorPrepare = new FindPersonDescriptor();
        findPersonDescriptorPrepare.setName(Arrays.asList(userInput.split("\\s+")));
        return findPersonDescriptorPrepare;
    }
}
