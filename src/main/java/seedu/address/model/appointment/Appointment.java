package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a scheduled appointment with an individual.
 * If appointment does not exist, appointment field is null.
 */
public class Appointment {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting should be in the following format: dd-MMM-yyyy HH:mm "
                    + "where only first alphabet of the month is capitalised.";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd-MMM-yyyy HH:mm"); // Specific format as described in argument.

    private final LocalDateTime appointmentTime;

    /**
     * Creates an appointment that describes
     *
     * @param dateTimeString the string representation of the desired appointment.
     */
    public Appointment(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.equals("")) {
            this.appointmentTime = null;
        } else {
            this.appointmentTime = Appointment.parseString(dateTimeString);
        }
    }

    @Override
    public String toString() {
        if (appointmentTime == null) {
            return "";
        }
        return appointmentTime.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                    && appointmentTime.equals(((Appointment) other).appointmentTime)); // state check
    }

    /**
     * Checks whether the string that is supposed to represent a meeting timing is valid.
     *
     * @param meetingDateTime the string to check.
     * @return true if string is parsable, empty or null.
     */
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
     * Parses and converts the dateTimeString to LocalDateTime object.
     *
     * @param dateTimeString provided by the user.
     * @return DateTime object representing the date and time of the appointment.
     */
    private static LocalDateTime parseString(String dateTimeString) {
        requireNonNull(dateTimeString);
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    public String getValue() {
        if (this.appointmentTime == null) {
            return "";
        }
        return this.appointmentTime.format(FORMATTER);
    }

}
