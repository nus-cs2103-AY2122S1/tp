package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents a Task's deadline, must only contain alphanumeric characters.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDeadline(String)}
 */
public class TaskDeadline {

    public static final String MESSAGE_CONSTRAINTS = "TaskDeadline must not be blank and must only consist of one \n"
            + "valid date in form of YYYY-MM-DD and/or one valid time in form of HH:MM";
    public final String value;

    /**
     * Constructs a {@code taskDeadline}.
     *
     * @param taskDeadline A valid taskDeadline.
     */
    public TaskDeadline(String taskDeadline) {
        requireNonNull(taskDeadline);
        checkArgument(isValidTaskDeadline(taskDeadline), MESSAGE_CONSTRAINTS);
        value = taskDeadline;
    }


    /**
     * Returns the time or date of the task.
     *
     * @param line The user's input.
     * @return The String representation of the time or date of the task.
     * @throws DateTimeParseException An exception thrown if the date or time is invalid.
     */
    public static String parseDateAndTime(Scanner line) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        LocalDate date;
        LocalTime time;
        String formattedDateAndTime;
        String[] split = line.nextLine().split(" ");
        if (isInDateFormat(split[0])) {
            date = LocalDate.parse(split[0]);
            formattedDateAndTime = dateFormatter.format(date);
            if (split.length == 2) {
                time = LocalTime.parse(split[1]);
                formattedDateAndTime = formattedDateAndTime + " " + timeFormatter.format(time);
            }
        } else {
            time = LocalTime.parse(split[0]);
            formattedDateAndTime = timeFormatter.format(time);
            if (split.length == 2) {
                date = LocalDate.parse(split[1]);
                formattedDateAndTime = dateFormatter.format(date) + " " + formattedDateAndTime;
            }
        }
        return formattedDateAndTime;
    }

    /**
     * Returns whether the string is in a date format.
     *
     * @param string The string that may potentially be a date.
     * @return A boolean stating whether the string is in a date format.
     */
    public static boolean isInDateFormat(String string) {
        if (string.length() == 10) {
            String year = string.substring(0, 4);
            String month = string.substring(5, 7);
            String day = string.substring(8, 10);
            return string.length() == 10 && string.charAt(4) == '-' && string.charAt(7) == '-'
                    && isInteger(year) && isInteger(month) && isInteger(day);
        }
        return false;
    }

    /**
     * Returns whether the string is in a time format.
     *
     * @param string The string that may potentially be a time.
     * @return A boolean stating whether the string is in a time format.
     */
    public static boolean isInTimeFormat(String string) {
        return string.length() == 5 && isInteger(string.substring(0, 2)) && isInteger(string.substring(3, 5))
                && string.charAt(2) == ':';
    }

    /**
     * Returns whether the string is an integer.
     *
     * @param string The string that may potentially be an integer.
     * @return A boolean stating whether the string is an integer.
     */
    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid taskDeadline.
     *
     * @param test The string that may potentially be a valid task deadline.
     * @return A boolean stating whether the string is a valid task deadline.
     */
    public static boolean isValidTaskDeadline(String test) {
        try {
            if (test.equals("")) {
                return false;
            }
            boolean datePresent = false;
            boolean timePresent = false;
            Scanner testDeadline = new Scanner(test);
            while (testDeadline.hasNext()) {
                String currWord = testDeadline.next();
                if (isInDateFormat(currWord) && !datePresent) {
                    datePresent = true;
                    LocalDate.parse(currWord);
                } else if (isInTimeFormat(currWord) && !timePresent) {
                    timePresent = true;
                    LocalTime.parse(currWord);
                } else {
                    return false;
                }
            }
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        Scanner deadline = new Scanner(value);
        return parseDateAndTime(deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadline // instanceof handles nulls
                && value.equals(((TaskDeadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
