package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_NUMBER_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProducts.CANNON;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClientCommand.AddClientDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

public class StatCommandTest {
    private final Model model = new ModelManager();

    @Test
    public void execute_noStatistics_throwCommandException() {
        StatCommand statCommand = new StatCommand();
        assertThrows(CommandException.class, StatCommand.MESSAGE_FAILURE, () -> statCommand.execute(model));
    }

    @Test
    public void execute_hasStatistics_success() {
        Order order = new Order(CANNON.getName(), new Quantity("1"), LocalDate.now());
        HashSet<Order> orders = new HashSet<>();
        orders.add(order);

        AddClientDescriptor descriptor = new AddClientDescriptor(new Name(VALID_NAME_AMY),
                new PhoneNumber(VALID_PHONE_NUMBER_AMY));
        descriptor.setOrders(orders);

        try {
            AddClientCommand addClientCommand = new AddClientCommand(descriptor);
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
