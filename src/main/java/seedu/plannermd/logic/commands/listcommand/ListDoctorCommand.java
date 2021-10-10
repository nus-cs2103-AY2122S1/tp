package seedu.plannermd.logic.commands.listcommand;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.model.Model;

public class ListDoctorCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = "Listed all doctors";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
