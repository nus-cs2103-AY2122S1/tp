package seedu.plannermd.logic.commands.apptcommand;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_EDIT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;

import java.util.List;
import java.util.Optional;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.commons.util.CollectionUtil;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.model.appointment.Duration;
import seedu.plannermd.model.appointment.Session;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Remark;

/**
 * Edits the details of an existing appointment in the plannermd.
 */
public class EditAppointmentCommand extends AppointmentCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + FLAG_EDIT
            + " : Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) " + "[" + PREFIX_PATIENT + "PATIENT_INDEX] "
            + "[" + PREFIX_DOCTOR + "DOCTOR_INDEX] " + "[" + PREFIX_START + "DATE TIME] "
            + "[" + PREFIX_DURATION + "DURATION] " + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " " + FLAG_EDIT + " 1 "
            + PREFIX_PATIENT + "2 " + PREFIX_START + "15/2/2022 10:00 " + PREFIX_DURATION + "30";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_CLASHING_APPOINTMENT = "This appointment clashes with an existing appointment.";
    public static final String MESSAGE_INVALID_START = "Start date/time should be of the format DD/MM/YYYY HH:MM "
            + "and adhere to the following constraints:\n"
            + "1. Day must be between 1-31 (0 in front of single digit is optional)\n"
            + "2. Month must be between 1-12 (0 in front of single digit is optional)\n"
            + "3. Year must be 4 characters\n"
            + "4. Hour must be between 0-23\n"
            + "5. Minute must be between 0-59.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
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
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor, model);

        if (model.isClashAppointmentForEdited(editedAppointment, appointmentToEdit)) {
            throw new CommandException(MESSAGE_CLASHING_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns an {@code Appointment} with the details of
     * {@code appointmentToEdit} edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(
            Appointment appointmentToEdit, EditAppointmentDescriptor editAppointmentDescriptor, Model model)
            throws CommandException {
        assert appointmentToEdit != null;

        Patient updatedPatient;
        List<Patient> lastShownPatientList = model.getFilteredPatientList();
        Optional<Index> optionalPatientIndex = editAppointmentDescriptor.getPatientIndex();
        if (optionalPatientIndex.isPresent()) {
            int patientIndex = optionalPatientIndex.get().getZeroBased();
            if (patientIndex >= lastShownPatientList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
            }
            updatedPatient = lastShownPatientList.get(patientIndex);
        } else {
            updatedPatient = appointmentToEdit.getPatient();
        }

        Doctor updatedDoctor;
        List<Doctor> lastShownDoctorList = model.getFilteredDoctorList();
        Optional<Index> optionalDoctorIndex = editAppointmentDescriptor.getDoctorIndex();
        if (optionalDoctorIndex.isPresent()) {
            int doctorIndex = optionalDoctorIndex.get().getZeroBased();
            if (doctorIndex >= lastShownDoctorList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
            }
            updatedDoctor = lastShownDoctorList.get(doctorIndex);
        } else {
            updatedDoctor = appointmentToEdit.getDoctor();
        }

        AppointmentDate updatedAppointmentDate = editAppointmentDescriptor.getAppointmentDate()
                .orElse(appointmentToEdit.getAppointmentDate());

        String startTime = editAppointmentDescriptor.getStartTime()
                .orElse(appointmentToEdit.getSession().getFormattedStartTime());
        Duration duration = editAppointmentDescriptor.getDuration()
                .orElse(appointmentToEdit.getSession().duration);
        Session updatedSession = new Session(startTime, duration);
        if (!updatedSession.isEndWithinSameDay()) {
            throw new CommandException(Session.MESSAGE_END_WITHIN_SAME_DAY);
        }

        Remark updatedRemark = editAppointmentDescriptor.getRemark().orElse(appointmentToEdit.getRemark());

        return new Appointment(updatedPatient, updatedDoctor, updatedAppointmentDate, updatedSession, updatedRemark);
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
        return index.equals(e.index) && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will
     * replace the corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private Index patientIndex;
        private Index doctorIndex;
        private AppointmentDate appointmentDate;
        private String startTime;
        private Duration duration;
        private Remark remark;

        public EditAppointmentDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setPatientIndex(toCopy.patientIndex);
            setDoctorIndex(toCopy.doctorIndex);
            setAppointmentDate(toCopy.appointmentDate);
            setStartTime(toCopy.startTime);
            setDuration(toCopy.duration);
            setRemark(toCopy.remark);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(patientIndex, doctorIndex, appointmentDate, startTime, duration, remark);
        }

        public void setPatientIndex(Index patientIndex) {
            this.patientIndex = patientIndex;
        }

        public Optional<Index> getPatientIndex() {
            return Optional.ofNullable(patientIndex);
        }

        public void setDoctorIndex(Index doctorIndex) {
            this.doctorIndex = doctorIndex;
        }

        public Optional<Index> getDoctorIndex() {
            return Optional.ofNullable(doctorIndex);
        }

        public void setAppointmentDate(AppointmentDate appointmentDate) {
            this.appointmentDate = appointmentDate;
        }

        public Optional<AppointmentDate> getAppointmentDate() {
            return Optional.ofNullable(appointmentDate);
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public Optional<String> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
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

            return getPatientIndex().equals(e.getPatientIndex()) && getDoctorIndex().equals(e.getDoctorIndex())
                    && getAppointmentDate().equals(e.getAppointmentDate()) && getStartTime().equals(e.getStartTime())
                    && getDuration().equals(e.getDuration()) && getRemark().equals(e.getRemark());
        }
    }
}
