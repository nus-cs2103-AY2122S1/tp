package seedu.tuitione.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "❓\tUnknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "⚠\tAlert:\n\nInvalid command format!\n\n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX =
            "❌\tIndex Error:\n\nThe student index cannot be found";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX =
            "❌\tIndex Error:\n\nThe lesson index cannot be found";
    public static final String MESSAGE_PLURAL_STUDENT_LISTED_OVERVIEW = "ℹ\tUpdate:\n\n%1$d students found!";
    public static final String MESSAGE_SINGULAR_STUDENT_LISTED_OVERVIEW = "ℹ\tUpdate:\n\n%1$d student found!";
    public static final String MESSAGE_LESSON_FOUND_OVERVIEW = "%1$d lessons(s) found!";
    public static final String MESSAGE_STUDENTS_FOUND_OVERVIEW = "%1$d student(s) found!";
}
