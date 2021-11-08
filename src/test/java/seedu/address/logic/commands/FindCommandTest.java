package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_ITEMS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FindCommand.MESSAGE_INVENTORY_NOT_DISPLAYED;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.BANANA_MUFFIN;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.BookKeeping;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TransactionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.display.DisplayMode;
import seedu.address.model.item.IdContainsNumberPredicate;
import seedu.address.model.item.Item;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.item.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs(),
            new TransactionList(), new BookKeeping());
    private Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
            new TransactionList(), new BookKeeping());

    private NameContainsKeywordsPredicate pieNamePredicate =
            new NameContainsKeywordsPredicate(List.of(APPLE_PIE.getName().fullName));
    private IdContainsNumberPredicate pieIdPredicate =
            new IdContainsNumberPredicate(List.of(APPLE_PIE.getId()));
    private TagContainsKeywordsPredicate pieTagPredicate =
            new TagContainsKeywordsPredicate(APPLE_PIE.getTags());
    private NameContainsKeywordsPredicate muffinNamePredicate =
            new NameContainsKeywordsPredicate(List.of(BANANA_MUFFIN.getName().fullName));

    // Returns a predicate that returns true if any of the given predicates will return true
    private static Predicate<Item> combinePredicate(Predicate<Item>... predicates) {
        return item -> {
            for (int i = 0; i < predicates.length; i++) {
                if (predicates[i].test(item)) {
                    return true;
                }
            }
            return false;
        };
    }

    @Test
    public void constructor_emptyList_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new FindCommand(new ArrayList<>()));
    }

    @Test
    public void equals() {

        FindCommand findNameCommand = new FindCommand(List.of(pieNamePredicate));
        FindCommand findIdCommand = new FindCommand(List.of(pieTagPredicate));

        // same object
        assertEquals(findNameCommand, findNameCommand);

        // same predicate
        assertEquals(findNameCommand, new FindCommand(List.of(pieNamePredicate)));

        // different types -> returns false
        assertFalse(findNameCommand.equals(1));

        // null -> returns false
        assertFalse(findNameCommand.equals(null));

        // different predicates -> returns false
        assertNotEquals(findNameCommand, findIdCommand);
    }

    @Test
    public void execute_existentName_itemFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 1);
        FindCommand command = new FindCommand(List.of(pieNamePredicate));
        expectedModel.updateFilteredItemList(DISPLAY_INVENTORY, pieNamePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_existentId_itemFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 1);
        FindCommand command = new FindCommand(List.of(pieIdPredicate));
        expectedModel.updateFilteredItemList(DISPLAY_INVENTORY, pieIdPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_existentTag_itemFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 3);
        FindCommand command = new FindCommand(List.of(pieTagPredicate));
        expectedModel.updateFilteredItemList(DISPLAY_INVENTORY, pieTagPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multiplePredicates_itemsFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 2);
        FindCommand command = new FindCommand(List.of(pieIdPredicate, muffinNamePredicate));

        Predicate<Item> combinedPredicate = combinePredicate(pieIdPredicate, muffinNamePredicate);;
        expectedModel.updateFilteredItemList(DISPLAY_INVENTORY, combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multiplePredicatesSameItem_itemFound() {
        String expectedMessage = String.format(MESSAGE_ITEMS_LISTED_OVERVIEW, 1);
        FindCommand command = new FindCommand(List.of(pieIdPredicate, pieNamePredicate));

        expectedModel.updateFilteredItemList(DISPLAY_INVENTORY, pieIdPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_notInDisplayInventory_itemFound() {
        FindCommand findCommand = new FindCommand(List.of(pieIdPredicate, muffinNamePredicate));
        model.updateFilteredDisplayList(DisplayMode.DISPLAY_TRANSACTION_LIST, Model.PREDICATE_SHOW_ALL_ITEMS);
        String expectedMessage = MESSAGE_INVENTORY_NOT_DISPLAYED;
        expectedModel.updateFilteredDisplayList(DisplayMode.DISPLAY_TRANSACTION_LIST, Model.PREDICATE_SHOW_ALL_ITEMS);
        assertCommandFailure(findCommand, model, expectedModel, expectedMessage);
    }


}
