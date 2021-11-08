package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.JOHNSON;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OrderBook;
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
        model = new ModelManager(new AddressBook(), getTypicalTaskBook(),
                new OrderBook(), new UserPrefs());
        model.addPerson(JOHNSON);
    }

    @Test
    public void execute_newOrder_success() throws Exception {
        Order validOrder = new OrderBuilder().build();
        expectedModel = new ModelManager(model.getAddressBook(),
                model.getTaskBook(), new OrderBook(), new UserPrefs());

        expectedModel.addOrder(validOrder);
        Command addCommand = new AddOrderCommand(validOrder);
        CommandResult commandResult = addCommand.execute(model);
        assertEquals(model.getOrderBook(), expectedModel.getOrderBook());
    }
}
