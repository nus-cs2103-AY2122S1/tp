package seedu.notor.commons.core;

import seedu.notor.logic.commands.HelpCommand;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command\n" + HelpCommand.MESSAGE_USAGE;
    public static final String MESSAGE_UNEXPECTED_ERROR = "Unexpected error!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid.";
    public static final String MESSAGE_INVALID_GROUP_DISPLAYED_INDEX = "The group index provided is invalid.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_PERSONS_NOT_LISTED = "Persons are not listed. Please switch to Person list"
            + "('p /list).";
    public static final String MESSAGE_PERSON_ARCHIVE_NOT_LISTED = "Person archive is not listed. "
            + "Please switch to Person Archive list (p /lar).";
    public static final String MESSAGE_GROUPS_LISTED_OVERVIEW = "%1$d groups listed!";
    public static final String MESSAGE_SUBGROUPS_LISTED_OVERVIEW = "%1$d subgroups listed!";
    public static final String MESSAGE_GROUPS_OR_SUBGROUP_NOT_LISTED = "Groups or SubGroups are not listed. " + "\n"
            + "Please use 'group /l' or 'group /find (params) to list groups.\nMore information can be found on the "
            + "help page";
    public static final String MESSAGE_GROUPS_NOT_LISTED = "Groups are not listed. Please list groups ('group /l' or "
            + "'group /find [params])\nMore information can be found on the help page";
    public static final String MESSAGE_PEOPLE_NOT_LISTED = "People are not listed. Please list persons ('person "
            + "/list' or 'person /find [params].\nMore information can be found on the help page";
}
