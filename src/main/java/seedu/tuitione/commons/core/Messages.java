package seedu.tuitione.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    // header templates
    public static final String HEADER_ALERT = "⚠\tAlert:\n";
    public static final String HEADER_ERROR = "❌\tError:\n";
    public static final String HEADER_SUCCESS = "✔\tSuccess:\n";
    public static final String HEADER_UPDATE = "ℹ\tUpdate:\n";

    public static final String MESSAGE_UNKNOWN_COMMAND = "❓\tUnknown command";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = HEADER_ALERT + "Invalid command format!\n\n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = HEADER_ERROR
            + "The student index cannot be found";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = HEADER_ERROR
            + "The lesson index cannot be found";
    public static final String MESSAGE_PLURAL_STUDENT_LISTED_OVERVIEW = HEADER_UPDATE + "%1$d students found!";
    public static final String MESSAGE_SINGULAR_STUDENT_LISTED_OVERVIEW = HEADER_UPDATE + "%1$d student found!";
    public static final String MESSAGE_LESSON_FOUND_OVERVIEW = "%1$d lessons(s) found!";
    public static final String MESSAGE_STUDENTS_FOUND_OVERVIEW = "%1$d student(s) found!";
}
