package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.Appointment;
import seedu.address.ui.UiManager;

/**
 * Deletes an existing appointment in the schedule
 */
public class DelAppCommand extends Command {

    public static final String COMMAND_WORD = "delApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an existing appointment from PlaceBook. "
            + "Parameters: "
            + "INDEX "
            + "Example: " + COMMAND_WORD + " "
            + "4";

    public static final String MESSAGE_SUCCESS = "Appointment deleted: %1$s";

    private final Index index;

    /**
     * Creates an DelAppCommand
     * @param index The index of the appointment to be deleted
     */
    public DelAppCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToDelete = lastShownList.get(index.getZeroBased());
        String deleteWarning = "You are about to delete:\n" + appointmentToDelete;

        if (UiManager.showDeleteDialogAndWait(deleteWarning)) {
            model.deleteAppointment(appointmentToDelete);
            model.updateState();
            return new CommandResult(String.format(MESSAGE_SUCCESS, appointmentToDelete));
        } else {
            return new CommandResult("No appointment deleted.");
        }
    }


}
