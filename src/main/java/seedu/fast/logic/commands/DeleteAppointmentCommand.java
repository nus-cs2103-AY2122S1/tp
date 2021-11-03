package seedu.fast.logic.commands;

import static seedu.fast.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.logging.Logger;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.util.CommandUtil;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Person;

public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "da";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes appointment with the client identified"
            + " by the index number used in the last client listing.\n\n"
            + "Parameters: \nINDEX (must be a positive integer)\n\n"
            + "Example: \n" + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted appointment with %1$s";
    public static final String MESSAGE_DELETE_APPOINTMENT_FAILED_EMPTY_APPT = "No appointment with %1$s yet!";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Index index;
    private final Appointment appointment;

    /**
     * Construct for an {@code DeleteAppointmentCommand}
     *
     * @param index index of the person in the filtered person list to delete the appointment
     * @param appointment appointment scheduled with the person
     */
    public DeleteAppointmentCommand(Index index, Appointment appointment) {
        this.index = index;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (CommandUtil.checkIndexExceedLimit(index, lastShownList)) {
            logger.warning("-----Invalid Delete Appointment Command: Invalid Index-----");
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRemark(), personToEdit.getTags(), appointment,
                personToEdit.getCount());

        String name = personToEdit.getName().fullName;

        if (Appointment.isAppointmentEmpty(personToEdit.getAppointment())) {
            logger.warning("-----Invalid Delete Appointment Command: Appointment does not exist-----");
            throw new CommandException(String.format(MESSAGE_DELETE_APPOINTMENT_FAILED_EMPTY_APPT, name));
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.info("-----Delete Appointment Command: Appointment deleted successfully-----");

        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, name));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAppointmentCommand)) {
            return false;
        }

        // state check
        DeleteAppointmentCommand e = (DeleteAppointmentCommand) other;
        return index.equals(e.index)
                && appointment.equals(e.appointment);
    }

}

