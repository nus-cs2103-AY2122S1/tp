package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Sorts the current list of items
 */

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the current list of items\n"
            + "Parameters: PREFIX\n"
            + "Example: sort T/\n"
            + "Note: ProfBook does not allow sorting by Address";

    public static final String MESSAGE_SUCCESS = "List sorted.";

    private final Prefix prefix;
    private final boolean reverse;

    /**
     * Creates a SortCommand to sort the observable list by the prefix given
     */

    public SortCommand(Prefix prefix, boolean reverse) {
        this.prefix = prefix;
        this.reverse = reverse;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortAddressBook(this.prefix, this.reverse);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SortCommand)) {
            return false;
        }
        SortCommand e = (SortCommand) other;
        return prefix.equals(e.prefix) && reverse == (e.reverse);
    }
}
