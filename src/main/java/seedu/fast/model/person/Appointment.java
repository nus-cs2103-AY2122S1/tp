package seedu.fast.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import seedu.fast.commons.util.DateUtil;

/**
 * Represents an appointment with the Person in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Appointment {
    public static final String NO_APPOINTMENT = "No Appointment Scheduled Yet";
    public static final String NO_TIME = "";
    public static final String NO_VENUE = "";

    public static final String INVALID_DATE_INPUT = "Date field must be yyyy-mm-dd, "
            + "with valid calendar year, month and days";
    public static final String INVALID_TIME_INPUT = "Time field must be hh:mm, "
            + "in 24-hour format, with valid hour and minute";
    public static final String INVALID_VENUE_INPUT = "Venue field must be less than 30 characters, "
            + "including blanks, whitespaces and symbols";


    private String date;
    private String time;
    private String venue;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param date A valid date (dd MMM yyyy).
     * @param time A valid time (hhmm).
     * @param venue A venue (less than 30 characters).
     */
    public Appointment(String date, String time, String venue) {
        requireNonNull(date);
        checkArgument(isValidDateFormat(date), INVALID_DATE_INPUT);
        checkArgument(isValidTimeFormat(time), INVALID_TIME_INPUT);
        checkArgument(isValidVenueFormat(time), INVALID_VENUE_INPUT);
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    /**
     * Converts the date from a String to a Date object.
     *
     * @return Date object from the string.
     */
    public Date convertDate() {
        Date temp = DateUtil.MAX_DATE;
        if (date.equals(NO_APPOINTMENT)) {
            return temp;
        }
        try {
            temp = new SimpleDateFormat("dd MMM yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Returns a string in the format 'dd MMM yyyy' of the appointment.
     *
     * @return A string representing the date of the appointment.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Returns a string in the format 'HHmm'hrs of the appointment.
     *
     * @return A string representing the formatted time of the appointment.
     */
    public String getTimeFormatted() {
        if (this.time.equals(NO_TIME)) {
            return "";
        }

        return this.time + "hrs";
    }

    /**
     * Returns a string in the format 'HHmm' of the appointment.
     *
     * @return A string representing the time of the appointment.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Returns a string representing the venue of the appointment.
     *
     * @return A string representing the venue of the appointment.
     */
    public String getVenue() {
        return this.venue;
    }

    /**
     * Check if the input date follows the format (dd MMM yyyy).
     *
     * @param test The input date string.
     * @return A boolean indicating if the date follows the format.
     */
    public static boolean isValidDateFormat(String test) {
        if (test.equalsIgnoreCase(NO_APPOINTMENT)) {
            return true;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("dd MMM yyyy");
            sdf.setLenient(false);
            sdf.parse(test);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Check if the input time follows the format (HHmm).
     *
     * @param test The input time string.
     * @return A boolean indicating if the time follows the format.
     */
    public static boolean isValidTimeFormat(String test) {
        if (test.equalsIgnoreCase(NO_TIME)) {
            return true;
        }

        String validationPattern = "^([0-1]?[0-9]|2[0-3])[0-5][0-9]$";
        return test.matches(validationPattern);
    }

    /**
     * Check if the input venue is at most 30 characters.
     *
     * @param test The input venue string.
     * @return A boolean indicating if the venue follows the format.
     */
    public static boolean isValidVenueFormat(String test) {
        return test.length() <= 30;
    }



    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppt = (Appointment) other;
        return otherAppt.getDate().equals(getDate())
                && otherAppt.getTimeFormatted().equals(getTimeFormatted())
                && otherAppt.getVenue().equals(getVenue());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, time, venue);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Appointment: ")
                .append(this.getDate())
                .append(" ")
                .append(this.getTimeFormatted())
                .append(" ")
                .append(this.getVenue());

        return builder.toString();
    }
}
