package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ReservationCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ParserUtil.DATE_FORMATTER;
import static seedu.address.logic.parser.ParserUtil.TIME_FORMATTER;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_BOB;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.reservation.CustomerContainsReservationPredicate;
import seedu.address.model.reservation.ListContainsReservationPredicate;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.table.Table;

class CheckCommandTest {
    private LocalDate date1 = LocalDate.parse("2021-01-01", DATE_FORMATTER);
    private LocalDate date2 = LocalDate.parse("2021-02-02", DATE_FORMATTER);
    private LocalTime time = LocalTime.parse("1200", TIME_FORMATTER);
    private EnumTypeOfCheck typeOfCheck = EnumTypeOfCheck.DateTime;
    private Model model = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());
    private Model resultModel = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());
    private Table table1 = new Table(5, 10);
    private Table table2 = new Table(10, 11);
    private Reservation reservation1 = new Reservation(
            CUSTOMER_ALICE.getPhone(), 5, LocalDateTime.of(date1, time), table1, new Remark(""), Set.of()
    );
    private Reservation reservation2 = new Reservation(
            CUSTOMER_BOB.getPhone(), 10, LocalDateTime.of(date2, time), table2, new Remark(""), Set.of()
    );

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

        CheckCommand findFirstCommand = new CheckCommand(predicate1);
        CheckCommand findSecondCommand = new CheckCommand(predicate2);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        CheckCommand findFirstCommandCopy = new CheckCommand(predicate1);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
}
