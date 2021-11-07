package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_GUEST_PASSPORT_NUMBER =
            "The passport number of the guest is invalid!";
    public static final String MESSAGE_GUEST_TO_EDIT_DOES_NOT_EXIST =
            "The guest you are trying to edit does not exist!";
    public static final String MESSAGE_VENDOR_TO_EDIT_DOES_NOT_EXIST =
            "The vendor you are trying to edit does not exist!";
    public static final String MESSAGE_GUEST_TO_CHECK_OUT_DOES_NOT_EXIST =
            "The guest you are trying to check out does not exist!";
    public static final String MESSAGE_GUEST_TO_CHECK_OUT_IS_IN_ARCHIVE =
            "You cannot edit a guest that has been archived!";
    public static final String MESSAGE_GUEST_IS_IN_ARCHIVE =
            "You cannot edit a guest that has been archived!";
    public static final String MESSAGE_INVALID_VENDORID =
            "The vendor id of the vendor is invalid!";
    public static final String MESSAGE_INVALID_MULTIPLE_UNIQUE_IDENTIFIER =
            "Only one unique identifier should be provided!";
    public static final String MESSAGE_GUESTS_LISTED_OVERVIEW = "%1$d guests listed!";
    public static final String MESSAGE_VENDORS_LISTED_OVERVIEW = "%1$d vendors listed!";
    public static final String MESSAGE_MISSING_ARGUMENTS =
            "You are missing arguments in your command. Please follow the command format!";
    public static final String MESSAGE_TOO_MANY_ARGUMENTS = "Please only specify one argument";
}
