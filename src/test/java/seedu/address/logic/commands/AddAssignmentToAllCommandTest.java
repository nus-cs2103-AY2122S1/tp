package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.model.person.Module;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class AddAssignmentToAllCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentToAllCommand(null,
                new AssignmentBuilder().build()));
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentToAllCommand(
                new PersonBuilder().build().getModule(), null));
    }

    @Test
    public void execute_assignmentAcceptedByModel_addSuccessful() throws Exception {
        AddAssignmentToAllCommandTest.ModelStubWithTwoPerson modelStub =
                new AddAssignmentToAllCommandTest.ModelStubWithTwoPerson();
        Module validModule = new PersonBuilder().build().getModule();
        Assignment validAssignment = new AssignmentBuilder().build();
        CommandResult commandResult =
                new AddAssignmentToAllCommand(validModule, validAssignment).execute(modelStub);

        assertEquals(String.format(AddAssignmentToAllCommand.MESSAGE_SUCCESS, validModule, validAssignment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAssignment), modelStub.assignmentsAdded);
    }

    @Test
    public void execute_assignmentNotDuplicated_addSuccessful() throws Exception {
        Assignment validAssignment = new AssignmentBuilder().build();
        String[] assignmentDetails = new String[] {validAssignment.getDescription().toString(),
                validAssignment.getDueDate().toString(),
                validAssignment.getStatus().value};
        Person validPerson1 = new PersonBuilder().withAssignmentList(assignmentDetails).build();
        Person validPerson2 = new PersonBuilder().withName("Alice").withAssignmentList(assignmentDetails).build();
        Module validModule = new PersonBuilder().build().getModule();

        AddAssignmentToAllCommandTest.ModelStubWithAssignment modelStub =
                new AddAssignmentToAllCommandTest.ModelStubWithAssignment(validPerson1, validPerson2, validAssignment);

        CommandResult commandResult =
                new AddAssignmentToAllCommand(validModule, validAssignment).execute(modelStub);

        assertEquals(String.format(AddAssignmentToAllCommand.MESSAGE_SUCCESS, validModule, validAssignment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAssignment), modelStub.assignments1);
        assertEquals(Arrays.asList(validAssignment), modelStub.assignments2);
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person amy = new PersonBuilder().withModule("CS2101").build();

        Assignment assignment1 = new AssignmentBuilder().withDescription("Assignment 1").build();
        Assignment assignment2 = new AssignmentBuilder().withDescription("Assignment 2").build();

        AddAssignmentToAllCommand addAssignment1Command = new AddAssignmentToAllCommand(alice.getModule(), assignment1);
        AddAssignmentToAllCommand addAssignment2Command = new AddAssignmentToAllCommand(alice.getModule(), assignment2);
        AddAssignmentToAllCommand addAssignment1DifferentModuleCommand =
                new AddAssignmentToAllCommand(amy.getModule(), assignment1);


        // same object -> returns true
        assertTrue(addAssignment1Command.equals(addAssignment1Command));

        // same values -> returns true
        AddAssignmentToAllCommand addAssignment1CommandCopy =
                new AddAssignmentToAllCommand(alice.getModule(), assignment1);
        assertTrue(addAssignment1Command.equals(addAssignment1CommandCopy));

        // different types -> returns false
        assertFalse(addAssignment1Command.equals(1));

        // null -> returns false
        assertFalse(addAssignment1Command.equals(null));

        // different assignment -> returns false
        assertFalse(addAssignment1Command.equals(addAssignment2Command));

        //different modules -> returns false
        assertFalse(addAssignment1Command.equals(addAssignment1DifferentModuleCommand));
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
        public void clearAssignmentList() {
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
     * A Model stub that contains two persons with same module and no assignments
     */
    private class ModelStubWithTwoPerson extends AddAssignmentToAllCommandTest.ModelStub {
        final Person person1 = new PersonBuilder().build();
        final Person person2 = new PersonBuilder().withName("Dave Chia").withEmail("dave@gmail.com").build();
        final ArrayList<Assignment> assignmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAssignment(Person person, Assignment assignment) {
            requireNonNull(assignment);
            person.getAssignments().contains(assignment);
            return assignmentsAdded.stream().anyMatch(assignment::isSameAssignment);
        }

        @Override
        public void addAllAssignments(List<Person> personList, Assignment assignment) {
            requireNonNull(assignment);
            personList.get(0).getAssignments().add(assignment);
            personList.get(1).getAssignments().add(assignment);
            assignmentsAdded.add(assignment);
        }


        @Override
        public ReadOnlyAddressBook getAddressBook() {
            AddressBook addressBook = new AddressBook();
            addressBook.addPerson(person1);
            addressBook.addPerson(person2);
            return addressBook;
        }

    }


    /**
     * A Model stub that contains 2 persons, with one person having an assignment
     */
    private class ModelStubWithAssignment extends AddAssignmentToAllCommandTest.ModelStub {

        private final Person person1;
        private final Person person2;
        private final ArrayList<Assignment> assignments1 = new ArrayList<>();
        private final ArrayList<Assignment> assignments2 = new ArrayList<>();


        ModelStubWithAssignment(Person person1, Person person2, Assignment assignment) {
            requireNonNull(assignment);
            this.person1 = person1;
            this.person2 = person2;
            assignments1.add(assignment);
        }

        @Override
        public boolean hasAssignment(Person person, Assignment assignment) {
            requireNonNull(assignment);
            if (person.equals(person1)) {
                return assignments1.stream().anyMatch(assignment::isSameAssignment);
            } else {
                return assignments2.stream().anyMatch(assignment::isSameAssignment);
            }
        }

        @Override
        public void addAllAssignments(List<Person> personList, Assignment assignment) {
            requireNonNull(assignment);
            for (Person person : personList) {
                boolean person1NoAssignment = person.equals(person1) && !hasAssignment(person1, assignment);
                boolean person2NoAssignment = person.equals(person2) && !hasAssignment(person2, assignment);
                if (person1NoAssignment) {
                    assignments1.add(assignment);
                }
                if (person2NoAssignment) {
                    assignments2.add(assignment);
                }
            }
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            AddressBook addressBook = new AddressBook();
            addressBook.addPerson(person1);
            addressBook.addPerson(person2);
            return addressBook;
        }
    }
}
