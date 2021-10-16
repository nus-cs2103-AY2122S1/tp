package seedu.fast.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.util.DateUtil;

public class AppointmentTest {
    public static final String VALID_DATE = "11 Nov 2021";
    public static final String VALID_TIME = "2300";
    public static final String VALID_VENUE = "testArea";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, null));
    }

    @Test
    public void convertDate() {
        //no date specified
        Appointment noDateAppointment = new Appointment(Appointment.NO_APPOINTMENT, Appointment.NO_TIME,
                Appointment.NO_VENUE);
        assertTrue(noDateAppointment.convertDate().equals(DateUtil.MAX_DATE));

        //correct format, valid date input
        Appointment validDateAppointment = new Appointment(VALID_DATE, Appointment.NO_TIME, Appointment.NO_VENUE);
        assertFalse(validDateAppointment.convertDate().equals(DateUtil.MAX_DATE));

    }

    @Test
    public void getDate() {
        //no date specified
        Appointment noTimeAppointment = new Appointment(Appointment.NO_APPOINTMENT, Appointment.NO_TIME,
                Appointment.NO_VENUE);
        assertTrue(noTimeAppointment.getDate().equals(Appointment.NO_APPOINTMENT));

        //date specified
        Appointment validTimeAppointment = new Appointment(VALID_DATE, VALID_TIME,
                Appointment.NO_VENUE);
        assertTrue(validTimeAppointment.getDate().equals(VALID_DATE));

        //invalid date not tested as parser automatically rejects invalid instances
    }

    @Test
    public void getTime() {
        //no time specified
        Appointment noTimeAppointment = new Appointment(Appointment.NO_APPOINTMENT, Appointment.NO_TIME,
                Appointment.NO_VENUE);
        assertTrue(noTimeAppointment.getTimeFormatted().equals(""));

        //time specified
        Appointment validTimeAppointment = new Appointment(VALID_DATE, VALID_TIME,
                Appointment.NO_VENUE);
        assertTrue(validTimeAppointment.getTimeFormatted().equals(VALID_TIME + "hrs"));

        //invalid time not tested as parser automatically rejects invalid instances
    }

    @Test
    public void getVenue() {
        //no venue specified
        Appointment noTimeAppointment = new Appointment(Appointment.NO_APPOINTMENT, Appointment.NO_TIME,
                Appointment.NO_VENUE);
        assertTrue(noTimeAppointment.getVenue().equals(""));

        //venue specified
        Appointment validVenueAppointment = new Appointment(VALID_DATE, Appointment.NO_TIME,
                VALID_VENUE);
        assertTrue(validVenueAppointment.getVenue().equals(VALID_VENUE));

        //invalid venue not tested as parser automatically rejects invalid instances
    }

    @Test
    public void equal() {
        Appointment standardAppointment = new Appointment(VALID_DATE, VALID_TIME, VALID_VENUE);
        Appointment appointmentWithSameData = new Appointment(VALID_DATE, VALID_TIME, VALID_VENUE);

        // same data appointment
        assertTrue(standardAppointment.equals(appointmentWithSameData));

        // same appointment
        assertTrue(standardAppointment.equals(standardAppointment));

        // null
        assertFalse(standardAppointment.equals(null));

        // different type
        assertFalse(standardAppointment.equals("Matthew"));

        // different fields
        assertFalse(standardAppointment.equals(new Appointment("27 Mar 2021", VALID_TIME, VALID_VENUE)));
        assertFalse(standardAppointment.equals(new Appointment(VALID_DATE, "1130", VALID_VENUE)));
        assertFalse(standardAppointment.equals(new Appointment(VALID_DATE, VALID_TIME, "")));
    }

    @Test
    public void hashcode() {
        Appointment standardAppointment = new Appointment(VALID_DATE, VALID_TIME, VALID_VENUE);
        Appointment appointmentWithSameData = new Appointment(VALID_DATE, VALID_TIME, VALID_VENUE);
        Appointment appointmentWithDifferentData = new Appointment(Appointment.NO_APPOINTMENT,
                Appointment.NO_TIME, Appointment.NO_VENUE);

        assertTrue(standardAppointment.hashCode() == appointmentWithSameData.hashCode());
        assertTrue(standardAppointment.hashCode() == standardAppointment.hashCode());

        assertFalse(standardAppointment.hashCode() == appointmentWithDifferentData.hashCode());
    }

    @Test
    public void isValidDateFormat() {
        // valid date
        assertTrue(Appointment.isValidDateFormat("27 Mar 2021"));

        // empty date
        assertTrue(Appointment.isValidDateFormat(Appointment.NO_APPOINTMENT));

        // invalid date
        assertFalse(Appointment.isValidDateFormat("2021-03-27"));
        assertFalse(Appointment.isValidDateFormat("03-27-2021"));
        assertFalse(Appointment.isValidDateFormat("2021 Mar 27"));
        assertFalse(Appointment.isValidDateFormat("Mar 27 2021"));
        assertFalse(Appointment.isValidDateFormat("27/Mar/2021"));
        assertFalse(Appointment.isValidDateFormat("27/03/2021"));
        assertFalse(Appointment.isValidDateFormat("27-Mar-2021"));
        assertFalse(Appointment.isValidDateFormat("27-03-2021"));
        assertFalse(Appointment.isValidDateFormat(""));
    }

    @Test
    public void isValidTimeFormat() {
        // valid time
        assertTrue(Appointment.isValidTimeFormat("1000"));

        // empty time
        assertTrue(Appointment.isValidTimeFormat(""));

        // invalid time
        assertFalse(Appointment.isValidTimeFormat("1000hrs"));
        assertFalse(Appointment.isValidTimeFormat("10:00"));
        assertFalse(Appointment.isValidTimeFormat("10:00hrs"));
        assertFalse(Appointment.isValidTimeFormat("10am"));
        assertFalse(Appointment.isValidTimeFormat("10.00"));
        assertFalse(Appointment.isValidTimeFormat("10:00am"));
        assertFalse(Appointment.isValidTimeFormat("ten o'clock"));
    }

    @Test
    public void isValidVenueFormat() {
        // valid time
        assertTrue(Appointment.isValidVenueFormat("Clementi Mall"));
        assertTrue(Appointment.isValidVenueFormat("1234"));
        assertTrue(Appointment.isValidVenueFormat("Clementi 123"));
        assertTrue(Appointment.isValidVenueFormat("?!@!!?@"));

        // empty time
        assertTrue(Appointment.isValidVenueFormat(""));

        // invalid time
        assertFalse(Appointment.isValidVenueFormat("testttestttestttestttesttestttestt"));
    }
}
