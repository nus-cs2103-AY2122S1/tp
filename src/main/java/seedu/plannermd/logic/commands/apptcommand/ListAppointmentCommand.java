package seedu.plannermd.logic.commands.apptcommand;

import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;

/**
 * Sets the appointment tab in the plannermd to display its default layout
 * which shows all the appointments on the current day.
 */
public class ListAppointmentCommand extends AppointmentCommand {

    public static final String COMMAND_WORD = "appt -l";
    public static final String MESSAGE_SUCCESS = "Listed all appointments for today.";
    public static final String MESSAGE_NO_APPOINTMENTS_TODAY = "There are no appointments for the current day.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredAppointmentList(AppointmentFilters.todayAppointmentFilter().collectAllFilters());
        if (model.getFilteredAppointmentList().size() == 0) {
            return new CommandResult(MESSAGE_NO_APPOINTMENTS_TODAY);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
