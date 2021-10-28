package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_BENSON;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_CARL;
import static seedu.address.testutil.TypicalReservation.CARL_RESERVATION;
import static seedu.address.testutil.TypicalReservation.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Phone;

public class GetCustomerReservingCommandTest {
    private static final Index FIRST_INDEX = Index.fromOneBased(1);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        String expectedMessage = GetCustomerReservingCommand.MESSAGE_CUSTOMER_RESERVING_LISTED;
        GetCustomerReservingCommand command = new GetCustomerReservingCommand(FIRST_INDEX);
        Phone customerPhone = expectedModel.getFilteredReservationList().get(0).getPhone();
        expectedModel.updateFilteredCustomerList(customer -> customer.getPhone().equals(customerPhone));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CUSTOMER_BENSON), model.getFilteredCustomerList());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_success() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReservationList().size() + 1);
        GetCustomerReservingCommand command = new GetCustomerReservingCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_RESERVATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        String expectedMessage = GetCustomerReservingCommand.MESSAGE_CUSTOMER_RESERVING_LISTED;
        GetCustomerReservingCommand command = new GetCustomerReservingCommand(FIRST_INDEX);
        model.updateFilteredReservationList(reservation -> reservation.equals(CARL_RESERVATION));
        expectedModel.updateFilteredReservationList(reservation -> reservation.equals(CARL_RESERVATION));
        Phone customerPhone = CARL_RESERVATION.getPhone();
        expectedModel.updateFilteredCustomerList(customer -> customer.getPhone().equals(customerPhone));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CUSTOMER_CARL), model.getFilteredCustomerList());
    }

    @Test
    public void execute_invalidIndexFilteredList_success() {
        model.updateFilteredReservationList(reservation -> reservation.equals(CARL_RESERVATION));
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReservationList().size() + 1);
        GetCustomerReservingCommand command = new GetCustomerReservingCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_RESERVATION_DISPLAYED_INDEX);
    }
}
