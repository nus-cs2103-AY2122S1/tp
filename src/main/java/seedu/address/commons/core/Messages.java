package seedu.address.commons.core;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_PERSON_SEARCHED = "This staff does not exist in the address book!";
    public static final String MESSAGE_INVALID_DATE_PARSED = "This date cannot be parsed, expected: YYYY-MM-DD";
    public static final String MESSAGE_INVALID_SHIFT_TIME = "The shift time provided is invalid, expected: HH:MM-HH:MM";
    public static final String MESSAGE_INVALID_TIME = "The format or range of input time is invalid";
    public static final String WRONG_NUMBER_OF_DATES = "Expects 0, 1 or 2 dates as input, received $d";
    public static final String DATES_IN_WRONG_ORDER = "The inputted end date must be after the start date.";
    public static final String FILE_NOT_FOUND = "The following file could not be accessed: ";
    public static final String SHIFT_PERIOD_PARSING_DEFAULT = "When date input is provided, it should be in "
            + "chronological order. If "
            + "only one date is provided, the end date is assumed to be seven days later. If no date is provided, "
            + "the period is assumed to be from the current date to seven days later. Expects 0, 1 or 2 date inputs.";
    public static final String DATE_RANGE_INPUT = "[" + PREFIX_DATE + "START_DATE]"
            + " [" + PREFIX_DATE + "END_DATE]";
}
