package seedu.notor.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_UNEXPECTED_ERROR = "Unexpected error!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_GROUP_DISPLAYED_INDEX = "The group index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_PERSONS_NOT_LISTED = "This command cannot be executed as persons are not "
            + "listed.\nPlease list persons by typing `person /list`.";
    public static final String MESSAGE_PERSON_ARCHIVE_NOT_LISTED = "This command cannot be executed as person archive"
            + " is not listed. \nPlease list the persons in the archive by typing `person /listarchive";
    public static final String MESSAGE_GROUPS_LISTED_OVERVIEW = "%1$d groups listed!";
    public static final String MESSAGE_SUBGROUPS_LISTED_OVERVIEW = "%1$d subgroups listed!";
    public static final String MESSAGE_GROUPS_OR_SUBGROUP_NOT_LISTED = "The command cannot be executed as Groups or "
            + "SubGroups are not listed. " + "\n"
            + "Please list groups (`group /list`)";
    public static final String MESSAGE_GROUPS_NOT_LISTED = "The command cannot be executed as Groups are not listed. "
            + "\n"
            + "Please list groups (`group /list`)";
}
