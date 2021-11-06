package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.testutil.Assert.assertThrows;
import static seedu.edrecord.testutil.TypicalModules.getTypicalModules;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.edrecord.commons.core.GuiSettings;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.ReadOnlyEdRecord;
import seedu.edrecord.model.ReadOnlyUserPrefs;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSet;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.PartOfModulePredicate;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.testutil.PersonBuilder;
import seedu.edrecord.testutil.TypicalModules;
import seedu.edrecord.ui.PersonListPanel;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
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
        public Path getEdRecordFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEdRecordFilePath(Path edRecordFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEdRecord(ReadOnlyEdRecord newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEdRecord getEdRecord() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getModuleSystemFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleSystemFilePath(Path moduleSystemFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleSystem(ReadOnlyModuleSystem moduleSystem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModuleSystem getModuleSystem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModulesAndGroups(ModuleSet mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Module getModule(Module mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSearchFilter(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableValue<PersonListPanel.View> getSelectedView() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedView(PersonListPanel.View newView) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleFilter(PartOfModulePredicate predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableValue<Module> getSelectedModule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSelectedModule() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssignmentInCurrentModule(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isTotalWeightageExceeded(Assignment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Assignment> getAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Assignment> searchAssignment(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasHigherGradeInCurrentModule(Assignment current, Assignment editedAssignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssignment(Assignment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAssignment(Assignment target, Assignment editedAssignment) {
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
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public Module getModule(Module mod) {
            for (Module m : TypicalModules.getTypicalModules()) {
                if (m.isSameModule(mod)) {
                    return m;
                }
            }
            // TODO: Refine this, this should not be returning mod.
            return mod;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return getTypicalModules().stream().anyMatch(module::isSameModule);
        }

        @Override
        public ReadOnlyEdRecord getEdRecord() {
            return new EdRecord();
        }

        @Override
        public boolean hasModulesAndGroups(ModuleSet mod) {
            return true;
        }
    }

}
