package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FindCommand.MESSAGE_NO_MATCHING;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.GRAB;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.CompletionContainsKeywordsPredicate;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.PositionContainsKeywordsPredicate;
import seedu.address.model.application.PriorityContainsKeywordsPredicate;
import seedu.address.model.application.RequirementsContainsKeywordsPredicate;
import seedu.address.model.application.StatusContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    private Model model = new ModelManager(getTypicalInternship(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternship(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroNameKeywords_noApplicationFound() {
        String expectedMessage = MESSAGE_NO_MATCHING;
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicationList());
    }

    @Test
    public void execute_zeroPositionKeywords_noApplicationFound() {
        String expectedMessage = MESSAGE_NO_MATCHING;
        PositionContainsKeywordsPredicate predicate = preparePositionPredicate(" ");

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicationList());
    }

    @Test
    public void execute_zeroCompletionKeywords_noApplicationFound() {
        String expectedMessage = MESSAGE_NO_MATCHING;
        CompletionContainsKeywordsPredicate predicate = prepareCompletionPredicate(" ");

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicationList());
    }

    @Test
    public void execute_zeroStatusKeywords_noApplicationFound() {
        String expectedMessage = MESSAGE_NO_MATCHING;
        StatusContainsKeywordsPredicate predicate = prepareStatusPredicate(" ");

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicationList());
    }

    @Test
    public void execute_zeroRequirementKeywords_noApplicationFound() {
        String expectedMessage = MESSAGE_NO_MATCHING;
        RequirementsContainsKeywordsPredicate predicate = prepareRequirementsPredicate(" ");

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicationList());
    }

    @Test
    public void execute_zeroPriorityKeywords_noApplicationFound() {
        String expectedMessage = MESSAGE_NO_MATCHING;
        PriorityContainsKeywordsPredicate predicate = preparePriorityPredicate(" ");

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicationList());
    }

    @Test
    public void execute_multipleKeywords_multipleApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 3, "applications");
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Amazon Bytedance Grab");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMAZON, BYTEDANCE, GRAB), model.getFilteredApplicationList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PositionContainsKeywordsPredicate}.
     */
    private PositionContainsKeywordsPredicate preparePositionPredicate(String userInput) {
        return new PositionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code CompletionContainsKeywordsPredicate}.
     */
    private CompletionContainsKeywordsPredicate prepareCompletionPredicate(String userInput) {
        return new CompletionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code StatusContainsKeywordsPredicate}.
     */
    private StatusContainsKeywordsPredicate prepareStatusPredicate(String userInput) {
        return new StatusContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RequirementsContainsKeywordsPredicate}.
     */
    private RequirementsContainsKeywordsPredicate prepareRequirementsPredicate(String userInput) {
        return new RequirementsContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PriorityContainsKeywordsPredicate}.
     */
    private PriorityContainsKeywordsPredicate preparePriorityPredicate(String userInput) {
        return new PriorityContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
