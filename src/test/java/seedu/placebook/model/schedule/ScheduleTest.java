package seedu.placebook.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.placebook.testutil.Assert.assertThrows;
import static seedu.placebook.testutil.TypicalAppointment.ALICE_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.placebook.model.schedule.exceptions.AppointmentNotFoundException;
import seedu.placebook.model.schedule.exceptions.DuplicateAppointmentException;

public class ScheduleTest {

    private final Schedule schedule = new Schedule();

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.addAppointment(null));
    }

    @Test
    public void add_unique_existingAppointment() {
        schedule.addAppointment(ALICE_APPOINTMENT);
        assertTrue(schedule.contains(ALICE_APPOINTMENT));
    }

    @Test
    public void add_duplicate_existingAppointment() {
        schedule.addAppointment(ALICE_APPOINTMENT);
        assertThrows(DuplicateAppointmentException.class, () -> schedule.addAppointment(ALICE_APPOINTMENT));
    }
    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.deleteAppointment(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> schedule.deleteAppointment(ALICE_APPOINTMENT));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        schedule.addAppointment(ALICE_APPOINTMENT);
        schedule.deleteAppointment(ALICE_APPOINTMENT);
        Schedule expectedSchedule = new Schedule();
        assertEquals(expectedSchedule, schedule);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> schedule.asUnmodifiableObservableList().remove(0));
    }
}
