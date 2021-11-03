package seedu.plannermd.logic.commands.apptcommand;

import java.util.function.Predicate;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.appointment.Appointment;

/**
 * Represents an Filter command with hidden internal logic and the ability to be executed.
 */
public abstract class FilterCommand extends AppointmentCommand {

    private AppointmentFilters filters;

    protected FilterCommand(AppointmentFilters filters) {
        this.filters = filters;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Predicate<Appointment> allFilters = filters.collectAllFilters();
        model.updateFilteredAppointmentList(allFilters);
        return new CommandResult(String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                model.getFilteredAppointmentList().size()));
    }

    protected AppointmentFilters getFilters() {
        return filters;
    }
}
