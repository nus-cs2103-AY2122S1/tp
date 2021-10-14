package seedu.fast.logic.commands;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.util.CollectionUtil;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Address;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.Email;
import seedu.fast.model.person.Name;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.Phone;
import seedu.fast.model.person.Remark;
import seedu.fast.model.tag.Tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_DELETE_APPOINTMENT;
import static seedu.fast.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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

    public static final String MESSAGE_UPDATE_APPOINTMENT_SUCCESS = "Updated appointment with %1$s: %2$s %3$s "
            + "%4$s";
    public static final String MESSAGE_UPDATE_APPOINTMENT_FAILED = "Unable to edit appointment. " +
            "At least one field to edit must be provided.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

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
                createEditedAppointment(personToEdit.getAppointment(), editAppointmentDescriptor));

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private static Appointment createEditedAppointment(Appointment appointmentToEdit, EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        String updatedDate = editAppointmentDescriptor.getDate().orElse(appointmentToEdit.getDate());
        String updatedTime = editAppointmentDescriptor.getTime().orElse(appointmentToEdit.getTime());
        String updatedVenue = editAppointmentDescriptor.getVenue().orElse(appointmentToEdit.getVenue());

        return new Appointment(updatedDate, updatedTime, updatedVenue);
    }

    private String generateSuccessMessage(Person editedPerson) {
        return String.format(MESSAGE_UPDATE_APPOINTMENT_SUCCESS, editedPerson.getName().fullName,
                editedPerson.getAppointment().getDate(),
                editedPerson.getAppointment().getTime(),
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


    public static class EditAppointmentDescriptor {
        private String date;
        private String time;
        private String venue;

        public EditAppointmentDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
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

        public void setDate(String date) {
            this.date = date;
        }

        public Optional<String> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Optional<String> getTime() {
            return Optional.ofNullable(time);
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

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
