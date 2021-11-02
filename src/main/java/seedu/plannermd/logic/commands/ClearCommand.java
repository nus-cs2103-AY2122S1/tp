package seedu.plannermd.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.plannermd.model.Model;
import seedu.plannermd.model.PlannerMd;

/**
 * Clears the PlannerMD.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "IWANTTOCLEAREVERYTHING";
    public static final String MESSAGE_SUCCESS = "PlannerMD has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPlannerMd(new PlannerMd());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
