package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fast.testutil.TypicalPersons.ALICE;
import static seedu.fast.testutil.TypicalPersons.BENSON;
import static seedu.fast.testutil.TypicalPersons.CARL;
import static seedu.fast.testutil.TypicalPersons.DANIEL;
import static seedu.fast.testutil.TypicalPersons.ELLE;
import static seedu.fast.testutil.TypicalPersons.FIONA;
import static seedu.fast.testutil.TypicalPersons.GRABAHAN;
import static seedu.fast.testutil.TypicalPersons.JOE;
import static seedu.fast.testutil.TypicalPersons.SUGON;
import static seedu.fast.testutil.TypicalPersons.getTypicalFast;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.fast.model.Model;
import seedu.fast.model.ModelManager;
import seedu.fast.model.UserPrefs;
import seedu.fast.model.person.NameContainsKeywordsPredicate;
import seedu.fast.model.person.PriorityPredicate;
import seedu.fast.model.person.RemarkContainsKeyWordsPredicate;
import seedu.fast.model.person.TagContainsKeyWordsPredicate;
import seedu.fast.model.tag.PriorityTag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalFast(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFast(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        PriorityPredicate thirdPredicate =
                new PriorityPredicate(Collections.singletonList("high"));
        PriorityPredicate fourthPredicate =
                new PriorityPredicate(Collections.singletonList("low"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);
        FindCommand findThirdCommand = new FindCommand(thirdPredicate);
        FindCommand findFourthCommand = new FindCommand(fourthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        assertTrue(findThirdCommand.equals(findThirdCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        FindCommand findThirdCommandCopy = new FindCommand(thirdPredicate);
        assertTrue(findThirdCommand.equals(findThirdCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findThirdCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findThirdCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
        assertFalse(findThirdCommand.equals(findFourthCommand));

        //different predicate type -> returns false
        assertFalse(findFirstCommand.equals(findThirdCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroPriorities_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PriorityPredicate predicate = preparePriorityPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePriorities_multiplePersonsFound() {
        model.addPerson(JOE);
        model.addPerson(GRABAHAN);
        expectedModel.addPerson(JOE);
        expectedModel.addPerson(GRABAHAN);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PriorityPredicate predicate = preparePriorityPredicate(
                PriorityTag.LowPriority.TERM + " "
                        + PriorityTag.HighPriority.TERM);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JOE, GRABAHAN), model.getFilteredPersonList());
    }

    @Test
    public void execute_onePriority_onePersonFound() {
        model.addPerson(JOE);
        model.addPerson(GRABAHAN);
        expectedModel.addPerson(JOE);
        expectedModel.addPerson(GRABAHAN);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PriorityPredicate predicate = preparePriorityPredicate(
                PriorityTag.LowPriority.TERM);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GRABAHAN), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagContainsKeyWordsPredicate predicate =
                prepareTagContainsKeyWordsPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTag_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        TagContainsKeyWordsPredicate predicate =
                prepareTagContainsKeyWordsPredicate("owesMoney");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_twoTags_threePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        TagContainsKeyWordsPredicate predicate =
                prepareTagContainsKeyWordsPredicate("friends");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroRemarks_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        RemarkContainsKeyWordsPredicate predicate =
                prepareRemarkContainsKeyWordsPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneRemark_onePersonFound() {
        model.addPerson(SUGON);
        expectedModel.addPerson(SUGON);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        RemarkContainsKeyWordsPredicate predicate =
                prepareRemarkContainsKeyWordsPredicate("what's DN?");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SUGON), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PriorityPredicate}.
     */
    private PriorityPredicate preparePriorityPredicate(String userInput) {
        return new PriorityPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeyWordsPredicate}.
     */
    private TagContainsKeyWordsPredicate prepareTagContainsKeyWordsPredicate(String userInput) {
        return new TagContainsKeyWordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code RemarkContainsKeyWordsPredicate}.
     */
    private RemarkContainsKeyWordsPredicate prepareRemarkContainsKeyWordsPredicate(String userInput) {
        return new RemarkContainsKeyWordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
