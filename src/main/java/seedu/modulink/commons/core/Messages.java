package seedu.modulink.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_UNEXPECTED_INPUT_FORMAT = "Unexpected input found: %s. \n%s";
    public static final String MESSAGE_UNKNOWN_PREFIX_FORMAT = "Unknown prefix(es) inputted! \n%s";
    public static final String MESSAGE_DUPLICATE_PREFIX_FORMAT = "The following prefixes were "
            + "specified more than once: %s. Only one occurrence of a prefix is allowed. \n%s";
    public static final String MESSAGE_MISSING_PREFIXES_FORMAT = "The following prefixes are missing: %s. \n%s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d person(s) listed!";
    public static final String MESSAGE_NO_PERSON_LISTED = "We couldn't find anyone %s";
    public static final String MESSAGE_NO_SUCH_ID_FOUND = "No such Student ID found!";
    public static final String MESSAGE_FAVOURITE_ADDED = "%s is added as a favourite!";
    public static final String MESSAGE_PERSON_ALREADY_FAVOURITE = "The Student is already a favourite!";
    public static final String MESSAGE_FAVOURITE_REMOVED = "%s is removed from favourites.";
    public static final String MESSAGE_PERSON_IS_NOT_FAVOURITE = "The Student is not a favourite.";
    public static final String MESSAGE_NO_FAVOURITES = "There is currently no one in your favourites list.";
    public static final String MESSAGE_DUPLICATE_ON_START = "Duplicate Student IDs were detected in the data file."
            + " A new addressbook was created";

}
