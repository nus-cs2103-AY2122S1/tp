package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

public class AddAssignmentCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(null,
                new AssignmentBuilder().build()));
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(INDEX_FIRST_PERSON,
                null));
    }

    @Test
    public void execute_assignmentAcceptedByModel_addSuccessful() throws Exception {
        AddAssignmentCommandTest.ModelStubAcceptingAssignmentAdded modelStub =
                new AddAssignmentCommandTest.ModelStubAcceptingAssignmentAdded();
        Assignment validAssignment = new AssignmentBuilder().build();
        CommandResult commandResult =
                new AddAssignmentCommand(INDEX_FIRST_PERSON, validAssignment).execute(modelStub);

        assertEquals(String.format(AddAssignmentCommand.MESSAGE_SUCCESS, validAssignment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAssignment), modelStub.assignmentsAdded);
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Assignment validAssignment = new AssignmentBuilder().build();

        AddAssignmentCommand addAssignmentCommand = new AddAssignmentCommand(INDEX_FIRST_PERSON, validAssignment);
        AddAssignmentCommandTest.ModelStub modelStub =
                new AddAssignmentCommandTest.ModelStubWithAssignment(validPerson, validAssignment);

        assertThrows(CommandException.class,
                AddAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT, () -> addAssignmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Assignment cs2100 = new AssignmentBuilder().withDescription("CS2100").build();
        Assignment cs2103 = new AssignmentBuilder().withDescription("CS2103").build();

        AddAssignmentCommand addCs2100Command = new AddAssignmentCommand(INDEX_FIRST_PERSON, cs2100);
        AddAssignmentCommand addCs2103Command = new AddAssignmentCommand(INDEX_FIRST_PERSON, cs2103);

        // same object -> returns true
        assertTrue(addCs2100Command.equals(addCs2100Command));

        // same values -> returns true
        AddAssignmentCommand addCs2100CommandCopy = new AddAssignmentCommand(INDEX_FIRST_PERSON, cs2100);
        assertTrue(addCs2100Command.equals(addCs2100CommandCopy));

        // different types -> returns false
        assertFalse(addCs2100Command.equals(1));

        // null -> returns false
        assertFalse(addCs2100Command.equals(null));

        // different assignment -> returns false
        assertFalse(addCs2100Command.equals(addCs2103Command));
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
        public boolean hasActivePerson() {
            throw new AssertionError("This method should not be called.");
        } // change here

        @Override
        public Person getActivePerson() {
            throw new AssertionError("This method should not be called.");
        } //change here

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
     * A Model stub that contains a person with single assignment.
     */
    private class ModelStubWithAssignment extends AddAssignmentCommandTest.ModelStub {
        private final Assignment assignment;
        private final Person person;

        ModelStubWithAssignment(Person person, Assignment assignment) {
            requireNonNull(assignment);
            this.assignment = assignment;
            this.person = person;
            addAssignment(this.person, this.assignment);
        }

        @Override
        public boolean hasAssignment(Person person, Assignment assignment) {
            requireNonNull(assignment);
            return person.getAssignments().contains(assignment);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            UniquePersonList persons = new UniquePersonList();
            persons.add(person);
            return new FilteredList<>(persons.asUnmodifiableObservableList());
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public void addAssignment(Person person, Assignment assignment) {
            requireNonNull(assignment);
            person.getAssignments().add(assignment);
        }
    }

    /**
     * A Model stub that always accept the assignment being added.
     */
    private class ModelStubAcceptingAssignmentAdded extends AddAssignmentCommandTest.ModelStub {
        final Person person = new PersonBuilder().build();
        final ArrayList<Assignment> assignmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAssignment(Person person, Assignment assignment) {
            requireNonNull(assignment);
            person.getAssignments().contains(assignment);
            return assignmentsAdded.stream().anyMatch(assignment::isSameAssignment);
        }

        @Override
        public void addAssignment(Person person, Assignment assignment) {
            requireNonNull(assignment);
            person.getAssignments().add(assignment);
            assignmentsAdded.add(assignment);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            UniquePersonList persons = new UniquePersonList();
            persons.add(person);
            return new FilteredList<>(persons.asUnmodifiableObservableList());
        }
    }
}
