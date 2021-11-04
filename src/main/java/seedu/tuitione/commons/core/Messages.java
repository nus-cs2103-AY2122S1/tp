package seedu.tuitione.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {


    // HEADER TEMPLATES

    public static final String HEADER_ALERT = "⚠\tAlert:\n";
    public static final String HEADER_ERROR = "❌\tError:\n";
    public static final String HEADER_SUCCESS = "✔\tSuccess:\n";
    public static final String HEADER_UPDATE = "ℹ\tUpdate:\n";


    // MESSAGE TEMPLATES

    public static final String MESSAGE_UNKNOWN_COMMAND = "❓\tUnknown command\n"
            + "Type in 'help' to see the list of commands available.";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = HEADER_ALERT + "Invalid command format.\n\n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = HEADER_ERROR
            + "The student index cannot be found.";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = HEADER_ERROR
            + "The lesson index cannot be found.";

    public static final String MESSAGE_LESSON_FOUND_OVERVIEW = "%1$d lessons(s) found.";
    public static final String MESSAGE_STUDENTS_FOUND_OVERVIEW = "%1$d student(s) found.";
    public static final String MESSAGE_NO_STUDENTS_FOUND_OVERVIEW = "No students found.";

    public static final String MESSAGE_DUPLICATE_STUDENT_FOUND = "This student already exists in TuitiONE.";
    public static final String MESSAGE_DUPLICATE_LESSON_FOUND = "This lesson already exists in TuitiONE.";


    // PARSER MESSAGES

    public static final String MESSAGE_INVALID_INDEX = HEADER_ALERT + "Index provided is not a valid number.\n"
            + "Ensure that your inputted number is a legitimate integer, not 0, not negative, and not too large.";
    public static final String MESSAGE_INVALID_TIME = HEADER_ALERT + "Time given is in an invalid format.\n"
            + "Ensure that your inputted time is in 24 hour formatting.";
    public static final String MESSAGE_INVALID_DAY = HEADER_ALERT + "Day given is in an invalid format.\n"
            + "Ensure that your inputted day is of the first 3 characters, e.g. 'wed'.";
    public static final String MESSAGE_INVALID_PRICE_NOT_NUMBER = HEADER_ALERT
            + "Price formatting is invalid, it is not a number.\n"
            + "Ensure that your inputted price is a legitimate decimal number, non-zero and non-negative, "
            + "and not too large or have too many decimal places.\n"
            + "Also note that your decimal price will be rounded off to 2 decimal places.";
}
