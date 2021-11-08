package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPersonCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddPersonCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddPersonCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(List.of(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddPersonCommand addPersonCommand = new AddPersonCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class,
                AddPersonCommand.MESSAGE_DUPLICATE_PERSON, () -> addPersonCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateEmail_throwsCommandException() {
        Person validPerson = new PersonBuilder()
                .withName("Bernice")
                .withEmail("alice@example.com")
                .build();

        AddPersonCommand addPersonCommand = new AddPersonCommand(validPerson);

        // Model contains someone with the email alice@example.com
        assertThrows(CommandException.class,
                AddPersonCommand.MESSAGE_DUPLICATE_EMAIL, () -> addPersonCommand.execute(model));
    }

    @Test
    public void execute_duplicateDifferentCaseEmail_throwsCommandException() {
        Person validPerson = new PersonBuilder()
                .withName("Bernice")
                .withEmail("ALiCe@example.com")
                .build();

        AddPersonCommand addPersonCommand = new AddPersonCommand(validPerson);

        // Model contains someone with the email alice@example.com
        assertThrows(CommandException.class,
                AddPersonCommand.MESSAGE_DUPLICATE_EMAIL, () -> addPersonCommand.execute(model));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddPersonCommand addAliceCommand = new AddPersonCommand(alice);
        AddPersonCommand addBobCommand = new AddPersonCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPersonCommand addAliceCommandCopy = new AddPersonCommand(alice);
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

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
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
        public boolean hasExistingEmail(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(n -> checkEmailPredicate(n, person));
        }

        private boolean checkEmailPredicate(Person person, Person toCheck) {
            return person.getEmail().equals(toCheck.getEmail());
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
