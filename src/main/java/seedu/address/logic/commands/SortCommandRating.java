package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts the contact list by rating in descending order.
 */
public class SortCommandRating extends SortCommand {
    private static final String MESSAGE_SORTED_BY_SUCCESS = "by rating";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortList("rating");
        return new CommandResult(String.format(MESSAGE_SORT_CONTACT_SUCCESS, MESSAGE_SORTED_BY_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof SortCommandRating;
    }

}
