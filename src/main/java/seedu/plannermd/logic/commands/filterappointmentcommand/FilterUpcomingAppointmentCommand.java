package seedu.plannermd.logic.commands.filterappointmentcommand;

import seedu.plannermd.logic.commands.Command;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;

public class FilterUpcomingAppointmentCommand extends Command {

    private AppointmentFilters filters;

    public FilterUpcomingAppointmentCommand(AppointmentFilters filters) {
        this.filters = filters;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        this.filters.collectAllFilters();
        return null;
    }
}
