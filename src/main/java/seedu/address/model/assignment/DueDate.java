package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddAssignmentCommand;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

public class DueDate implements Comparable<DueDate> {

    public static final String MESSAGE_CONSTRAINTS_TIME = "Due time should be in a format HHmm";
    public static final String MESSAGE_CONSTRAINTS_DATE = "Due date should be in a format d/M/yyyy";
    public static final String MESSAGE_CONSTRAINTS_DUE_DATE = "Due dates should be in a format d/M/yyyy [,HHmm]\n"
            + AddAssignmentCommand.FRIENDLY_COMMAND_SYNTAX;
    public static final String OUTPUT_CONSTRAINTS = "Due dates saved should be in a format dd MMM yyyy, hh:mm a";

    //Solution below adapted from
    // https://stackoverflow.com/questions/4374185/regular-expression-match-to-test-for-a-valid-year
    public static final String FIRST_28_DAYS_REGEX = "((0?[1-9]|1[0-9]|2[0-8])[/](0?[1-9]|1[012]))";
    public static final String MONTHS_WITH_31_DAYS_REGEX = "((29|30|31)[/](0?[13578]|1[02]))";
    public static final String MONTHS_WITH_30_DAYS_REGEX = "((29|30)[/](0?[4,6,9]|11))";
    public static final String YEAR_REGEX = "[/]((?!0000)\\d\\d\\d\\d)";
    public static final String NON_LEAP_YEAR_REGEX = "(" + FIRST_28_DAYS_REGEX
            + "|" + MONTHS_WITH_31_DAYS_REGEX
            + "|" + MONTHS_WITH_30_DAYS_REGEX + ")"
            + YEAR_REGEX;
    public static final String LEAP_YEAR_REGEX = "29[/]0?2[/](19|[2-9][0-9])"
            + "(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)";
    public static final String DATE_VALIDATION_REGEX = "(^" + NON_LEAP_YEAR_REGEX + "$)|(^" + LEAP_YEAR_REGEX + "$)";


    public static final String TIME_VALIDATION_REGEX = "^(00|[0,1][0-9]|2[0-3])([0-5][0-9])$";
    public static final String DATE_AND_TIME_VALIDATION_REGEX =
              "((" + NON_LEAP_YEAR_REGEX + ")|(" + LEAP_YEAR_REGEX + "))(,)"
                      + "(00|[0,1][0-9]|2[0-3])([0-5][0-9])$";
    public static final String LATEST_TIME_IN_DAY = "2359";
    public static final ArrayList<String> FRIENDLY_COMMANDS = initArrayList();

    protected static final DateTimeFormatter PARSE_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter PARSE_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a",
            Locale.ENGLISH);

    private static final Logger logger = LogsCenter.getLogger(DueDate.class);

    public final String value;

    private final LocalDate date;
    private final LocalDateTime dateTime;
    private final LocalTime time;

    /**
     * Constructs a {@code DueDate}.
     *
     * @param date Date of due date.
     * @param time Time of due date.
     */
    public DueDate(String date, String time) {
        requireNonNull(date);
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS_TIME);
        logger.info("Date: " + date + "\nTime: " + time);
        if (isValidFriendlyDate(date)) {
            this.date = friendlyToDate(date);
        } else {
            checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS_DATE);
            this.date = LocalDate.parse(date, PARSE_DATE_FORMAT);
        }
        this.time = LocalTime.parse(time, PARSE_TIME_FORMAT);
        this.dateTime = LocalDateTime.of(this.date, this.time);
        this.value = this.dateTime.format(OUTPUT_FORMAT);
    }

    /**
     * Constructs a {@code DueDate}.
     *
     * @param date Date of due date.
     */
    public DueDate(String date) {
        this(date, LATEST_TIME_IN_DAY);
    }

    /**
     * Constructs a {@code DueDate}.
     *
     * @param dateTime Date and time of dueDate
     */
    public DueDate(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.dateTime = dateTime;
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
        this.value = this.dateTime.format(OUTPUT_FORMAT);
    }
    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid Time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    /**
     * Checks if the given string is one of the valid friendly date commands.
     *
     * @param date user input for a date.
     * @return true if the given string is a valid friendly date command.
     */
    public static boolean isValidFriendlyDate(String date) {
        for (String s : FRIENDLY_COMMANDS) {
            if (s.equals(date.toLowerCase())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns true if a given string contains a valid Friendly Date and time.
     *
     * @param date user input friendly date and time
     * @return true if matches regex
     */
    public static boolean isValidFriendlyDateAndTime(String date) {
        String[] splitDate = date.split(",");
        return isValidFriendlyDate(splitDate[0]) && splitDate[1].matches(TIME_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid DueDate output.
     */
    public static boolean isValidDueDateOutput(String test) {
        try {
            OUTPUT_FORMAT.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Creates a due date based on the format in the Json file
     *
     * @param dueDate dueDate in the Json file
     * @return the corresponding dueDate
     */
    public static DueDate createDueDate(String dueDate) {
        requireNonNull(dueDate);
        checkArgument(isValidDueDateOutput(dueDate), OUTPUT_CONSTRAINTS);
        LocalDateTime dateTime = LocalDateTime.parse(dueDate, OUTPUT_FORMAT);
        return new DueDate(dateTime);
    }

    /**
     * * Returns true if the date and time is valid.
     */
    public static boolean isValidDateAndTime(String date) {
        return date.matches(DATE_AND_TIME_VALIDATION_REGEX);
    }

    /**
     * Returns true if date is valid due date form.
     */
    public static boolean isValidDueDate(String date) {
        return isValidDate(date) || isValidDateAndTime(date) || isValidFriendlyDate(date)
                || isValidFriendlyDateAndTime(date);
    }

    /**
     * Instantiates the global arraylist with the friendly commands.
     *
     * @return an arraylist of String containing the friendly commands.
     */
    public static ArrayList<String> initArrayList() {
        ArrayList<String> temp = new ArrayList<>();
        temp.add("mon");
        temp.add("tue");
        temp.add("wed");
        temp.add("thu");
        temp.add("fri");
        temp.add("sat");
        temp.add("sun");
        temp.add("tmr");
        temp.add("today");
        temp.add("week");
        return temp;
    }

    /**
     * Converts a friendly date into a LocalDate object.
     *
     * @param friendlyDate user given friendly date.
     * @return user given friendly date in LocalDate format.
     */
    public static LocalDate friendlyToDate(String friendlyDate) {
        LocalDate currentDate = LocalDate.now();
        String lowerCaseFriendlyDate = friendlyDate.toLowerCase();

        switch (lowerCaseFriendlyDate) {
        case "mon":
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            break;
        case "tue":
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
            break;
        case "wed":
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
            break;
        case "thu":
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
            break;
        case "fri":
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            break;
        case "sat":
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
            break;
        case "sun":
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
            break;
        case "tmr":
            currentDate = currentDate.plusDays(1);
            break;
        case "today":
            break;
        case "week":
            currentDate = currentDate.plusDays(7);
            break;
        default:
            break;
        }
        return currentDate;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueDate // instanceof handles nulls
                && value.equals(((DueDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(DueDate d) {
        return this.dateTime.compareTo(d.dateTime);
    }
}
