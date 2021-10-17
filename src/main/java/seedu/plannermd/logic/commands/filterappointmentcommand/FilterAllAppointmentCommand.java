package seedu.plannermd.logic.commands.filterappointmentcommand;

import seedu.plannermd.logic.commands.Command;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.model.Model;

public class FilterAllAppointmentCommand extends Command {

    public static final String MESSAGE_USAGE = "Filter all appointments according to the"
            + "filters parameters given.";

    private AppointmentFilters filters;

    public FilterAllAppointmentCommand(AppointmentFilters filters) {
        this.filters = filters;
    }

    @Override
    public CommandResult execute(Model model) {
        this.filters.collectAllFilters();
        return null;
    }
}
