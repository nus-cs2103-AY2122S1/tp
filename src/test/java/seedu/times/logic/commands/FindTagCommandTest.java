package seedu.times.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.testutil.TypicalTimestable.ALICE;
import static seedu.times.testutil.TypicalTimestable.BENSON;
import static seedu.times.testutil.TypicalTimestable.DANIEL;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.UserPrefs;
import seedu.times.model.person.predicates.TagsContainsKeywordsPredicate;

public class FindTagCommandTest {
    private Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTimesTable(), new UserPrefs());

    @Test
    public void equals() {
        TagsContainsKeywordsPredicate firstPredicate =
                new TagsContainsKeywordsPredicate(Collections.singletonList("Maths"));
        TagsContainsKeywordsPredicate secondPredicate =
                new TagsContainsKeywordsPredicate(Collections.singletonList("Physics"));

        FindTagCommand findMathsCommand = new FindTagCommand(firstPredicate);
        FindTagCommand findPhysicsCommand = new FindTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findMathsCommand.equals(findMathsCommand));

        // same values -> returns true
        FindTagCommand findMathsCommandCopy = new FindTagCommand(firstPredicate);
        assertTrue(findMathsCommand.equals(findMathsCommandCopy));

        // different types -> returns false
        assertFalse(findMathsCommand.equals(1));

        // null -> returns false
        assertFalse(findMathsCommand.equals(null));

        // different predicate with different list of keywords -> returns false
        assertFalse(findMathsCommand.equals(findPhysicsCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagsContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTagCommand command = new FindTagCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        TagsContainsKeywordsPredicate predicate = preparePredicate("Maths");
        FindTagCommand command = new FindTagCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, DANIEL), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        TagsContainsKeywordsPredicate predicate = preparePredicate("maths physics");
        FindTagCommand command = new FindTagCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code TagsContainsKeywordsPredicate}.
     */
    private TagsContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagsContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
