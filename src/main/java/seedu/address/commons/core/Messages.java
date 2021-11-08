package seedu.address.commons.core;

import seedu.address.commons.status.LoginStatus;

/**
 * Container for user visible messages.
 */
public class Messages {
    /**
     * Represents the MESSAGE_UNKNOWN_COMMAND.
     */
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";

    /**
     * Represents the MESSAGE_INVALID_COMMAND_FORMAT.
     */
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    /**
     * Represents the MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX.
     */
    public static final String MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX = "The member index provided is invalid.";

    /**
     * Represents the MESSAGE_INVALID_MEMBER_DISPLAYED_ID.
     */
    public static final String MESSAGE_INVALID_MEMBER_DISPLAYED_ID = "The member ID provided is invalid.";

    /**
     * Represents the MESSAGE_INVALID_TRANSACTION_DISPLAYED_ID.
     */
    public static final String MESSAGE_INVALID_TRANSACTION_DISPLAYED_ID = "The transaction ID provided is invalid.";

    /**
     * Represents the MESSAGE_INVALID_RESERVATION_DISPLAYED_ID.
     */
    public static final String MESSAGE_INVALID_RESERVATION_DISPLAYED_ID = "The reservation ID provided is invalid.";

    /**
     * Represents the MESSAGE_MEMBERS_LISTED_OVERVIEW.
     */
    public static final String MESSAGE_MEMBERS_LISTED_OVERVIEW = "%1$d members listed!";

    /**
     * Represents the MESSAGE_PERMISSION_DENIED.
     */
    public static final String MESSAGE_PERMISSION_DENIED =
            "Permission denied! Please login as " + LoginStatus.MANAGER + ".";

}
