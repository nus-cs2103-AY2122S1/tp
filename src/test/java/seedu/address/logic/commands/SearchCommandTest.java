package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.DANIEL;
import static seedu.address.testutil.TypicalClients.ELLE;
import static seedu.address.testutil.TypicalClients.FIONA;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.ClientContainsKeywordsPredicate;

class SearchCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ClientContainsKeywordsPredicate firstPredicate =
                new ClientContainsKeywordsPredicate(
                        ArgumentTokenizer.tokenize("abc def a/Blk 30 e/example.com"));
        ClientContainsKeywordsPredicate secondPredicate =
                new ClientContainsKeywordsPredicate(ArgumentTokenizer.tokenize("abc a/Blk 30"));

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

        // different client -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noClientFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 0);
        ClientContainsKeywordsPredicate predicate = preparePredicate("e/not_example.com");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_multipleKeywords_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 3);
        ClientContainsKeywordsPredicate predicate = preparePredicate("Pauline Kurz Elle Kunz p/94 e/example.com");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA), model.getFilteredClientList());
    }

    @Test
    public void execute_tagKeywords_clientsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 3);
        ClientContainsKeywordsPredicate predicate = preparePredicate("t/friends");
        SearchCommand command = new SearchCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredClientList());
    }

    private ClientContainsKeywordsPredicate preparePredicate(String s) {
        ArgumentMultimap aMM = ArgumentTokenizer.tokenize(" " + s, ALL_PREFIXES);
        return new ClientContainsKeywordsPredicate(aMM);
    }
}
