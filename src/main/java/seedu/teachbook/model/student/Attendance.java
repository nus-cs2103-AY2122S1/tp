package seedu.teachbook.model.student;

import static seedu.teachbook.commons.util.AppUtil.checkArgument;
import static seedu.teachbook.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a Student's attendance in the TeachBook.
 * Guarantees: immutable.
 */
public class Attendance {

    public static final String PRESENT = "Present";
    public static final String ABSENT = "Absent";

    public final boolean isPresent;
    public final LocalDateTime lastModified;

    /**
     * Constructs an {@code Attendance}.
     *
     * @param isPresent indicator if a {@code Student} is present.
     * @param lastModified datetime when the {@code Attendance} object was created.
     */
    public Attendance(boolean isPresent, LocalDateTime lastModified) {
        requireAllNonNull(isPresent, lastModified);
        this.isPresent = isPresent;
        this.lastModified = lastModified;
    }

    /**
     * Checks if the given string is a valid attendance.
     *
     * @param test attendance string to be tested.
     * @return {@code true} is the given string is a valid attendance.
     */
    public static boolean isValidAttendance(String test) {
        String[] attendanceComponents = test.split("\\s+");
        if (attendanceComponents.length != 2
                || (!attendanceComponents[0].equalsIgnoreCase(PRESENT)
                && !attendanceComponents[0].equalsIgnoreCase(ABSENT))) {
            return false;
        }

        try {
            LocalDateTime.parse(attendanceComponents[1]);
        } catch (DateTimeParseException exception) {
            return false;
        }

        return true;
    }

    /**
     * Constructs the default {@code Attendance} object to be used to create a {@code Student} object.
     *
     * @return default {@code Attendance} object to be used to create a {@code Student} object.
     */
    public static Attendance getDefaultAttendance() {
        return new Attendance(false, LocalDateTime.now());
    }

    /**
     * Returns the {@code isPresent} field of the {@code Attendance} object.
     *
     * @return {@code isPresent} field of the {@code Attendance} object.
     */
    public boolean getIsPresent() {
        return isPresent;
    }

    /**
     * Constructs an {@code Attendance} object from the given attendance string.
     *
     * @param attendanceString a valid attendance string.
     * @return {@code Attendance} object constructed from the given attendance string.
     */
    public static Attendance fromString(String attendanceString) {
        checkArgument(isValidAttendance(attendanceString));
        String[] attendanceComponents = attendanceString.split("\\s+");
        boolean isPresent = attendanceComponents[0].equalsIgnoreCase(PRESENT);
        LocalDateTime lastModified = LocalDateTime.parse(attendanceComponents[1]);
        return new Attendance(isPresent, lastModified);
    }

    /**
     * Returns the string representation of an {@code Attendance} object in the "dd MMM yyyy hh:mm a" format.
     *
     * @return formatted string representation of an {@code Attendance} object.
     */
    public String asFormattedString() {
        return isPresent
                ? PRESENT + " "
                + lastModified.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a", Locale.ENGLISH))
                : ABSENT;
    }

    @Override
    public String toString() {
        return (isPresent ? PRESENT : ABSENT) + " " + lastModified.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && isPresent == ((Attendance) other).isPresent // state check
                && lastModified.equals(((Attendance) other).lastModified));
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPresent, lastModified);
    }
}
