package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ITEM_DISPLAYED_INDEX = "The item index provided is invalid";
    public static final String MESSAGE_ITEMS_LISTED_OVERVIEW = "%1$d items listed!";
    public static final String MESSAGE_INVALID_COUNT_FORMAT = "The count provided must be an integer!\n"
            + "Minimum: 1\n" + "Maximum: 999,999";
    public static final String MESSAGE_INVALID_COUNT_INDEX = "The index provided must be a number (can't be >1 number)"
            + " and cannot be 0 or negative!";
    public static final String MESSAGE_INVALID_PRICE_FORMAT = "Prices provided must be numerical values!";
    public static final String MESSAGE_INVALID_PRICE_RANGE =
            "Prices provided must be at least $0 and less than $10,000,000!";
    public static final String MESSAGE_INVALID_ID_FORMAT = "The id provided must be integer!";
    public static final String MESSAGE_INVALID_ID_LENGTH_AND_SIGN = "The id provided must be positive"
            + " and at most 6 digits!";
}
