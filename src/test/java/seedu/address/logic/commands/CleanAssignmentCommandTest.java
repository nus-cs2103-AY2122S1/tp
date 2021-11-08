package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public class CleanAssignmentCommandTest {

    @Test
    public void execute_allAssignment_cleanSuccessful() throws Exception {
        Person validPerson1 = new PersonBuilder().build();
        Person validPerson2 = new PersonBuilder().withName("Alice").build();
        Assignment validAssignment = new AssignmentBuilder().build();

        CleanAssignmentCommandTest.ModelStubAllCompletedAssignment modelStub =
                new CleanAssignmentCommandTest.ModelStubAllCompletedAssignment(
                        validPerson1, validPerson2, validAssignment);

        CommandResult commandResult = new CleanAssignmentCommand().execute(modelStub);

        assertEquals(CleanAssignmentCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(List.of(validAssignment), modelStub.firstAssignmentList);
        assertEquals(List.of(), modelStub.secondAssignmentList);
    }

    @Test
    public void execute_partialAssignment_cleanSuccessful() throws Exception {
        Person validPerson1 = new PersonBuilder().build();
        Person validPerson2 = new PersonBuilder().withName("Alice").build();
        Assignment completedAssignment = new AssignmentBuilder().withCompletedStatus().build();

        CleanAssignmentCommandTest.ModelStubPartialCompletedAssignment modelStub =
                new CleanAssignmentCommandTest.ModelStubPartialCompletedAssignment(
                        validPerson1, validPerson2, completedAssignment);

        CommandResult commandResult =
                new CleanAssignmentCommand().execute(modelStub);

        assertEquals(CleanAssignmentCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(List.of(), modelStub.firstAssignmentList);
        assertEquals(List.of(), modelStub.secondAssignmentList);
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExistingEmail(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasActivePerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getActivePerson() {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssignment(Person person, Assignment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssignment(Person person, Assignment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAllAssignments(List<Person> personList, Assignment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssignment(Person person, Assignment toDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markAssignment(Person person, Assignment toMark) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cleanAssignments() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateAssignmentList(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAssignmentCompleted(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Assignment getAssignmentInList(Index targetAssignmentIndex) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assignment> getAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Assignment> getPersonAssignmentList(Person person) {
            throw new AssertionError("This method should not be called.");
        }
    }


    /**
     * A Model stub that contains two persons with one having a completed assignment
     */
    private class ModelStubAllCompletedAssignment extends CleanAssignmentCommandTest.ModelStub {
        final Person firstPerson;
        final Person secondPerson;
        private final ArrayList<Assignment> firstAssignmentList = new ArrayList<>();
        private final ArrayList<Assignment> secondAssignmentList = new ArrayList<>();

        ModelStubAllCompletedAssignment(Person firstPerson, Person secondPerson, Assignment assignment) {
            requireNonNull(assignment);
            this.firstPerson = firstPerson;
            this.secondPerson = secondPerson;
            firstAssignmentList.add(assignment);
            Assignment completedAssignment = new AssignmentBuilder(assignment).withCompletedStatus().build();
            secondAssignmentList.add(completedAssignment);
        }

        @Override
        public void cleanAssignments() {
            firstAssignmentList.removeIf(Assignment::isCompleted);
            secondAssignmentList.removeIf(Assignment::isCompleted);
        }

    }


    /**
     * A Model stub that contains 2 persons, with one person having an assignment
     */
    private class ModelStubPartialCompletedAssignment extends CleanAssignmentCommandTest.ModelStub {
        final Person firstPerson;
        final Person secondPerson;
        private final ArrayList<Assignment> firstAssignmentList = new ArrayList<>();
        private final ArrayList<Assignment> secondAssignmentList = new ArrayList<>();

        ModelStubPartialCompletedAssignment(Person firstPerson, Person secondPerson, Assignment assignment) {
            requireNonNull(assignment);
            this.firstPerson = firstPerson;
            this.secondPerson = secondPerson;
            firstAssignmentList.add(assignment);
            secondAssignmentList.add(assignment);
        }

        @Override
        public void cleanAssignments() {
            firstAssignmentList.removeIf(Assignment::isCompleted);
            secondAssignmentList.removeIf(Assignment::isCompleted);
        }
    }
}
