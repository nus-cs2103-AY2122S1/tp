package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.address.model.Model;

/**
 * List all appointments in PlaceBook to the user.
 */
public class ListAppCommand extends Command {
    public static final String COMMAND_WORD = "listApp";

    public static final String MESSAGE_SUCCESS = "Listed all Appointments";

    private final String sortBy;

    public ListAppCommand(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.sortFilteredAppointmentList(sortBy);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
