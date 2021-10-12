package dash.logic.commands.taskcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.logic.commands.taskcommand.FindTaskCommand.FindTaskDescriptor;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserPrefs;
import dash.model.task.DescriptionContainsKeywordsPredicate;
import dash.model.task.TagTaskContainsKeywordsPredicate;
import dash.testutil.TypicalPersons;
import dash.testutil.TypicalTasks;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindTaskCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(),
            TypicalTasks.getTypicalTaskList());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(),
            TypicalTasks.getTypicalTaskList());

    @Test
    public void equals() {
        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("ST2334"));
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("quiz"));
        TagTaskContainsKeywordsPredicate thirdPredicate =
                new TagTaskContainsKeywordsPredicate(Collections.singletonList("quiz"));

        FindTaskDescriptor firstFindTaskDescriptor = new FindTaskDescriptor();
        firstFindTaskDescriptor.setDesc(Collections.singletonList("ST2334"));
        firstFindTaskDescriptor.setTags(Collections.singletonList("quiz"));

        FindTaskDescriptor secondFindPersonDescriptor = new FindTaskDescriptor();
        secondFindPersonDescriptor.setDesc(Collections.singletonList("MA1101R"));


        // same object -> returns true
        assertTrue(firstFindTaskDescriptor.equals(firstFindTaskDescriptor));

        // same values -> returns true
        FindTaskDescriptor firstFindTaskDescriptorCopy = new FindTaskDescriptor();
        firstFindTaskDescriptorCopy.setDesc(Collections.singletonList("ST2334"));
        firstFindTaskDescriptorCopy.setTags(Collections.singletonList("quiz"));
        assertTrue(firstFindTaskDescriptor.equals(firstFindTaskDescriptorCopy));

        // different types -> returns false
        assertFalse(firstFindTaskDescriptor.equals(1));

        // null -> returns false
        assertFalse(firstFindTaskDescriptor.equals(null));

        // different person -> returns false
        assertFalse(firstFindTaskDescriptor.equals(secondFindPersonDescriptor));
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        FindTaskDescriptor predicate = preparePredicate("ST2334 quiz");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate.combinePredicates());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalTasks.QUIZ), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private FindTaskDescriptor preparePredicate(String userInput) {
        FindTaskDescriptor findTaskDescriptorPrepare = new FindTaskDescriptor();
        findTaskDescriptorPrepare.setDesc(Arrays.asList(userInput.split("\\s+")));
        return findTaskDescriptorPrepare;
    }
}
