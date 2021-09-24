package seedu.address.model.appointment;

import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a scheduled appointment with an individual.
 * If appointment does not exist, appointment field is null.
 */
public class Appointment {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting should be in the following format: dd-MMM-yyyy hh:mm";
    private final LocalDateTime appointment;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd-MMM-yyyy hh:mm"); // Specific format as described in argument.

    /**
     * Creates an appointment that describes
     *
     * @param dateTimeString
     */
    public Appointment(String dateTimeString) {
        if(dateTimeString == null) {
            this.appointment = null;
        } else {
            this.appointment = Appointment.parseString(dateTimeString);
        }
    }

    @Override
    public String toString() {
        return appointment.toString();
    }

    public static boolean isValidMeetingTime(String meetingDateTime) {
        try {
            LocalDateTime.parse(meetingDateTime, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
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

}
