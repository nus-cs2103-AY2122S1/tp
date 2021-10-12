package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.KEYWORD_MATCHING_MEIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand.FindCondition;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonMatchesKeywordsPredicate;
import seedu.address.testutil.PersonMatchesKeywordsPredicateBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonMatchesKeywordsPredicate firstPredicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName(VALID_NAME_AMY).build();
        PersonMatchesKeywordsPredicate secondPredicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName(VALID_NAME_BOB).build();

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

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneField_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder()
                .withName(KEYWORD_MATCHING_MEIER).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleFieldsAllCondition_multiplePersonsFound() {
        // Default condition
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder().withAddress("ave")
                .withTags("friends").build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());

        // Match all condition
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        predicate = new PersonMatchesKeywordsPredicateBuilder().withAddress("ave").withTags("friends")
                .withCondition(FindCommand.FindCondition.ALL).build();
        command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleFieldsAnyCondition_multiplePersonsFound() {
        // Match any condition
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder().withAddress("ave")
                .withTags("friends").withCondition(FindCommand.FindCondition.ANY).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleFieldsNoneCondition_multiplePersonsFound() {
        // Match none condition
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicateBuilder().withAddress("ave")
                .withTags("friends").withCondition(FindCommand.FindCondition.NONE).build();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void executeFindConditionValueOfName_mixedCase_success() {
        assertEquals(FindCondition.ALL, FindCondition.valueOfName("AlL"));
        assertEquals(FindCondition.ANY, FindCondition.valueOfName("aNY"));
        assertEquals(FindCondition.NONE, FindCondition.valueOfName("NoNe"));
    }
}
