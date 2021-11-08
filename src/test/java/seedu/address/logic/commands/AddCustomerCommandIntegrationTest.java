package seedu.address.logic.commands;

import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyRhrh;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.customer.Customer;
import seedu.address.testutil.CustomerBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCustomerCommand}.
 */
public class AddCustomerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());
    }

    @Test
    public void execute_newCustomer_success() {
        Customer validCustomer = new CustomerBuilder().build();

        Model expectedModel = new ModelManager(model.getRhrh(), new UserPrefs());
        expectedModel.addCustomer(validCustomer);

        assertCommandSuccess(new AddCustomerCommand(validCustomer), model,
                String.format(AddCustomerCommand.MESSAGE_SUCCESS, validCustomer), expectedModel);
    }

    @Test
    public void execute_duplicateCustomer_throwsCommandException() {
        ReadOnlyRhrh temp = model.getRhrh();
        Customer customerInList = model.getRhrh().getCustomerList().get(0);
        assertCommandFailure(new AddCustomerCommand(customerInList), model,
                AddCustomerCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

}
