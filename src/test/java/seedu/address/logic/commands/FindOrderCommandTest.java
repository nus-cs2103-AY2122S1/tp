package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOrders.SALESORDER1;
import static seedu.address.testutil.TypicalOrders.SALESORDER2;
import static seedu.address.testutil.TypicalOrders.SALESORDER3;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.OrderContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindOrderCommand}.
 * Modelled after FindCommandTest
 */
class FindOrderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            getTypicalOrderBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(),
            getTypicalTaskBook(), getTypicalOrderBook(), new UserPrefs());


    @Test
    public void equals() {
        OrderContainsKeywordsPredicate firstPredicate =
                new OrderContainsKeywordsPredicate(Collections.singletonList("first"));
        OrderContainsKeywordsPredicate secondPredicate =
                new OrderContainsKeywordsPredicate(Collections.singletonList("second"));

        FindOrderCommand findFirstCommand = new FindOrderCommand(firstPredicate);
        FindOrderCommand findSecondCommand = new FindOrderCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noOrderFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 0);
        OrderContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }

    @Test
    public void execute_multipleKeywords_multipleOrdersFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 3);
        OrderContainsKeywordsPredicate predicate = preparePredicate("Josh testorder3 Mac");
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SALESORDER1, SALESORDER2, SALESORDER3), model.getFilteredOrderList());
    }

    /**
     * Parses {@code userInput} into a {@code OrderContainsKeywordsPredicate}.
     */
    private OrderContainsKeywordsPredicate preparePredicate(String userInput) {
        return new OrderContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
