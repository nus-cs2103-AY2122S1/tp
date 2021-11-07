package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.AIRZONE;
import static seedu.address.testutil.TypicalContacts.BATTLEBOX;
import static seedu.address.testutil.TypicalContacts.CARLTON;
import static seedu.address.testutil.TypicalContacts.FUKTAKCHI;
import static seedu.address.testutil.TypicalContacts.GSEA;
import static seedu.address.testutil.TypicalContacts.HOTEL_SOLOHA;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.IsFindableContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        IsFindableContainsKeywordsPredicate firstPredicate =
                new IsFindableContainsKeywordsPredicate(Collections.singletonList("first"));
        IsFindableContainsKeywordsPredicate secondPredicate =
                new IsFindableContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different Contact -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_singleFullNameKeyword_singleContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate("Eco");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GSEA), model.getFilteredContactList());
    }

    @Test
    public void execute_singleFullAddressKeyword_singleContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate("michegan");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GSEA), model.getFilteredContactList());
    }

    @Test
    public void execute_singleFullPhoneKeyword_singleContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate("9482224");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GSEA), model.getFilteredContactList());
    }

    @Test
    public void execute_singleFullReviewKeyword_singleContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate("bad");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARLTON), model.getFilteredContactList());
    }

    @Test
    public void execute_singleFullEmailKeyword_singleContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate("airzone@example.com");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AIRZONE), model.getFilteredContactList());
    }

    @Test
    public void execute_singleFullKeyword_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate("Museum");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BATTLEBOX, FUKTAKCHI), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleFullKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate("carlton gsea tokyo");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARLTON, GSEA, HOTEL_SOLOHA), model.getFilteredContactList());
    }

    @Test
    public void execute_singleNonFullKeyword_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate("Mu");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BATTLEBOX, FUKTAKCHI), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleNonFullKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        IsFindableContainsKeywordsPredicate predicate = preparePredicate("carl ho");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARLTON, HOTEL_SOLOHA), model.getFilteredContactList());
    }

    /**
     * Parses {@code userInput} into a {@code IsFindableContainsKeywordsPredicate}.
     */
    private IsFindableContainsKeywordsPredicate preparePredicate(String userInput) {
        return new IsFindableContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
