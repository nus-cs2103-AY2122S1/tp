package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortOrdersCommandTest.DATE_DESC;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) for AddOrderCommand.
 */
public class AddOrderIntegrationTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                getTypicalOrderBook(), new UserPrefs());
    }

    @Test
    public void execute_newOrder_success() {
        Order validOrder = new OrderBuilder().build();
        expectedModel = new ModelManager(model.getAddressBook(),
                model.getTaskBook(), model.getOrderBook(), new UserPrefs());

        expectedModel.addOrder(validOrder);

        assertCommandSuccess(new AddOrderCommand(validOrder), model,
                String.format(AddOrderCommand.MESSAGE_SUCCESS, validOrder), expectedModel);
    }

    @Test
    public void execute_sortedOrder_defaultOrdering() {
        Order validOrder = new OrderBuilder().build();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                getTypicalOrderBook(), new UserPrefs());

        expectedModel.addOrder(validOrder);

        model.sortOrderList(DATE_DESC);

        String expectedMessage = String.format(AddOrderCommand.MESSAGE_SUCCESS, validOrder);
        assertCommandSuccess(new AddOrderCommand(validOrder), model, expectedMessage, expectedModel);
    }
}
