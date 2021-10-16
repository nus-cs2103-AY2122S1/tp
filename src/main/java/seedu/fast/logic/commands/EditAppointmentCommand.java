package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;
import static seedu.fast.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.util.CollectionUtil;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Person;


public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "eppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits appointment with the person identified"
            + " by the index number used in the last person listing.\n\n"
            + "Parameters (to edit an appointment): \nINDEX (must be a positive integer), "
            + "[" + PREFIX_APPOINTMENT + "DATE] (must be yyyy-mm-dd), "
            + "[" + PREFIX_APPOINTMENT_TIME + "TIME] (must be hh:mm (24-hour format), 'leave blank' to remove it), "
            + "[" + PREFIX_APPOINTMENT_VENUE + "VENUE] (maximum 30 characters long), 'leave blank' to remove it;"
            + "\n\nExamples: \n" + "To edit the entire appointment: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "2021-10-25 "
            + PREFIX_APPOINTMENT_TIME + "22:15 "
            + PREFIX_APPOINTMENT_VENUE + "Orchard Central" + "\n"
            + "To edit the date: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "2021-10-25 "
            + "To edit the time: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT_TIME + "18:30 "
            + "To edit the venue: " + COMMAND_WORD + " 1 " + PREFIX_APPOINTMENT_VENUE + "Ion \n";

    public static final String MESSAGE_UPDATE_APPOINTMENT_SUCCESS = "Updated appointment with %1$s: %2$s %3$s"
            + " %4$s";
    public static final String MESSAGE_UPDATE_APPOINTMENT_FAILED = "Unable to edit appointment. "
            + "At least one field to edit must be provided.";
    public static final String MESSAGE_UPDATE_APPOINTMENT_ERROR = "No Appointment to edit.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * Construct for an {@code EditAppointmentCommand}
     *
     * @param index index of the person in the filtered person list to edit the appointment
     * @param editAppointmentDescriptor details of appointment to edit with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRemark(), personToEdit.getTags(),
                createEditedAppointment(personToEdit.getAppointment(), editAppointmentDescriptor),
                personToEdit.getCount());

        if (personToEdit.getAppointment().getDate().equalsIgnoreCase(Appointment.NO_APPOINTMENT)) {
            throw new CommandException(MESSAGE_UPDATE_APPOINTMENT_ERROR);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
            EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        String updatedDate = editAppointmentDescriptor.getDate().orElse(appointmentToEdit.getDate());
        String updatedTime = editAppointmentDescriptor.getTime().orElse(appointmentToEdit.getTimeFormatted());
        String updatedVenue = editAppointmentDescriptor.getVenue().orElse(appointmentToEdit.getVenue());

        return new Appointment(updatedDate, updatedTime, updatedVenue);
    }

    /**
     * Generates the message when an appointment is edited successfully.
     *
     * @param editedPerson The contact with the new appointment.
     * @return A string indicating that the appointment has been updated.
     */
    private String generateSuccessMessage(Person editedPerson) {
        return String.format(MESSAGE_UPDATE_APPOINTMENT_SUCCESS, editedPerson.getName().fullName,
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
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;
        return index.equals(e.index)
                && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private String date;
        private String time;
        private String venue;

        /**
         * Construct for an {@code EditAppointmentDescriptor}
         */
        public EditAppointmentDescriptor() {
        }

        /**
         * Copy constructor for {@code EditAppointmentDescriptor}.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setDate(toCopy.date);
            setTime(toCopy.time);
            setVenue(toCopy.venue);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, time, venue);
        }

        /**
         * Sets the date to the input date.
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         * Returns an Optional describing the date if it is present.
         * Otherwise, returns an empty Optional.
         *
         * @return an Optional containing the date if it is present, or an empty Optional if it is not present.
         */
        public Optional<String> getDate() {
            return Optional.ofNullable(date);
        }

        /**
         * Sets the time to the input time.
         */
        public void setTime(String time) {
            this.time = time;
        }

        /**
         * Returns an Optional describing the time if it is present.
         * Otherwise, returns an empty Optional.
         *
         * @return an Optional containing the time if it is present, or an empty Optional if it is not present.
         */
        public Optional<String> getTime() {
            return Optional.ofNullable(time);
        }

        /**
         * Sets the venue to the input venue.
         */
        public void setVenue(String venue) {
            this.venue = venue;
        }

        /**
         * Returns an Optional describing the venue if it is present.
         * Otherwise, returns an empty Optional.
         *
         * @return an Optional containing the venue if it is present, or an empty Optional if it is not present.
         */
        public Optional<String> getVenue() {
            return Optional.ofNullable(venue);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getDate().equals(e.getDate())
                    && getTime().equals(e.getTime())
                    && getVenue().equals(e.getVenue());
        }
    }


}
