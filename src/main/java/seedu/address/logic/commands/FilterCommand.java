package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Filters contacts in the address book by category.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    private final String category;

    /**
     * constructor for FilterCommand
     * @param category type of contacts to be filtered
     */
    public FilterCommand(String category) {
        requireNonNull(category);
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format("Filter command not implemented yet. Filter contacts in %s category.", category));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        // state check
        FilterCommand e = (FilterCommand) other;
        return category.equals(e.category);
    }
}
