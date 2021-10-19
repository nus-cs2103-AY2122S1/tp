package seedu.plannermd.logic.commands.apptcommand;

import java.util.function.Predicate;

import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.appointment.Appointment;

public class FilterAppointmentCommand extends AppointmentCommand {

    public static final String MESSAGE_USAGE = "Filter all appointments according to the"
            + "filters parameters given.";

    private AppointmentFilters filters;
    private String userMessage;

    public FilterAppointmentCommand(AppointmentFilters filters, String userMessage) {
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
