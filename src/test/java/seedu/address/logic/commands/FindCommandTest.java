package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ITEMS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.CHOCOCHIP;
import static seedu.address.testutil.TypicalItems.DALGONA_COFFEE;
import static seedu.address.testutil.TypicalItems.EGGNOG;
import static seedu.address.testutil.TypicalItems.FOREST_CAKE;

import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.IdContainsNumberPredicate;
import seedu.address.model.item.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        IdContainsNumberPredicate firstIdPredicate =
                new IdContainsNumberPredicate(Collections.singletonList("#140272"));
        IdContainsNumberPredicate secondIdPredicate =
                new IdContainsNumberPredicate(Collections.singletonList("#475272"));


        FindCommand findNameFirstCommand = new FindCommand(firstNamePredicate);
        FindCommand findNameSecondCommand = new FindCommand(secondNamePredicate);
        FindCommand findIdFirstCommand = new FindCommand(firstIdPredicate);
        FindCommand findIdSecondCommand = new FindCommand(secondIdPredicate);


        // same Name type-> returns true
        assertTrue(findNameFirstCommand.equals(findNameFirstCommand));

        // same Id type-> returns true
        assertTrue(findIdFirstCommand.equals(findIdFirstCommand));

        // same Name values -> returns true
        FindCommand findNameFirstCommandCopy = new FindCommand(firstNamePredicate);
        assertTrue(findNameFirstCommand.equals(findNameFirstCommandCopy));

        // same Id values -> returns true
        FindCommand findIdFirstCommandCopy = new FindCommand(firstIdPredicate);
        assertTrue(findIdFirstCommand.equals(findIdFirstCommandCopy));

        // different Name types -> returns false
        assertFalse(findNameFirstCommand.equals(1));

        // different Id types -> returns false
        assertFalse(findIdFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findNameFirstCommand.equals(null));

        // null -> returns false
        assertFalse(findIdFirstCommand.equals(null));

        // different Name -> returns false
        assertFalse(findNameFirstCommand.equals(findNameSecondCommand));

        // different Id -> returns false
        assertFalse(findIdFirstCommand.equals(findIdSecondCommand));
    }

    @Test
    public void execute_zeroNameKeywords_noItemFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicateName(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItemList());
    }

    @Test
    public void execute_zeroIdKeywords_noItemFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 0);
        IdContainsNumberPredicate predicate = preparePredicateId(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItemList());
    }

    @Test
    public void execute_multipleNameKeywords_multipleItemsFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicateName("Chocolate Egg Forest");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHOCOCHIP, EGGNOG, FOREST_CAKE), model.getFilteredItemList());
    }

    @Test
    public void execute_multipleIdKeywords_multipleItemsFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 2);
        IdContainsNumberPredicate predicate = preparePredicateId("#444444 #555555");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItemList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHOCOCHIP, DALGONA_COFFEE), model.getFilteredItemList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicateName(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code IdContainsKeywordsPredicate}.
     */
    private IdContainsNumberPredicate preparePredicateId(String userInput) {
        return new IdContainsNumberPredicate(Arrays.asList(userInput.split("\\s+")));
    }


}
