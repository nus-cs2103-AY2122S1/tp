package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.ClearCommand;

class AppointmentDateTest {

    private final String validDate = "31/12/2022";
    private final String invalidDate = "31-12-2021";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new AppointmentDate(invalidDate));
    }

    @Test
    public void isValidAppointmentDate_validDate_success() {
        assertTrue(AppointmentDate.isValidAppointmentDate(validDate));
    }

    @Test
    public void isValidAppointmentDate_invalidDate_failure() {
        assertFalse(AppointmentDate.isValidAppointmentDate(invalidDate));
        assertFalse(AppointmentDate.isValidAppointmentDate("abc123"));
    }

    @Test
    public void isValidAppointmentDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AppointmentDate.isValidAppointmentDate(null));
    }

    @Test
    public void isEqualDate_equalDates_returnsTrue() {
        assertTrue(new AppointmentDate(validDate).isEqualDate(new AppointmentDate(validDate)));
    }

    @Test
    public void isEqualDate_notEqualDates_returnsFalse() {
        String otherDate = "31/12/2030";
        assertFalse(new AppointmentDate(validDate).isEqualDate(new AppointmentDate(otherDate)));
    }

    @Test
    public void equals() {
        final AppointmentDate appointmentDate = new AppointmentDate(validDate);

        // same values -> returns true
        AppointmentDate copyAppointmentDate = new AppointmentDate(validDate);
        assertEquals(appointmentDate, copyAppointmentDate);

        // same object -> returns true
        assertEquals(appointmentDate, appointmentDate);

        // null -> returns false
        assertNotEquals(null, appointmentDate);

        // different types -> returns false
        assertNotEquals(appointmentDate, new ClearCommand());

        // different date -> returns false
        assertNotEquals(appointmentDate, new AppointmentDate("1/1/2023"));
    }

    @Test
    public void compareTo() {
        String date = "10/10/2040";
        String dateBefore = "10/10/2030";
        String dateAfter = "10/10/2050";

        AppointmentDate appointmentDate = new AppointmentDate(date);
        AppointmentDate otherAppointmentDate;

        otherAppointmentDate = new AppointmentDate(date);
        assertEquals(appointmentDate.compareTo(otherAppointmentDate), 0);

        otherAppointmentDate = new AppointmentDate(dateAfter);
        assertTrue(appointmentDate.compareTo(otherAppointmentDate) < 0);

        otherAppointmentDate = new AppointmentDate(dateBefore);
        assertTrue(appointmentDate.compareTo(otherAppointmentDate) > 0);
    }
}
