package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicate.GenderContainsKeywordsPredicate;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicate.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code DeleteMultipleCommand}.
 */
public class DeleteMultipleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PhoneContainsKeywordsPredicate firstPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("first"));
        PhoneContainsKeywordsPredicate secondPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("second"));

        DeleteMultipleCommand deleteFirstMultipleCommand = new DeleteMultipleCommand(firstPredicate);
        DeleteMultipleCommand deleteSecondMultipleCommand = new DeleteMultipleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(deleteFirstMultipleCommand.equals(deleteFirstMultipleCommand));

        // same values -> returns true
        DeleteMultipleCommand deleteFirstMultipleCommandCopy = new DeleteMultipleCommand(firstPredicate);
        assertTrue(deleteFirstMultipleCommand.equals(deleteFirstMultipleCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstMultipleCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstMultipleCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstMultipleCommand.equals(deleteSecondMultipleCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonDeleted() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, 0);
        EmailContainsKeywordsPredicate predicate = prepareEmailPredicate("qedn@4%#ksdwf*c");
        DeleteMultipleCommand command = new DeleteMultipleCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneNameKeyword_multiplePersonsDeleted() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Meier");
        DeleteMultipleCommand command = new DeleteMultipleCommand(predicate);
        expectedModel.deletePerson(BENSON);
        expectedModel.deletePerson(DANIEL);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsDeleted() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Alice Benson");
        DeleteMultipleCommand command = new DeleteMultipleCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneEmailKeyword_multiplePersonsDeleted() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, 7);
        EmailContainsKeywordsPredicate predicate = prepareEmailPredicate("@example.com");
        DeleteMultipleCommand command = new DeleteMultipleCommand(predicate);
        expectedModel.deletePerson(ALICE);
        expectedModel.deletePerson(BENSON);
        expectedModel.deletePerson(CARL);
        expectedModel.deletePerson(DANIEL);
        expectedModel.deletePerson(ELLE);
        expectedModel.deletePerson(FIONA);
        expectedModel.deletePerson(GEORGE);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleEmailKeywords_multiplePersonsDeleted() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, 0);
        EmailContainsKeywordsPredicate predicate = prepareEmailPredicate("johnd heinz cornelia");
        DeleteMultipleCommand command = new DeleteMultipleCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneGenderKeyword_multiplePersonsDeleted() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, 3);
        GenderContainsKeywordsPredicate predicate = prepareGenderPredicate("F");
        DeleteMultipleCommand command = new DeleteMultipleCommand(predicate);
        expectedModel.deletePerson(ALICE);
        expectedModel.deletePerson(ELLE);
        expectedModel.deletePerson(FIONA);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, DANIEL, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleGenderKeyword_multiplePersonsDeleted() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, 0);
        GenderContainsKeywordsPredicate predicate = prepareGenderPredicate("F M");
        DeleteMultipleCommand command = new DeleteMultipleCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTagKeyword_multiplePersonsDeleted() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, 3);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("friends");
        DeleteMultipleCommand command = new DeleteMultipleCommand(predicate);
        expectedModel.deletePerson(ALICE);
        expectedModel.deletePerson(BENSON);
        expectedModel.deletePerson(DANIEL);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTagKeywords_multiplePersonsDeleted() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PERSONS_DELETED_OVERVIEW, 1);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("owesMoney friends");
        DeleteMultipleCommand command = new DeleteMultipleCommand(predicate);
        expectedModel.deletePerson(BENSON);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EmailContainsKeywordsPredicate}.
     */
    private EmailContainsKeywordsPredicate prepareEmailPredicate(String userInput) {
        return new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code GenderContainsKeywordsPredicate}.
     */
    private GenderContainsKeywordsPredicate prepareGenderPredicate(String userInput) {
        return new GenderContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
