package seedu.plannermd.logic.commands.apptcommand;

import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_END;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;

/**
 * Filters the appointment records in the plannermd.
 */
public class FilterAppointmentCommand extends FilterCommand {

    public static final String COMMAND_WORD = "appt -f";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter all appointments according to the"
            + " filters parameters given.\n"
            + "Parameters: [" + PREFIX_PATIENT + "PATIENT NAME] [" + PREFIX_DOCTOR + "DOCTOR_NAME] ["
            + PREFIX_START + "START DATE] [" + PREFIX_END + "END DATE]\n"
            + "Example: "
            + COMMAND_WORD + " " + PREFIX_PATIENT + "Alice " + PREFIX_DOCTOR + "Bob "
            + PREFIX_START + "15/10/2021 " + PREFIX_END + "18/10/2021";

    /**
     * Creates a new FilterAppointmentCommand object.
     *
     * @param filters The filter to be applied tp the appointment list in the plannermd.
     */
    public FilterAppointmentCommand(AppointmentFilters filters) {
        super(filters);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterAppointmentCommand)) {
            return false;
        }

        // state check
        FilterAppointmentCommand e = (FilterAppointmentCommand) other;
        return getFilters().equals(e.getFilters());
    }
}
