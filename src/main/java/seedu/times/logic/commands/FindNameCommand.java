package seedu.times.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.times.commons.core.Messages;
import seedu.times.model.Model;
import seedu.times.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.times.ui.TabName;

/**
 * Finds and lists all persons in Timestable whose name contains any of the search terms.
 * Keyword matching is case insensitive.
 */
public class FindNameCommand extends Command {

    public static final String COMMAND_WORD = "findname";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: NAME [, [NAME]...]\n"
            + "Example: " + COMMAND_WORD + " alice, bob lim, charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindNameCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        updateView(TabName.STUDENTS);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindNameCommand // instanceof handles nulls
                && predicate.equals(((FindNameCommand) other).predicate)); // state check
    }
}
