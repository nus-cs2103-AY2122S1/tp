package seedu.modulink.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.modulink.commons.core.Messages;
import seedu.modulink.model.Model;
import seedu.modulink.model.person.StudentIdContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose Student ID contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindIdCommand extends Command {

    public static final String COMMAND_WORD = "findId";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds profiles whose Student ID matches "
            + "the specified ID (case-insensitive) exactly and displays them as a list with index numbers.\n"
            + "Parameters: STUDENT_ID [MORE_STUDENT_IDS]...\n"
            + "Example: " + COMMAND_WORD + " A1234567F";

    private final StudentIdContainsKeywordsPredicate predicate;

    public FindIdCommand(StudentIdContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        int result = model.getFilteredPersonList().size();
        if (result <= 0) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_NO_PERSON_LISTED, "with this ID."));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindIdCommand // instanceof handles nulls
                && predicate.equals(((FindIdCommand) other).predicate)); // state check
    }
}
