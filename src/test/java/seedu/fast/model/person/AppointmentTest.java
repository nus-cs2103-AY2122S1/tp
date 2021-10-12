package seedu.fast.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.util.DateUtil;

public class AppointmentTest {
    public static final String BAD_FORMAT_DATE = "2021-111-13";
    public static final String INVALID_DATE = "2021-13-13";
    public static final String VALID_DATE = "11 Nov 2021";
    //following string will remain correct as long as locale of the system is the same
    public static final String VALID_DATE_STRING = "Thu Nov 11 00:00:00 SGT 2021";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, null));
    }

    @Test
    public void convertDate() {
        //no date
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
        assertTrue(validDateAppointment.convertDate().toString().equals(VALID_DATE_STRING));

    }

}
