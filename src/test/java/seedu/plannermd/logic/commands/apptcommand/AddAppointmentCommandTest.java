package seedu.plannermd.logic.commands.apptcommand;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.THIRTY_MIN_APPOINTMENT;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.TWO_HOUR_APPOINTMENT;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.plannermd.commons.core.GuiSettings;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.ReadOnlyUserPrefs;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.model.appointment.UniqueAppointmentList;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.UniquePersonList;
import seedu.plannermd.testutil.appointment.AddAppointmentDescriptorBuilder;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;
import seedu.plannermd.testutil.doctor.DoctorBuilder;
import seedu.plannermd.testutil.patient.PatientBuilder;

public class AddAppointmentCommandTest {
    private AddAppointmentCommand.AddAppointmentDescriptor descriptor =
            new AddAppointmentDescriptorBuilder(new AppointmentBuilder().build()).build();

    @Test
    public void constructor_nullAll_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null,
                null, null));
    }

    @Test
    public void constructor_nullDoctorIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null,
                INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void constructor_nullPatientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON,
                null, descriptor));
    }

    @Test
    public void constructor_nullDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();

        CommandResult commandResult = new AddAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON,
                descriptor).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment,
                AppointmentDate.DISPLAYED_DATE_FORMATTER.format(validAppointment.getAppointmentDate().date)),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        ModelStubWithAppointment modelStub = new ModelStubWithAppointment(validAppointment);
        Patient patient = validAppointment.getPatient();
        Doctor doctor = validAppointment.getDoctor();
        Index doctorIndex = Index.fromZeroBased(1);
        Index patientIndex = Index.fromOneBased(1);
        for (Patient pt : modelStub.getFilteredPatientList()) {
            if (pt.equals(patient)) {
                patientIndex = Index.fromZeroBased(modelStub.getFilteredPatientList().indexOf(pt));
                break;
            }
        }
        for (Doctor dr : modelStub.getFilteredDoctorList()) {
            if (dr.equals(doctor)) {
                doctorIndex = Index.fromZeroBased(modelStub.getFilteredDoctorList().indexOf(dr));
                break;
            }
        }
        AddAppointmentCommand.AddAppointmentDescriptor descriptor =
                new AddAppointmentDescriptorBuilder(validAppointment).build();

        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(patientIndex, doctorIndex, descriptor);


        assertThrows(CommandException.class,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, () -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment thirtyMinAppt = new AppointmentBuilder(THIRTY_MIN_APPOINTMENT).build();
        Appointment twoHrAppt = new AppointmentBuilder(TWO_HOUR_APPOINTMENT).build();

        AddAppointmentCommand.AddAppointmentDescriptor thirtyMinDescriptor =
                new AddAppointmentDescriptorBuilder(thirtyMinAppt).build();

        AddAppointmentCommand.AddAppointmentDescriptor twoHrDescriptor =
                new AddAppointmentDescriptorBuilder(twoHrAppt).build();

        AddAppointmentCommand addThirtyMinApptCommand =
                new AddAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, thirtyMinDescriptor);
        AddAppointmentCommand addTwoHrApptCommand =
                new AddAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON, twoHrDescriptor);

        // same object -> returns true
        assertTrue(addThirtyMinApptCommand.equals(addThirtyMinApptCommand));

        // same values -> returns true
        AddAppointmentCommand addThirtyMinApptCommandCopy =
                new AddAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON,
                        new AddAppointmentDescriptorBuilder(thirtyMinDescriptor).build());

        assertTrue(addThirtyMinApptCommand.equals(addThirtyMinApptCommandCopy));

        // different types -> returns false
        assertFalse(addThirtyMinApptCommand.equals(1));

        // null -> returns false
        assertFalse(addThirtyMinApptCommand.equals(null));

        // different person -> returns false
        assertFalse(addThirtyMinApptCommand.equals(addTwoHrApptCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setState(State state) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public State getState() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void toggleState() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPlannerMdFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPlannerMdFilePath(Path plannerMdFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPlannerMd(ReadOnlyPlannerMd plannerMd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPlannerMd getPlannerMd() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDoctor(Doctor doctor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDoctor(Doctor target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDoctor(Doctor doctor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDoctor(Doctor target, Doctor editedDoctor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isClashAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isClashAppointmentForEdited(Appointment editedAppointment, Appointment oldAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<? super Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Doctor> getFilteredDoctorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDoctorList(Predicate<? super Doctor> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<? super Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public <T extends Person> void deleteAppointmentsWithPerson(T person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public <T extends Person> void editAppointmentsWithPerson(T person, T editedPerson) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithAppointment extends AddAppointmentCommandTest.ModelStub {
        private final Appointment appointment;

        ModelStubWithAppointment(Appointment appointment) {
            requireNonNull(appointment);
            this.appointment = appointment;
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            Patient patient = new PatientBuilder().build();
            UniquePersonList<Patient> patientList = new UniquePersonList<>();
            patientList.add(patient);
            return patientList.asUnmodifiableObservableList();
        }

        @Override
        public ObservableList<Doctor> getFilteredDoctorList() {
            Doctor doctor = new DoctorBuilder().build();
            UniquePersonList<Doctor> doctorList = new UniquePersonList<>();
            doctorList.add(doctor);
            return doctorList.asUnmodifiableObservableList();
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            UniqueAppointmentList appointmentList = new UniqueAppointmentList();
            return appointmentList.asUnmodifiableObservableList();
        }

        @Override
        public boolean isClashAppointment(Appointment appointment) {
            requireAllNonNull(appointment);
            for (Appointment existingAppointment : getFilteredAppointmentList()) {
                if (appointment.isClash(existingAppointment)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<? super Appointment> predicate) {
            requireNonNull(predicate);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends AddAppointmentCommandTest.ModelStub {
        private final ArrayList<Doctor> doctorsAdded = new ArrayList<>();
        private final ArrayList<Patient> patientsAdded = new ArrayList<>();
        private ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            Patient patient = new PatientBuilder().build();
            UniquePersonList<Patient> patientList = new UniquePersonList<>();
            patientList.add(patient);
            return patientList.asUnmodifiableObservableList();
        }

        @Override
        public ObservableList<Doctor> getFilteredDoctorList() {
            Doctor doctor = new DoctorBuilder().build();
            UniquePersonList<Doctor> doctorList = new UniquePersonList<>();
            doctorList.add(doctor);
            return doctorList.asUnmodifiableObservableList();
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            UniqueAppointmentList appointmentList = new UniqueAppointmentList();
            return appointmentList.asUnmodifiableObservableList();
        }

        @Override
        public boolean isClashAppointment(Appointment appointment) {
            requireAllNonNull(appointment);
            for (Appointment existingAppointment : getFilteredAppointmentList()) {
                if (appointment.isClash(existingAppointment)) {
                    return true;
                }
            }
            return false;
        }


        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(patient::isSamePerson);
        }

        @Override
        public boolean hasDoctor(Doctor doctor) {
            requireNonNull(doctor);
            return doctorsAdded.stream().anyMatch(doctor::isSamePerson);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<? super Appointment> predicate) {
            requireAllNonNull(predicate);
            appointmentsAdded = appointmentsAdded.stream().filter(predicate)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            patientsAdded.add(patient);
        }

        @Override
        public void addDoctor(Doctor doctor) {
            requireNonNull(doctor);
            doctorsAdded.add(doctor);
        }

        @Override
        public ReadOnlyPlannerMd getPlannerMd() {
            return new PlannerMd();
        }
    }
}
