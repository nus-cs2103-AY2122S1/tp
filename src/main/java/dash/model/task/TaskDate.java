package dash.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import static dash.commons.util.AppUtil.checkArgument;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Date of a Task.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDate(String)}}
 */
public class TaskDate {
    public static final String MESSAGE_CONSTRAINTS = "Date/Time should not be blank."
        + "They should also follow a format. (eg. Date: dd/MM/yyyy, Time: HHmm)"
        + "If both Date and Time are included, Date should come first before Time."
        + "A full list of available formats can be seen under the Help tab.";

    private LocalDate date = null;
    private LocalTime time = null;
    private String taskDateString;
    private String taskTimeString;
    private String detectedDateFormat = null;
    private String detectedTimeFormat = null;
    private static final String[] dateFormats = {
            "dd/MM/yyyy",
            "dd-MM-yyyy",
            "yyyy/MM/dd",
            "yyyy-MM-dd",
            "dd MMM yyyy"
    };
    private static final String[] timeFormats = {
            "HHmm",
            "hh:mm a"
    };

    /**
     * Constructs a {@code TaskDate}.
     *
     * @param taskDate A valid taskDate.
     */
    public TaskDate(String taskDate) {
        requireNonNull(taskDate);
        checkArgument(isValidArgument(taskDate), MESSAGE_CONSTRAINTS);

        if (hasDate()) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(detectedDateFormat);
            this.date = LocalDate.parse(taskDateString, dateFormat);
        }

        if (hasTime()) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(detectedTimeFormat);
            this.time = LocalTime.parse(taskTimeString, timeFormat);
        }
    }

    /**
     * Returns true if a given string is a valid date/time.
     *
     * @param taskDate A valid formatted string of date/time
     * @return boolean if valid format
     */
    public static boolean isValidTaskDate(String taskDate) {

        if (!taskDate.contains(" ")) {
            return isThisDate(taskDate) || isThisTime(taskDate);
        }

        String maybeDate = taskDate.split(" ", 2)[0];
        String maybeTime = taskDate.split(" ", 2)[1];

        return isThisDate(maybeDate) && isThisTime(maybeTime);
    }

    private boolean isValidArgument(String taskDate) {

        if (!taskDate.contains(" ")) {
            return isDate(taskDate) || isTime(taskDate);
        }

        String maybeDate = taskDate.split(" ", 2)[0];
        String maybeTime = taskDate.split(" ", 2)[1];

        return isDate(maybeDate) && isTime(maybeTime);
    }

    /**
     * Returns the instance's Date.
     *
     * @return LocalDate object.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the instance's Time.
     *
     * @return LocalTime object.
     */
    public LocalTime getTime() {
        return this.time;
    }

    private boolean hasDate() {
        return detectedDateFormat != null;
    }

    private boolean hasTime() {
        return detectedTimeFormat != null;
    }

    /**
     * Returns true if given string is a Date.
     *
     * @param dateString A string in valid date format
     * @return boolean
     */
    public static boolean isThisDate(String dateString) {
        boolean isDate = false;

        for (String i : dateFormats) {
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern(i));
                isDate = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return isDate;
    }

    private boolean isDate(String dateString) {
        boolean isDate = false;

        for (String i : dateFormats) {
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern(i));
                detectedDateFormat = i;
                isDate = true;
                taskDateString = dateString;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return isDate;
    }

    /**
     * Returns true if given string is a Time.
     *
     * @param timeString A string in valid date format
     * @return boolean
     */
    public static boolean isThisTime(String timeString) {
        boolean isTime = false;

        for (String i : timeFormats) {
            try {
                LocalDate.parse(timeString, DateTimeFormatter.ofPattern(i));
                isTime = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return isTime;
    }

    private boolean isTime(String timeString) {
        boolean isTime = false;

        for (String i : timeFormats) {
            try {
                LocalDate.parse(timeString, DateTimeFormatter.ofPattern(i));
                detectedTimeFormat = i;
                isTime = true;
                taskTimeString = timeString;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return isTime;
    }

    @Override
    public String toString() {

        if (hasDate() && hasTime()) {
            String dateString = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            String timeString = this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
            return String.format("%s %s", dateString, timeString);
        }
        if (hasDate()) {
            String dateString = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            return String.format("%s", dateString);
        }
        String timeString = this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return String.format("%s", timeString);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                && date.equals(((TaskDate) other).getDate())
                && time.equals(((TaskDate) other).getTime())); // state check
    }
}
