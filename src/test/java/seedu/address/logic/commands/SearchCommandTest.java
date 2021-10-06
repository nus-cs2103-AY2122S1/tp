package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

class SearchCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate(
                        ArgumentTokenizer.tokenize("abc def a/Blk 30 e/example.com"));
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(ArgumentTokenizer.tokenize("abc a/Blk 30"));

        SearchCommand searchFirstCommand = new SearchCommand(firstPredicate);
        SearchCommand searchSecondCommand = new SearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchCommand findSearchCommandCopy = new SearchCommand(firstPredicate);
        assertTrue(searchFirstCommand.equals(findSearchCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsKeywordsPredicate predicate = preparePredicate("* e/not_example.com");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate predicate = preparePredicate("Pauline Kurz Elle Kunz p/94 e/example.com");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA), model.getFilteredPersonList());
    }

    private PersonContainsKeywordsPredicate preparePredicate(String s) {
        ArgumentMultimap aMM = ArgumentTokenizer.tokenize(s,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        return new PersonContainsKeywordsPredicate(aMM);
    }
}
