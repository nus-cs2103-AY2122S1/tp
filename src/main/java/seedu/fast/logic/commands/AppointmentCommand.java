package seedu.fast.logic.commands;

import static seedu.fast.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Person;

/**
 * Adds/Changes the appointment with an existing person in the FAST.
 */
public class AppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appointment";
    public static final String APPOINTMENT_DELETE_COMMAND = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add appointment with the person identified"
            + " by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer), "
            + PREFIX_APPOINTMENT + "DATE (must be yyyy-mm-dd) or "
            + PREFIX_APPOINTMENT + APPOINTMENT_DELETE_COMMAND + "\n"
            + PREFIX_APPOINTMENT + "[DATE] or " + PREFIX_APPOINTMENT + APPOINTMENT_DELETE_COMMAND + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "2021-10-25 or "
            + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + APPOINTMENT_DELETE_COMMAND;

    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "Added appointment with %1$s: %2$s";
    public static final String MESSAGE_UPDATE_APPOINTMENT_SUCCESS = "Updated appointment with %1$s: %2$s";
    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted appointment with %1$s";

    private final Index index;
    private final Appointment appointment;

    /**
     * Construct for an {@code AppointmentCommand}
     *
     * @param index index of the person in the filtered person list to edit the remark
     * @param appointment appointment scheduled with the person
     */
    public AppointmentCommand(Index index, Appointment appointment) {
        requireAllNonNull(index, appointment);

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
        if (editedPerson.getAppointment().getDate().equals(Appointment.NO_APPOINTMENT)) {
            message = MESSAGE_DELETE_APPOINTMENT_SUCCESS;
        } else if (personToEdit.getAppointment().getDate().equals(Appointment.NO_APPOINTMENT)) {
            message = MESSAGE_ADD_APPOINTMENT_SUCCESS;
        } else {
            message = MESSAGE_UPDATE_APPOINTMENT_SUCCESS;
        }
        return String.format(message, editedPerson.getName().fullName, editedPerson.getAppointment().getDate());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentCommand)) {
            return false;
        }

        // state check
        AppointmentCommand e = (AppointmentCommand) other;
        return index.equals(e.index)
                && appointment.equals(e.appointment);
    }
}
