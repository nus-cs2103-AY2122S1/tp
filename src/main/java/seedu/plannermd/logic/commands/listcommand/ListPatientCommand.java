package seedu.plannermd.logic.commands.listcommand;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.model.Model;

/**
 * Lists all persons in the plannermd to the user.
 */
public class ListPatientCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all patients";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
