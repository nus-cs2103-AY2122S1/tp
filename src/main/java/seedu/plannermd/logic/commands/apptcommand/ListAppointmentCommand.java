package seedu.plannermd.logic.commands.apptcommand;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentIsAfterPredicate;

/**
 * Sets the appointment tab in the plannermd to display its default layout
 * which shows all the appointments on the current day.
 */
public class ListAppointmentCommand extends AppointmentCommand {

    public static final String COMMAND_WORD = "appt -l";
    public static final String MESSAGE_SUCCESS = "Listed all appointments for today.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Predicate<Appointment> isTodayAppt = new AppointmentIsAfterPredicate(LocalDate.now());
        model.updateFilteredAppointmentList(isTodayAppt);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
