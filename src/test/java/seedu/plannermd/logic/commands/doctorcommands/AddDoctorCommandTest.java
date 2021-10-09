package seedu.plannermd.logic.commands.doctorcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.plannermd.commons.core.GuiSettings;
import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.addcommand.AddDoctorCommand;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.ReadOnlyUserPrefs;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.testutil.doctor.DoctorBuilder;

public class AddDoctorCommandTest {
    @Test
    public void constructor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDoctorCommand(null));
    }
    @Test
    public void execute_doctorAcceptedByModel_addSuccessful() throws Exception {
        AddDoctorCommandTest.ModelStubAcceptingDoctorAdded modelStub =
                new AddDoctorCommandTest.ModelStubAcceptingDoctorAdded();
        Doctor validDoctor = new DoctorBuilder().build();

        CommandResult commandResult = new AddDoctorCommand(validDoctor).execute(modelStub);

        assertEquals(String.format(AddDoctorCommand.MESSAGE_SUCCESS, validDoctor), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDoctor), modelStub.doctorsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Doctor validDoctor = new DoctorBuilder().build();
        AddDoctorCommand addDoctorCommand = new AddDoctorCommand(validDoctor);
        AddDoctorCommandTest.ModelStub modelStub = new AddDoctorCommandTest.ModelStubWithDoctor(validDoctor);

        assertThrows(CommandException.class,
                AddDoctorCommand.MESSAGE_DUPLICATE_DOCTOR, () -> addDoctorCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Doctor alice = new DoctorBuilder().withName("Alice").build();
        Doctor bob = new DoctorBuilder().withName("Bob").build();
        AddDoctorCommand addAliceCommand = new AddDoctorCommand(alice);
        AddDoctorCommand addBobCommand = new AddDoctorCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddDoctorCommand addAliceCommandCopy = new AddDoctorCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<? super Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Doctor> getFilteredDoctorList() {
            return null;
        }

        @Override
        public void updateFilteredDoctorList(Predicate<? super Doctor> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithDoctor extends AddDoctorCommandTest.ModelStub {
        private final Person person;

        ModelStubWithDoctor(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDoctor(Doctor doctor) {
            requireNonNull(doctor);
            return this.person.isSamePerson(doctor);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingDoctorAdded extends AddDoctorCommandTest.ModelStub {
        final ArrayList<Doctor> doctorsAdded = new ArrayList<>();

        @Override
        public boolean hasDoctor(Doctor doctor) {
            requireNonNull(doctor);
            return doctorsAdded.stream().anyMatch(doctor::isSamePerson);
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
