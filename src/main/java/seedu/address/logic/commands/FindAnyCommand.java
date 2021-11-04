package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FindAnyPredicate;


/**
 * Finds and lists all persons in address book whose name contains ANY of the argument keywords provided.
 * Keyword matching is case insensitive.
 */
public class FindAnyCommand extends Command {

    public static final String COMMAND_WORD = "findAny";
    public static final String COMMAND_DESCRIPTION = "Finds all persons whose names contain ANY of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n";
    public static final String COMMAND_EXAMPLE = "Parameters: n/[name] ... t/[tag] ...\n"
            + "Note that Users can opt for case-sensitive search on Tags by including the 'c/' flag "
            + "after the command word\n"
            + "Example: " + COMMAND_WORD + " n/alice n/bob t/friends t/colleagues";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;

    private final FindAnyPredicate findAnyPredicate;

    public FindAnyCommand(FindAnyPredicate findAnyPredicate) {
        this.findAnyPredicate = findAnyPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(findAnyPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || ((other instanceof FindAnyCommand) // instanceof handles nulls
                && findAnyPredicate.equals(((FindAnyCommand) other).findAnyPredicate)); // state check
    }
}
