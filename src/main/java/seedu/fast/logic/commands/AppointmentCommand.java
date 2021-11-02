package seedu.fast.logic.commands;

import static seedu.fast.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;
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

/**
 * Adds/Changes the appointment with an existing person in the FAST.
 */
public class AppointmentCommand extends Command {

    public static final String COMMAND_WORD = "aa";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add appointment with the client identified"
            + " by the index number used in the last client listing.\n\n"
            + "Parameters (to add an appointment): \nINDEX (must be a positive integer), "
            + PREFIX_APPOINTMENT + "DATE (must be yyyy-mm-dd), "
            + "[" + PREFIX_APPOINTMENT_TIME + "TIME] (must be hh:mm (24-hour format)), "
            + "[" + PREFIX_APPOINTMENT_VENUE + "VENUE] (maximum 20 characters long)." + "\n\n"
            + "Examples: \n" + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "2030-10-25 "
            + PREFIX_APPOINTMENT_TIME + "22:15 "
            + PREFIX_APPOINTMENT_VENUE + "Orchard Central" + "\n"
            + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "2030-10-25 "
            + PREFIX_APPOINTMENT_TIME + "19:00 \n"
            + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "2030-10-25 "
            + PREFIX_APPOINTMENT_VENUE + "Ion \n"
            + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "2030-10-25 \n\n";

    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "Added appointment with %1$s: %2$s %3$s %4$s";
    public static final String MESSAGE_ADD_APPOINTMENT_FAILURE_APPT_EXIST = "The appointment already exists! "
            + "Use the edit command to change details, or delete it and add it again!";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Index index;
    private final Appointment appointment;

    /**
     * Construct for an {@code AppointmentCommand}
     *
     * @param index index of the person in the filtered person list to add appointment
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

        if (CommandUtil.checkIndexExceedLimit(index, lastShownList)) {
            logger.warning("-----Invalid Add Appointment Command: Invalid Index-----");
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRemark(), personToEdit.getTags(), appointment,
                personToEdit.getCount());

        if (!Appointment.isAppointmentEmpty(personToEdit.getAppointment())) {
            logger.warning("-----Invalid Add Appointment Command: Appointment Already Exist-----");
            throw new CommandException(MESSAGE_ADD_APPOINTMENT_FAILURE_APPT_EXIST);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.info("-----Add Appointment Command: Appointment added successfully-----");

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message when appointment has been added successfully.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person editedPerson) {

        return String.format(MESSAGE_ADD_APPOINTMENT_SUCCESS, editedPerson.getName().fullName,
                editedPerson.getAppointment().getDate(),
                editedPerson.getAppointment().getTimeFormatted(),
                editedPerson.getAppointment().getVenue());
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
