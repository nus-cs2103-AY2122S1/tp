package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ContactHasTagPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book which have the specified tags attached to them.
 * tag matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts which have "
            + "the specified tag (case-insensitive) attached to them and displays them as a list with index numbers.\n"
            + "Parameters: TAGNAME [MORE_TAGNAMES]...\n"
            + "Example: " + COMMAND_WORD + " r/staff f/computing";

    private final ContactHasTagPredicate predicate;

    public FilterCommand(ContactHasTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }

}
