package dash.logic.commands.personcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.logic.commands.personcommand.FindPersonCommand.FindPersonDescriptor;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserPrefs;
import dash.model.person.NameContainsKeywordsPredicate;
import dash.model.task.TaskList;
import dash.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(), new TaskList());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(),
            new TaskList());

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
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindPersonDescriptor predicate = preparePredicate("Kurz");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate.combinePredicates());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.CARL), model.getFilteredPersonList());
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
