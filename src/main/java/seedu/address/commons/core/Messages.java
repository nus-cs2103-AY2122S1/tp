package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_TASK = "The size of %1$s's task list is not that big";
    public static final String MESSAGE_INVALID_INTERNAL_COMMAND_FORMAT = "Invalid internal command format! Make a "
            + "bug report?";
    public static final String MESSAGE_UNKNOWN_INTERNAL_COMMAND = "Unknown internal command! Make a "
            + "bug report?";
    public static final String MESSAGE_INVALID_REMINDER_DAY_VALUE = "The value of the day must be a positive Integer!";
    public static final String MESSAGE_PERSON_TO_EDIT_TASK_NOT_DISPLAYED =
            "The task list of the selected person is not shown on the task list panel!\n"
            + "Please view the selected person's task list first using cat INDEX, where\n"
            + "INDEX refers to the index of the person in the displayed person list.\n"
            + "Alternatively, you can use cat -A if you prefer to display every person's "
            + "task list.\n";
}
