package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.commons.core.Messages.MESSAGE_NO_MODULE_SELECTED;
import static seedu.edrecord.testutil.Assert.assertThrows;

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
import seedu.edrecord.testutil.AssignmentBuilder;
import seedu.edrecord.ui.PersonListPanel;

public class MakeAssignmentCommandTest {
    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MakeAssignmentCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Assignment validAssignment = new AssignmentBuilder().build();
        assertThrows(NullPointerException.class, () -> new MakeAssignmentCommand(validAssignment).execute(null));
    }


    @Test
    public void execute_noModuleSelected_throwsCommandException() {
        Assignment validAssignment = new AssignmentBuilder().build();
        ModelStubWithoutSelectedModule modelStub = new ModelStubWithoutSelectedModule();
        MakeAssignmentCommand addAssignmentCommand = new MakeAssignmentCommand(validAssignment);

        assertThrows(CommandException.class,
                MESSAGE_NO_MODULE_SELECTED, () -> addAssignmentCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Assignment validAssignment = new AssignmentBuilder().build();
        ModelStub modelStub = new ModelStubWithAssignment(validAssignment);
        MakeAssignmentCommand addAssignmentCommand = new MakeAssignmentCommand(validAssignment);

        assertThrows(CommandException.class,
                MakeAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT, () -> addAssignmentCommand.execute(modelStub));
    }

    @Test
    public void execute_assignmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAssignmentAdded modelStub = new ModelStubAcceptingAssignmentAdded();
        Assignment validAssignment = new AssignmentBuilder().build();

        CommandResult commandResult = new MakeAssignmentCommand(validAssignment).execute(modelStub);

        assertEquals(
                String.format(MakeAssignmentCommand.MESSAGE_SUCCESS, validAssignment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAssignment), modelStub.assignmentsAdded);
    }

    @Test
    public void equals() {
        Assignment mission = new AssignmentBuilder().withName("Mission").build();
        Assignment training = new AssignmentBuilder().withName("Training").build();
        MakeAssignmentCommand addMission = new MakeAssignmentCommand(mission);
        MakeAssignmentCommand addTraining = new MakeAssignmentCommand(training);

        // same object -> returns true
        assertTrue(addMission.equals(addMission));

        // same values -> returns true
        MakeAssignmentCommand addMissionCopy = new MakeAssignmentCommand(mission);
        assertTrue(addMission.equals(addMissionCopy));

        // different types -> returns false
        assertFalse(addMission.equals(1));

        // null -> returns false
        assertFalse(addMission.equals(null));

        // different assignment -> returns false
        assertFalse(addMission.equals(addTraining));
    }

    /**
     * A default model stub that has all of the methods failing.
     */
    private static class ModelStub implements Model {
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
        public boolean hasModulesAndGroups(
            ModuleSet mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Module getModule(Module mod) {
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
     * A Model stub that does not have a selected module.
     */
    private static class ModelStubWithoutSelectedModule extends ModelStub {
        @Override
        public boolean hasSelectedModule() {
            return false;
        }
    }

    /**
     * A Model stub that contains a single assignment.
     */
    private static class ModelStubWithAssignment extends ModelStub {
        private final Assignment assignment;

        ModelStubWithAssignment(Assignment assignment) {
            requireNonNull(assignment);
            this.assignment = assignment;
        }

        @Override
        public boolean hasSelectedModule() {
            return true;
        }

        @Override
        public boolean hasAssignmentInCurrentModule(Assignment assignment) {
            requireNonNull(assignment);
            return this.assignment.isSameAssignment(assignment);
        }
    }

    /**
     * A model stub that always accepts the assignment being added.
     */
    private static class ModelStubAcceptingAssignmentAdded extends ModelStub {
        final ArrayList<Assignment> assignmentsAdded = new ArrayList<>();

        @Override
        public boolean hasSelectedModule() {
            return true;
        }

        @Override
        public boolean hasAssignmentInCurrentModule(Assignment assignment) {
            requireNonNull(assignment);
            return assignmentsAdded.stream().anyMatch(assignment::isSameAssignment);
        }

        @Override
        public boolean isTotalWeightageExceeded(Assignment toAdd) {
            double totalWeightage = assignmentsAdded.stream()
                    .mapToDouble(assignment -> assignment.getWeightage().weightage)
                    .sum();
            return totalWeightage > 100;
        }

        @Override
        public void addAssignment(Assignment assignment) {
            requireNonNull(assignment);
            assignmentsAdded.add(assignment);
        }

        // @Override
        // public ReadOnlyEdRecord getEdRecord() {
        //     return new EdRecord();
        // }
    }
}
