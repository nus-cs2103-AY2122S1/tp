package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.isValidDate;
import static seedu.address.commons.util.StringUtil.isValidTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NextMeeting implements OptionalPersonNonStringField {
    public static final String DATE_MESSAGE_CONSTRAINTS = "Next meeting date should be in the form of Day-Month-Year, "
        + "where Day, month and year should be numerical values.";
    public static final String TIME_MESSAGE_CONSTRAINTS = "Next meeting time should be in the 24-hour format, "
        + "where Hour and Minutes should be numerical values.";

    private static final NextMeeting NULL_MEETING = new NextMeeting(null, null, null,
        null);
    private static final String NO_NEXT_MEETING = "No meeting planned.";


    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;

    public final String dateInString;
    public final String startTimeInString;
    public final String endTimeInString;
    public final String location;

    /**
     * Constructs a {@code NextMeeting}.
     *
     * @param date date agent next meets a client
     */
    public NextMeeting(String date, String startTime, String endTime, String location) {
        if (!IS_NULL_VALUE_ALLOWED) {
            requireNonNull(date);
            requireNonNull(startTime);
            requireNonNull(endTime);
            requireNonNull(location);
        }

        date = convertEmptyStringIfNull(date);
        startTime = convertEmptyStringIfNull(startTime);
        endTime = convertEmptyStringIfNull(endTime);
        this.location = convertEmptyStringIfNull(location);

        checkArgument(isValidDate(date), DATE_MESSAGE_CONSTRAINTS);
        dateInString = date;

        checkArgument(isValidTime(startTime), TIME_MESSAGE_CONSTRAINTS);
        startTimeInString = startTime;

        checkArgument(isValidTime(endTime), TIME_MESSAGE_CONSTRAINTS);
        endTimeInString = endTime;

        this.date = parseToLocalDate(date);
        this.startTime = parseToLocalTime(startTime);
        this.endTime = parseToLocalTime(endTime);
    }

    private LocalDate parseToLocalDate(String date) {
        if (date.isEmpty()) {
            return null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(date, formatter);
        }
    }

    private LocalTime parseToLocalTime(String time) {
        if (time.isEmpty()) {
            return null;
        } else {
            return LocalTime.parse(time);
        }
    }

    /**
     * Checks if {@code s} is null.
     * Returns empty string if null, otherwise returns s.
     */
    private String convertEmptyStringIfNull(String s) {
        if (s == null) {
            return "";
        }
        return s;
    }

    /**
     * Parses a given String {@code meeting} to return a {@code NextMeeting}
     */
    public static NextMeeting parseNextMeetingString(String meeting) {
        if (meeting.equals(NO_NEXT_MEETING)) {
            return NULL_MEETING;
        }
        String date = meeting.split(" ", 2)[0];
        String startTime = meeting.substring(meeting.indexOf("(") + 1, meeting.indexOf("~"));
        String endTime = meeting.substring(meeting.indexOf("~") + 1, meeting.indexOf(")"));
        String location = meeting.split(", ", 2)[1];
        return new NextMeeting(date, startTime, endTime, location);
    }

    public static NextMeeting getNullMeeting() {
        return NULL_MEETING;
    }

    @Override
    public String toString() {
        if (date == null) {
            return NO_NEXT_MEETING;
        }
        return String.format("%s (%s~%s), %s", dateInString, startTimeInString, endTimeInString, location);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof NextMeeting // instanceof handles nulls
            && date.equals(((NextMeeting) other).date)
            && startTime.equals(((NextMeeting) other).startTime)
            && endTime.equals(((NextMeeting) other).endTime)
            && location.equals(((NextMeeting) other).location)); // state check
    }
}
