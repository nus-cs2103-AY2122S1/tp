package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_NUMBER_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

public class StatCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute_noStatistics_throwCommandException() {
        StatCommand statCommand = new StatCommand();
        assertThrows(CommandException.class, StatCommand.MESSAGE_FAILURE, () -> statCommand.execute(model));
    }

    @Test
    public void execute_hasStatistics_success() {
        AddClientCommand.AddClientDescriptor descriptor = new AddClientCommand.AddClientDescriptor(
                new Name(VALID_NAME_AMY), new PhoneNumber(VALID_PHONE_NUMBER_AMY));
        Order order = new Order(new ID(INDEX_FIRST_PRODUCT.getOneBased()), new Quantity("1"), LocalDate.now());
        HashSet<Order> orders = new HashSet<>();
        orders.add(order);
        descriptor.setOrders(orders);
        AddClientCommand addClientCommand = new AddClientCommand(descriptor);
        try {
            addClientCommand.execute(model);
            StatCommand statCommand = new StatCommand();
            String actualFeedback = statCommand.execute(model).getFeedbackToUser();
            String expectedFeedback = StatCommand.MESSAGE_SUCCESS;
            assertEquals(expectedFeedback, actualFeedback);
        } catch (CommandException e) {
            fail();
        }
    }
}
