package seedu.plannermd.logic.commands.apptcommand;

import java.util.function.Predicate;

import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.appointment.Appointment;

public class FilterUpcomingAppointmentCommand extends AppointmentCommand {

    private AppointmentFilters filters;
    private String userMessage;

    public FilterUpcomingAppointmentCommand(AppointmentFilters filters, String userMessage) {
        this.filters = filters;
        this.userMessage = userMessage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Predicate<Appointment> allFilters = filters.collectAllFilters();
        model.updateFilteredAppointmentList(allFilters);

        return null;
    }
}
