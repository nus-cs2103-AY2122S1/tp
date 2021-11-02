package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts the contact list by name in alphabetical order.
 */
public class SortCommandName extends SortCommand {
    private static final String MESSAGE_SORTED_BY_SUCCESS = "by name";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortList("name");
        return new CommandResult(String.format(MESSAGE_SORT_CONTACT_SUCCESS, MESSAGE_SORTED_BY_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof SortCommandName;
    }
}
