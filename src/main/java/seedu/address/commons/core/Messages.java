package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX = "The customer index provided is "
            + "invalid";
    public static final String MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX = "The employee index provided is invalid";
    public static final String MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX = "The supplier index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_RESERVATIONS_LISTED_DATETIME =
            "%1$d person(s) have reservations on %2$te %2$th %2$tY, %2$tl:%2$tM %2$tp";
    public static final String MESSAGE_RESERVATIONS_LISTED_DATE_ONLY =
            "%1$d person(s) have reservations on %2$te %2$th %2$tY";
    public static final String MESSAGE_RESERVATIONS_LISTED_TIME_ONLY =
            "%1$d person(s) have reservations on %2$te %2$th %2$tY (Today), %2$tl:%2$tM %2$tp";
}
