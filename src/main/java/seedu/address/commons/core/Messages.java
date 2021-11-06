package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_HELP_TIP = "💡 TIP: Type \"help\" to view the valid commands!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!" + "\n" + MESSAGE_HELP_TIP;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid!";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX =
            "The lesson index provided is invalid for this student";
    public static final String MESSAGE_DUPLICATE_STUDENT = "A student with the exact same name already exists!";
    public static final String MESSAGE_NO_PARAMS = "This command should not have any parameters!";

}
