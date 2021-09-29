package seedu.plannermd.logic.commands;

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
import seedu.plannermd.logic.commands.addcommand.AddPatientCommand;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.ReadOnlyUserPrefs;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.testutil.PersonBuilder;

public class AddPatientCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPatientCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddPatientCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddPatientCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddPatientCommand addPatientCommand = new AddPatientCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddPatientCommand.MESSAGE_DUPLICATE_PATIENT, () -> addPatientCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddPatientCommand addAliceCommand = new AddPatientCommand(alice);
        AddPatientCommand addBobCommand = new AddPatientCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPatientCommand addAliceCommandCopy = new AddPatientCommand(alice);
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
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return this.person.isSamePerson(patient);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return personsAdded.stream().anyMatch(patient::isSamePerson);
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            personsAdded.add(patient);
        }

        @Override
        public ReadOnlyPlannerMd getPlannerMd() {
            return new PlannerMd();
        }
    }

}
