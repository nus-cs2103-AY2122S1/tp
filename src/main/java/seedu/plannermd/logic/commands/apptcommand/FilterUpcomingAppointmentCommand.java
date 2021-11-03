package seedu.plannermd.logic.commands.apptcommand;

import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;

/**
 * Filters the appointment records in the plannermd, showing only upcoming appointments.
 */
public class FilterUpcomingAppointmentCommand extends FilterCommand {

    public static final String COMMAND_WORD = "appt -u";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter all upcoming appointments according to the"
            + " filters parameters given.\n"
            + "Parameters: [" + PREFIX_PATIENT + "PATIENT NAME] [" + PREFIX_DOCTOR + "DOCTOR_NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PATIENT + "Alice " + PREFIX_DOCTOR + "Bob";

    /**
     * Creates a new FilterUpcomingAppointmentCommand.
     *
     * @param filters The filter to apply to the appointment list in the plannermd.
     */
    public FilterUpcomingAppointmentCommand(AppointmentFilters filters) {
        super(filters);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterUpcomingAppointmentCommand)) {
            return false;
        }

        // state check
        FilterUpcomingAppointmentCommand e = (FilterUpcomingAppointmentCommand) other;
        return getFilters().equals(e.getFilters());
    }
}
