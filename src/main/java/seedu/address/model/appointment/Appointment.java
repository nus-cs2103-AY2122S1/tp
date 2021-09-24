package seedu.address.model.appointment;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a scheduled appointment with an individual.
 * If appointment does not exist, appointment field is null.
 */
public class Appointment {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting should be in the following format: dd-MMM-yyyy HH:mm where only first alphabet of the month is capitalised.";
    public final LocalDateTime appointment;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd-MMM-yyyy HH:mm"); // Specific format as described in argument.

    /**
     * Creates an appointment that describes
     *
     * @param dateTimeString the string representation of the desired appointment.
     */
    public Appointment(String dateTimeString) {
        if(dateTimeString == null || dateTimeString.equals("")) {
            this.appointment = null;
        } else {
            this.appointment = Appointment.parseString(dateTimeString);
        }
    }

    @Override
    public String toString() {
        if (appointment == null) {
            return "";
        }
        return appointment.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                    && appointment.equals(((Appointment) other).appointment)); // state check
    }

    public static boolean isValidMeetingTime(String meetingDateTime) {
        if (meetingDateTime == null || meetingDateTime.equals("")) {
            return true;
        }
        try {
            LocalDateTime.parse(meetingDateTime, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Parses and converts the dateTimeString to LocalDateTime object
     *
     * @param dateTimeString provided by the user
     * @return DateTime object representing the date and time of the appointment
     */
    private static LocalDateTime parseString(String dateTimeString){
        assert(dateTimeString != null);
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    /**
     * Check whether this appointment object represents a real appointment.
     *
     * @return boolean on whether there is a date time in this appointment.
     */
    public boolean exists() {
        return this.appointment != null;
    }

    public String getValue() {
        if (this.appointment == null) {
            return "";
        }
        return this.appointment.format(FORMATTER);
    }

}
