package dash.model.task;

import static dash.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Date of a Task, can have Date, Task, both or none.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDate(String)}}
 */
public class TaskDate {
    public static final String MESSAGE_CONSTRAINTS = "Date/Time should not be blank. "
        + "They should also follow a format. (eg. Date: dd/MM/yyyy, Time: HHmm) "
        + "If both Date and Time are included, Date should come first before Time and they should be separated "
        + "by a comma. A full list of available formats can be seen under the Help tab.";
    private static final String[] DATE_FORMATS = {
        "dd/MM/yyyy",
        "dd-MM-yyyy",
        "yyyy/MM/dd",
        "yyyy-MM-dd",
        "dd MMM yyyy"
    };
    private static final String[] TIME_FORMATS = {
        "HHmm",
        "hh:mm a"
    };
    private LocalDate date = null;
    private LocalTime time = null;
    private String taskDateString;
    private String taskTimeString;
    private String detectedDateFormat = null;
    private String detectedTimeFormat = null;


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

    public TaskDate() {
    }

    /**
     * Returns true if a given string is a valid date/time.
     *
     * @param taskDate A valid formatted string of date/time
     * @return boolean if valid format
     */
    public static boolean isValidTaskDate(String taskDate) {

        if (!taskDate.contains(",")) {
            return isThisDate(taskDate) || isThisTime(taskDate);
        }

        String maybeDate = taskDate.split(",", 2)[0].trim();
        String maybeTime = taskDate.split(",", 2)[1].trim();

        return isThisDate(maybeDate) && isThisTime(maybeTime);
    }

    private boolean isValidArgument(String taskDate) {

        if (!taskDate.contains(",")) {
            return isDate(taskDate) || isTime(taskDate);
        }

        String maybeDate = taskDate.split(",", 2)[0].trim();
        String maybeTime = taskDate.split(",", 2)[1].trim();

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

    /**
     * Returns true if this instance has Date.
     *
     * @return boolean
     */
    public boolean hasDate() {
        return detectedDateFormat != null;
    }

    /**
     * Returns true if this instance has Time.
     *
     * @return boolean
     */
    public boolean hasTime() {
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

        for (String dateFormat : DATE_FORMATS) {
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat));
                isDate = true;
            } catch (Exception e) {
                System.out.println("wrong format");
            }
        }
        return isDate;
    }

    private boolean isDate(String dateString) {
        boolean isDate = false;

        for (String dateFormat : DATE_FORMATS) {
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat));
                detectedDateFormat = dateFormat;
                isDate = true;
                taskDateString = dateString;
            } catch (Exception e) {
                System.out.println("wrong format");
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

        for (String timeFormat : TIME_FORMATS) {
            try {
                LocalTime.parse(timeString, DateTimeFormatter.ofPattern(timeFormat));
                isTime = true;
            } catch (Exception e) {
                System.out.println("wrong format");
            }
        }
        return isTime;
    }

    private boolean isTime(String timeString) {
        boolean isTime = false;

        for (String timeFormat : TIME_FORMATS) {
            try {
                LocalTime.parse(timeString, DateTimeFormatter.ofPattern(timeFormat));
                detectedTimeFormat = timeFormat;
                isTime = true;
                taskTimeString = timeString;
            } catch (Exception e) {
                System.out.println("wrong format");
            }
        }
        return isTime;
    }

    public String toDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public String toTimeString() {
        return this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    @Override
    public boolean equals(Object other) {
        TaskDate otherTaskDate = (TaskDate) other;
        boolean isSameDate = true;
        boolean isSameTime = true;

        if (otherTaskDate.hasDate()) {
            if (!this.hasDate()) {
                return false;
            }
            isSameDate = date.equals(otherTaskDate.getDate());
        }

        if (otherTaskDate.hasTime()) {
            if (!this.hasTime()) {
                return false;
            }
            isSameTime = time.equals(otherTaskDate.getTime());
        }

        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                && isSameDate && isSameTime); // state check
    }

    @Override
    public String toString() {

        if (hasDate() && hasTime()) {
            return String.format("%s, %s", toDateString(), toTimeString());
        }
        if (hasDate()) {
            return String.format("%s", toDateString());
        }
        if (hasTime()) {
            return String.format("%s", toTimeString());
        }
        return "";
    }

}
