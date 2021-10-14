package seedu.fast.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.util.DateUtil;

public class AppointmentTest {
    public static final String BAD_FORMAT_DATE = "2021-111-13";
    public static final String INVALID_DATE = "2021-13-13";
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

        //wrong format date input, stack trace should be printed.
        Appointment badFormatAppointment = new Appointment(BAD_FORMAT_DATE, Appointment.NO_TIME, Appointment.NO_VENUE);
        badFormatAppointment.convertDate();

        //correct format, invalid date input, stack trace should be printed.
        Appointment invalidDateAppointment = new Appointment(INVALID_DATE, Appointment.NO_TIME, Appointment.NO_VENUE);
        invalidDateAppointment.convertDate();

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
        assertTrue(noTimeAppointment.getTime().equals(""));

        //time specified
        Appointment validTimeAppointment = new Appointment(VALID_DATE, VALID_TIME,
                Appointment.NO_VENUE);
        assertTrue(validTimeAppointment.getTime().equals(VALID_TIME + "hrs"));

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

}
