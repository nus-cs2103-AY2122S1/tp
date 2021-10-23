package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Sorts member list in alphabetical order.
 */
public class SortMemberCommand extends Command {
    public static final String COMMAND_WORD = "sortm";

    public static final String MESSAGE_SUCCESS = "Sorted all members in alphabetical order";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortMemberList();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
