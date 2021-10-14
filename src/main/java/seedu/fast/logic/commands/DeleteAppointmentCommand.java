package seedu.fast.logic.commands;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Person;

import java.util.List;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "dappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes appointment with the person identified"
            + " by the index number used in the last person listing.\n\n"
            + "Parameters (to add an appointment): \nINDEX (must be a positive integer), "
            + "Examples: \n" + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted appointment with %1$s";
    public static final String MESSAGE_DELETE_APPOINTMENT_FAILED = "No appointment with %1$s yet!";

    private final Index index;
    private final Appointment appointment;

    /**
     * Construct for an {@code AppointmentCommand}
     *
     * @param index       index of the person in the filtered person list to delete the appointment
     * @param appointment appointment scheduled with the person
     */
    public DeleteAppointmentCommand(Index index, Appointment appointment) {
        this.index = index;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRemark(), personToEdit.getTags(), appointment);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(personToEdit, editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the appointment is added, deleted or updated
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit, Person editedPerson) {
        String message = "";
        boolean isEmptyStatusAfter = editedPerson.getAppointment().getDate().equals(Appointment.NO_APPOINTMENT);
        boolean isEmptyStatusBefore = personToEdit.getAppointment().getDate().equals(Appointment.NO_APPOINTMENT);

        if (isEmptyStatusAfter && !isEmptyStatusBefore) {
            message = MESSAGE_DELETE_APPOINTMENT_SUCCESS;
        } else if (isEmptyStatusAfter && isEmptyStatusBefore) {
            message = MESSAGE_DELETE_APPOINTMENT_FAILED;
        } else {
            // should never reach here
            message = MESSAGE_INVALID_COMMAND_FORMAT;
        }

        return String.format(message, editedPerson.getName().fullName);
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

