package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProducts.IPAD;
import static seedu.address.testutil.TypicalProducts.IPHONE;
import static seedu.address.testutil.TypicalProducts.MACBOOK;
import static seedu.address.testutil.TypicalProducts.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.product.ProductContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindProductCommand}.
 */
public class FindProductCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ProductContainsKeywordsPredicate firstPredicate =
                new ProductContainsKeywordsPredicate(Collections.singletonList("first"));
        ProductContainsKeywordsPredicate secondPredicate =
                new ProductContainsKeywordsPredicate(Collections.singletonList("second"));

        FindProductCommand findFirstCommand = new FindProductCommand(firstPredicate);
        FindProductCommand findSecondCommand = new FindProductCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindProductCommand findFirstCommandCopy = new FindProductCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different product -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noProductFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 0);
        ProductContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindProductCommand command = new FindProductCommand(predicate);
        expectedModel.updateFilteredProductList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredProductList());
    }

    @Test
    public void execute_multipleKeywords_multipleProductsFound() {
        String expectedMessage = String.format(MESSAGE_PRODUCTS_LISTED_OVERVIEW, 3);
        ProductContainsKeywordsPredicate predicate = preparePredicate("iphone macbook ipad");
        FindProductCommand command = new FindProductCommand(predicate);
        expectedModel.updateFilteredProductList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(IPHONE, IPAD, MACBOOK), model.getFilteredProductList());
    }

    /**
     * Parses {@code userInput} into a {@code ProductContainsKeywordsPredicate}.
     */
    private ProductContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ProductContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
