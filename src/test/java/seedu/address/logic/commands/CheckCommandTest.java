package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ReservationCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;
import static seedu.address.testutil.TypicalReservation.ALICE_RESERVATION;
import static seedu.address.testutil.TypicalReservation.BENSON_RESERVATION;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.reservation.CustomerContainsReservationPredicate;
import seedu.address.model.reservation.ListContainsReservationPredicate;
import seedu.address.model.reservation.Reservation;

class CheckCommandTest {
    private LocalDate date1 = ALICE_RESERVATION.getDateTime().toLocalDate();
    private LocalDate date2 = BENSON_RESERVATION.getDateTime().toLocalDate();
    private LocalTime time = ALICE_RESERVATION.getDateTime().toLocalTime();
    private EnumTypeOfCheck typeOfCheck = EnumTypeOfCheck.DateTime;
    private Model model = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());
    private Model resultModel = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());
    private Reservation reservation1 = ALICE_RESERVATION;
    private Reservation reservation2 = BENSON_RESERVATION;

    @Test
    void execute_validDateTime_success() {
        String expectedMessage =
                String.format(Messages.MESSAGE_RESERVATIONS_LISTED_DATETIME, 1, LocalDateTime.of(date1, time));
        resultModel.addReservation(reservation1);
        resultModel.addReservation(reservation2);
        model.addReservation(reservation1);
        model.addReservation(reservation2);
        ListContainsReservationPredicate predicate = new ListContainsReservationPredicate(date1, time, typeOfCheck);
        CheckCommand command = new CheckCommand(predicate);
        resultModel.updateFilteredReservationList(predicate);
        resultModel.updateFilteredCustomerList(
                new CustomerContainsReservationPredicate(resultModel.getFilteredReservationList()));
        assertCommandSuccess(command, model, expectedMessage, resultModel);
        Model expectedModel = new ModelManager();
        expectedModel.addCustomer(CUSTOMER_ALICE);
        assertEquals(expectedModel.getFilteredCustomerList(), resultModel.getFilteredCustomerList());
    }

    @Test
    void execute_validDateTimeNoReservationFound_success() {
        String expectedMessage =
                String.format(Messages.MESSAGE_RESERVATIONS_LISTED_DATETIME, 0, LocalDateTime.of(date2, time));
        resultModel.addReservation(reservation1);
        model.addReservation(reservation1);
        ListContainsReservationPredicate predicate = new ListContainsReservationPredicate(date2, time, typeOfCheck);
        CheckCommand command = new CheckCommand(predicate);
        resultModel.updateFilteredReservationList(predicate);
        resultModel.updateFilteredCustomerList(
                new CustomerContainsReservationPredicate(resultModel.getFilteredReservationList()));
        assertCommandSuccess(command, model, expectedMessage, resultModel);
        assertEquals(Collections.emptyList(), resultModel.getFilteredCustomerList());
    }

    @Test
    void testEquals() {
        ListContainsReservationPredicate predicate1 = new ListContainsReservationPredicate(date1, time, typeOfCheck);
        ListContainsReservationPredicate predicate2 = new ListContainsReservationPredicate(date2, time, typeOfCheck);

        CheckCommand checkFirstCommand = new CheckCommand(predicate1);
        CheckCommand checkSecondCommand = new CheckCommand(predicate2);

        // same object -> returns true
        assertTrue(checkFirstCommand.equals(checkFirstCommand));

        // same values -> returns true
        CheckCommand checkFirstCommandCopy = new CheckCommand(predicate1);
        assertTrue(checkFirstCommand.equals(checkFirstCommandCopy));

        // different types -> returns false
        assertFalse(checkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(checkFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(checkFirstCommand.equals(checkSecondCommand));
    }
}
