package seedu.siasa.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_POLICY_DISPLAYED_INDEX = "The policy index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_CANCELLED_COMMAND = "Command cancelled";
    public static final String MESSAGE_INVALID_COMMISSION_NUM_OF_PMT =
            "The number of payments with receivable commission should not be "
                    + "greater than the number of payments in the payment structure";

}
