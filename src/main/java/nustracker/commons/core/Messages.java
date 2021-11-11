package nustracker.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENTID = "No student has the student ID: %1$s";
    public static final String MESSAGE_INVALID_EVENT_NAME = "The event %1$s does not exist";
    public static final String MESSAGE_INVALID_MAJOR = "The major %1$s is invalid";
    public static final String MESSAGE_INVALID_YEAR = "The year %1$s is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_MULTIPLE_FILTER_FIELDS = "Cannot filter by multiple fields!";
    public static final String MESSAGE_STUDENT_LIST_NOT_SHOWN =
            "The Student list has to be shown before this command can be used.";
    public static final String MESSAGE_EVENT_LIST_NOT_SHOWN =
            "The Event list has to be shown before this command can be used.";
    public static final String MESSAGE_COMMAND_EXTRANEOUS_SLASHES =
            "Invalid command format! Commands that use parameters cannot contain extraneous"
                    + " slashes or prefixes. \n%1$s";
}
