package seedu.address.logic.commands;

/**
 * Sorts the contact list either by name in alphabetical order or by rating in descending order.
 */
public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of contacts either by name (in alphabetical order) "
            + "or by rating (in descending order).\n"
            + "Parameters: 'name' or 'rating'\n"
            + "Example: " + COMMAND_WORD + " name";

    public static final String MESSAGE_SORT_CONTACT_SUCCESS = "Sorted contacts %s";

    public SortCommand() {}

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }
}
