package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose Student ID contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindIdCommand extends Command {

    public static final String COMMAND_WORD = "findId";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose Student ID matches "
            + "the specified ID (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: Student ID \n"
            + "Example: " + COMMAND_WORD + " A1234567F";

    private final StudentIdContainsKeywordsPredicate predicate;

    public FindIdCommand(StudentIdContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindIdCommand // instanceof handles nulls
                && predicate.equals(((FindIdCommand) other).predicate)); // state check
    }
}
