package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FindPredicate;


/**
 * Finds and lists all contacts in address book whose details contain ALL of the argument keywords provided.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_DESCRIPTION = "Finds all contacts whose names contain ALL of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n";
    public static final String COMMAND_EXAMPLE = "Parameters: n/[name] ... t/[tag] ...\n"
            + "Note that users can opt for case-sensitive search on Tags by including the 'c/' flag "
            + "after the command word\n"
            + "Example: " + COMMAND_WORD + " n/alice n/bob t/friends t/colleagues";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;
    public static final String CASE_SENSITIVE_FLAG_FORMAT_MESSAGE = "The case-sensitive flag `c/` must come "
            + "right after the " + "command word!\n" + "For example, rather than 'find n/NAME c/ t/TAG' or "
            + "'find n/NAME t/TAG c/', it should be 'find c/ n/NAME t/TAG' instead.";

    private final FindPredicate findPredicate;

    public FindCommand(FindPredicate findPredicate) {
        this.findPredicate = findPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(findPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || ((other instanceof FindCommand) // instanceof handles nulls
                && findPredicate.equals(((FindCommand) other).findPredicate)); // state check
    }
}
