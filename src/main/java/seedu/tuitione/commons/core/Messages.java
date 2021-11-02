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

    public static final String MESSAGE_UNKNOWN_COMMAND = "❓\tUnknown command";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = HEADER_ALERT + "Invalid command format!\n\n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = HEADER_ERROR
            + "The student index cannot be found.";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = HEADER_ERROR
            + "The lesson index cannot be found.";

    public static final String MESSAGE_LESSON_FOUND_OVERVIEW = "%1$d lessons(s) found!";
    public static final String MESSAGE_STUDENTS_FOUND_OVERVIEW = "%1$d student(s) found!";
    public static final String MESSAGE_NO_STUDENTS_FOUND_OVERVIEW = "No students found!";

    public static final String MESSAGE_DUPLICATE_STUDENT_FOUND = "This student already exists in TuitiONE.";
    public static final String MESSAGE_DUPLICATE_LESSON_FOUND = "This lesson already exists in TuitiONE.";


    // MESSAGE GENERATION METHODS

    /**
     * Generates an alert message String with the supplied content String.
     */
    public static String generateAlert(String content) {
        return HEADER_ALERT + content;
    }

    /**
     * Generates an error message String with the supplied content String.
     */
    public static String generateError(String content) {
        return HEADER_ERROR + content;
    }

    /**
     * Generates an success message String with the supplied content String.
     */
    public static String generateSuccess(String content) {
        return HEADER_SUCCESS + content;
    }

    /**
     * Generates an update message String with the supplied content String.
     */
    public static String generateUpdate(String content) {
        return HEADER_UPDATE + content;
    }
}
