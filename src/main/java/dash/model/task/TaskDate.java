package dash.model.task;

import static dash.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Optional;

/**
 * Represents a Date of a Task, can have Date with optional Time or have neither.
 * Guarantees: immutable; is valid as declared in {@link #isValidArgument(String)}}
 */
public class TaskDate {
    public static final String MESSAGE_CONSTRAINTS = "Date/Time should not be blank. "
            + "They should also be valid (consider leap years) and follow a format."
            + " (eg. Date: dd MMM yyyy -> 02 Dec 2021,"
            + " Time: hh:mm a -> 12:00 PM) \n"
            + "If both Date and Time are included, Date should come first before Time and they should be separated "
            + "by a comma. A full list of available formats can be seen under the Help tab.";
    private static final String[] DATE_FORMATS = {
        "dd/MM/uuuu",
        "dd-MM-uuuu",
        "uuuu/MM/dd",
        "uuuu-MM-dd",
        "dd MMM uuuu"
    };
    private static final String[] TIME_FORMATS = {
        "HHmm",
        "hh:mm a"
    };
    private Optional<LocalDate> date = Optional.empty();
    private Optional<LocalTime> time = Optional.empty();
    private String taskDateString;
    private String taskTimeString;
    private String detectedDateFormat = null;
    private String detectedTimeFormat = null;

    /**
     * Constructs a {@code TaskDate}.
     *
     * @param taskDate A valid taskDate.
     */
    public TaskDate(String taskDate, boolean isForEditing) {
        requireNonNull(taskDate);
        checkArgument(isValidArgument(taskDate), MESSAGE_CONSTRAINTS);

        if (detectedDateFormat != null) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(detectedDateFormat);
            this.date = Optional.of(LocalDate.parse(taskDateString,
                    dateFormat.withResolverStyle(ResolverStyle.STRICT)));
        } else {
            if (!isForEditing) {
                this.date = Optional.of(LocalDate.now());
            }
        }

        if (detectedTimeFormat != null) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(detectedTimeFormat, Locale.US);
            this.time = Optional.of(LocalTime.parse(taskTimeString, timeFormat));
        }
    }

    /**
     * Constructs a {@code TaskDate} of empty Date and Time.
     */
    public TaskDate() {
    }


    private boolean isValidArgument(String taskDate) {
        requireNonNull(taskDate);
        if (!taskDate.contains(",")) {
            return isDate(taskDate) || isTime(taskDate);
        }

        String maybeDate = taskDate.split(",", 2)[0].trim();
        String maybeTime = taskDate.split(",", 2)[1].trim();

        return isDate(maybeDate) && isTime(maybeTime);
    }
    /**
     * Returns the instance's Date which can be missing.
     *
     * @return Optional LocalDate.
     */
    public Optional<LocalDate> getDate() {
        return this.date;
    }

    /**
     * Returns the instance's Time which can be missing.
     *
     * @return Optional LocalTime.
     */
    public Optional<LocalTime> getTime() {
        return this.time;
    }

    /**
     * Returns true if this instance has Date.
     *
     * @return boolean
     */
    public boolean hasDate() {
        return date.isPresent();
    }

    /**
     * Returns true if this instance has Time.
     *
     * @return boolean
     */
    public boolean hasTime() {
        return time.isPresent();
    }

    private boolean isDate(String dateString) {
        requireNonNull(dateString);
        boolean isDate = false;

        for (String dateFormat : DATE_FORMATS) {
            try {
                LocalDate.parse(dateString,
                        DateTimeFormatter.ofPattern(dateFormat).withResolverStyle(ResolverStyle.STRICT));
                detectedDateFormat = dateFormat;
                taskDateString = dateString;
                isDate = true;
            } catch (Exception e) {
                isDate = isDate || false;
            }
        }
        return isDate;
    }

    private boolean isTime(String timeString) {
        requireNonNull(timeString);
        boolean isTime = false;

        for (String timeFormat : TIME_FORMATS) {
            try {
                LocalTime.parse(timeString, DateTimeFormatter.ofPattern(timeFormat, Locale.US));
                detectedTimeFormat = timeFormat;
                taskTimeString = timeString;
                isTime = true;
            } catch (Exception e) {
                isTime = isTime || false;
            }
        }
        return isTime;
    }


    /**
     * Returns String of Date in given format, empty string if no date is present.
     *
     * @return String representation of Date.
     */
    public String toDateString() {
        return date.map(mapDate -> mapDate.format(DateTimeFormatter.ofPattern("dd MMM yyy")))
                .orElse("");
    }

    /**
     * @return String of Time in given format, empty string if no time is present.
     */
    public String toTimeString() {
        return time.map(mapTime -> mapTime.format(DateTimeFormatter.ofPattern("hh:mm a", Locale.US)))
                .orElse("");
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
        } else {
            if (this.hasDate()) {
                return false;
            }
        }

        if (otherTaskDate.hasTime()) {
            if (!this.hasTime()) {
                return false;
            }
            isSameTime = time.equals(otherTaskDate.getTime());
        } else {
            if (this.hasTime()) {
                return false;
            }
        }

        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                && isSameDate && isSameTime); // state check
    }

    @Override
    public String toString() {
        if (hasTime() && hasDate()) {
            return String.format("%s, %s", toDateString(), toTimeString());
        }
        if (hasTime()) {
            return String.format("%s", toTimeString());
        }
        if (hasDate()) {
            return String.format("%s", toDateString());
        }
        return "";
    }
}
