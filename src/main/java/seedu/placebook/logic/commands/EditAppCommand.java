package seedu.placebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.placebook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.placebook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.placebook.logic.parser.CliSyntax.PREFIX_ENDDATETIME;
import static seedu.placebook.logic.parser.CliSyntax.PREFIX_STARTDATETIME;
import static seedu.placebook.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;
import java.util.Optional;

import seedu.placebook.commons.core.Messages;
import seedu.placebook.commons.core.index.Index;
import seedu.placebook.commons.util.CollectionUtil;
import seedu.placebook.logic.commands.exceptions.CommandException;
import seedu.placebook.model.Model;
import seedu.placebook.model.person.Address;
import seedu.placebook.model.person.UniquePersonList;
import seedu.placebook.model.schedule.Appointment;
import seedu.placebook.model.schedule.TimePeriod;
import seedu.placebook.ui.Ui;

public class EditAppCommand extends Command {

    public static final String COMMAND_WORD = "editApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_STARTDATETIME + "DATE (dd-MM-yyyy) TIME (HHmm) ] "
            + "[" + PREFIX_ENDDATETIME + "DATE (dd-MM-yyyy) TIME (HHmm) ] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ADDRESS + "Starbucks @ Raffles City "
            + PREFIX_STARTDATETIME + "14-12-2021 1600"
            + PREFIX_ENDDATETIME + "14-12-2021 1800"
            + PREFIX_DESCRIPTION + "discuss marketing strategies";

    public static final String MESSAGE_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book.";

    private final Index index;
    private final EditAppCommand.EditAppDescriptor editAppDescriptor;

    /**
     * @param index of the appointment in the filtered appointment list to edit
     * @param editAppDescriptor details to edit the appointment with
     */
    public EditAppCommand(Index index, EditAppCommand.EditAppDescriptor editAppDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppDescriptor);

        this.index = index;
        this.editAppDescriptor = new EditAppCommand.EditAppDescriptor(editAppDescriptor);
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppDescriptor);

        if (!model.getClashingAppointments(editedAppointment).isEmpty()) {
            if (!model.getClashingAppointments(editedAppointment).contains(appointmentToEdit)) {
                String clashingAppointments = model.getClashingAppointmentsAsString(editedAppointment);
                throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT
                        + '\n' + clashingAppointments);
            }
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.updateState();
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns an {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppDescriptor editAppDescriptor) {
        assert appointmentToEdit != null;

        UniquePersonList clients = appointmentToEdit.getClients();
        Address updatedLocation = editAppDescriptor.getLocation().orElse(appointmentToEdit.getLocation());
        TimePeriod updatedTimePeriod = editAppDescriptor.getTimePeriod().orElse(appointmentToEdit.getTimePeriod());
        String updatedDescription = editAppDescriptor.getDescription().orElse(appointmentToEdit.getDescription());

        return new Appointment(clients, updatedLocation, updatedTimePeriod, updatedDescription);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppCommand)) {
            return false;
        }

        // state check
        EditAppCommand e = (EditAppCommand) other;
        return index.equals(e.index)
                && editAppDescriptor.equals(e.editAppDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditAppDescriptor {
        private UniquePersonList clients;
        private Address location;
        private TimePeriod timePeriod;
        private String description;

        public EditAppDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppDescriptor(EditAppCommand.EditAppDescriptor toCopy) {

            setClients(toCopy.clients);
            setLocation(toCopy.location);
            setTimePeriod(toCopy.timePeriod);
            setDescription(toCopy.description);

        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil
                    .isAnyNonNull(location, timePeriod, description);
        }

        public void setClients(UniquePersonList clients) {
            this.clients = clients;
        }

        public UniquePersonList getClients() {
            return clients;
        }

        public void setLocation(Address location) {
            this.location = location;
        }

        public Optional<Address> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setTimePeriod(TimePeriod timePeriod) {
            this.timePeriod = timePeriod;
        }

        public Optional<TimePeriod> getTimePeriod() {
            return Optional.ofNullable(timePeriod);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppCommand.EditAppDescriptor)) {
                return false;
            }

            // state check
            EditAppCommand.EditAppDescriptor e = (EditAppCommand.EditAppDescriptor) other;

            return getClients().equals(e.getClients())
                    && getLocation().equals(e.getLocation())
                    && getTimePeriod().equals(e.getTimePeriod())
                    && getDescription().equals(e.getDescription());
        }
    }

}
