package seedu.plannermd.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all doctors in PlannerMD whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindDoctorCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all doctors whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public FindDoctorCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDoctorList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DOCTORS_LISTED_OVERVIEW, model.getFilteredDoctorList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindDoctorCommand // instanceof handles nulls
                && predicate.equals(((FindDoctorCommand) other).predicate)); // state check
    }
}
