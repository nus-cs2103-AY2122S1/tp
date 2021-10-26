package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAppointment.ALICE_APPOINTMENT;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.UniquePersonList;

public class AppointmentTest {

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(ALICE_APPOINTMENT.equals(ALICE_APPOINTMENT));

        // null -> returns false
        assertFalse(ALICE_APPOINTMENT.equals(null));

        UniquePersonList testClients = new UniquePersonList();
        testClients.add(ALICE);

        // same attributes, different object
        Appointment similarAliceAppointment = new Appointment(testClients,
                new Address("369 Tanjong Rhu"),
                new TimePeriod(LocalDateTime.of(2021, 12, 25, 21, 30),
                        LocalDateTime.of(2021, 12, 25, 22, 30)),
                "Talk about sales");
        assertTrue(ALICE_APPOINTMENT.equals(similarAliceAppointment));

        // same client, different attributes
        Appointment editedAliceAppointment = new Appointment(testClients,
                new Address("369 Geylang Street"),
                new TimePeriod(LocalDateTime.of(2021, 12, 25, 21, 30),
                        LocalDateTime.of(2021, 12, 25, 22, 30)),
                "Talk about sales");
        assertFalse(ALICE_APPOINTMENT.equals(editedAliceAppointment));
    }
}
