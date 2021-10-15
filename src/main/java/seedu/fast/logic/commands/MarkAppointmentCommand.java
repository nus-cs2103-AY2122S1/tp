package seedu.fast.logic.commands;

import static seedu.fast.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.fast.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Person;

public class MarkAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks an appointment with the person identified as completed"
            + " by the index number used in the last person listing.\n\n"
            + "Parameters (to add an appointment): \nINDEX (must be a positive integer), "
            + "Examples: \n" + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_MARK_APPOINTMENT_SUCCESS = "Completed an appointment with "
            + "%1$s %2$s %3$s %4$s";
    public static final String MESSAGE_MARK_APPOINTMENT_FAILURE = "No Appointment exist!";

    private final Index index;
    private final Appointment appointment;

    /**
     * Construct for an {@code MarkAppointmentCommand}
     *
     * @param index index of the person in the filtered person list to mark the appointment as completed
     * @param appointment the existing appointment that has been completed
     */
    public MarkAppointmentCommand(Index index, Appointment appointment) {
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

        // No appointment -> no reason to be able to mark it as completed.
        if (personToEdit.getAppointment().getDate().equalsIgnoreCase(Appointment.NO_APPOINTMENT)) {
            throw new CommandException(MESSAGE_MARK_APPOINTMENT_FAILURE);
        }

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRemark(), personToEdit.getTags(), appointment,
                personToEdit.getCount().incrementAppointmentCount());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(personToEdit));
    }

    /**
     * Generates a command execution success message when the appointment is marked as completed successfully.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {

        return String.format(MESSAGE_MARK_APPOINTMENT_SUCCESS, personToEdit.getName().fullName,
                personToEdit.getAppointment().getDate(),
                personToEdit.getAppointment().getTimeFormatted(),
                personToEdit.getAppointment().getVenue());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkAppointmentCommand)) {
            return false;
        }

        // state check
        MarkAppointmentCommand e = (MarkAppointmentCommand) other;
        return index.equals(e.index)
                && appointment.equals(e.appointment);
    }
}
