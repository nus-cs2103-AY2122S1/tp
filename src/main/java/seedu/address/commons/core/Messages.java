package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command, please type 'help' to read our user guide!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX = "The client index provided is invalid";
    public static final String MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX = "The product index provided is invalid";

    public static final String MESSAGE_CLIENTS_LISTED_OVERVIEW = "%1$d client(s) listed!";
    public static final String MESSAGE_PRODUCTS_LISTED_OVERVIEW = "%1$d product(s) listed!";

    public static final String MESSAGE_VIEW_CLIENT = "Displaying details of Client (ID %1$s) at index %2$s";
    public static final String MESSAGE_VIEW_PRODUCT = "Displaying details of Product (ID %1$s) at index %2$s";
}
