package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command! \n"
                    + "Type 'help' to view our User Guide to learn more about what commands are accepted.";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Warning: Invalid command format! \n%1$s";

    public static final String MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX = "Warning: No valid index is provided!";

    public static final String MESSAGE_INDEX_EXCEEDS_LIST_LENGTH =
            "The index provided exceeds the length of the displayed list!";

    public static final String MESSAGE_APPLICATION_LISTED_OVERVIEW = "%1$d %2$s listed";

    public static final String MESSAGE_INDEX_REQUIREMENT = "INDEX "
        + "(must be a positive integer that doesn't exceed the length of the displayed list)";

}
