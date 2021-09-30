package seedu.plannermd.logic.commands.findcommand;

import static java.util.Objects.requireNonNull;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in PlannerMD whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPatientCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate<Patient> predicate;

    public FindPatientCommand(NameContainsKeywordsPredicate<Patient> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPatientCommand // instanceof handles nulls
                && predicate.equals(((FindPatientCommand) other).predicate)); // state check
    }
}
