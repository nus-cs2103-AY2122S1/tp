package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Phone;
import seedu.address.model.reservation.Reservation;


class ReserveCommandTest {
    private static final int DUMMY_NUMBER_OF_PEOPLE = 2;
    private static final Phone DUMMY_PHONE = new Phone("98765432");
    private static final LocalDateTime DUMMY_DATE_TIME =
            LocalDateTime.parse("2021-11-11 2000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validReservation_addSuccessful() throws Exception {
        ReserveCommand command = new ReserveCommand(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME);
        Reservation expectedReservation = new Reservation(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME);

        command.execute(model);

        assertTrue(model.hasReservation(expectedReservation));
    }
}
