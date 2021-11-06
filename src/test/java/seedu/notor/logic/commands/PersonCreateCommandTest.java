package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.notor.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.notor.commons.core.GuiSettings;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.executors.Executor;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonCreateExecutor;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.Model;
import seedu.notor.model.Notor;
import seedu.notor.model.ReadOnlyNotor;
import seedu.notor.model.ReadOnlyUserPrefs;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;
import seedu.notor.testutil.PersonBuilder;
import seedu.notor.ui.listpanel.PersonListPanel;

public class PersonCreateCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonCreateCommand(null, null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Executor.setup(modelStub);

        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new PersonCreateCommand(null, validPerson).execute(modelStub);

        assertEquals(String.format(PersonCreateExecutor.MESSAGE_SUCCESS, validPerson),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validPerson), modelStub.personsAdded);
    }

    // @formatter:off
    @Test
    public void execute_duplicatePerson_throwsCommandException() throws ParseException {
        Person validPerson = new PersonBuilder().build();
        PersonCreateCommand personCreateCommand = new PersonCreateCommand(null, validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);
        Executor.setup(modelStub);

        assertThrows(ExecuteException.class, PersonCreateExecutor.MESSAGE_DUPLICATE_PERSON, ()
            -> personCreateCommand.execute(modelStub));
    }

    @Test
    public void equals() throws ParseException {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        PersonCreateCommand addAliceCommand = new PersonCreateCommand(null, alice);
        PersonCreateCommand addBobCommand = new PersonCreateCommand(null, bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        PersonCreateCommand addAliceCommandCopy = new PersonCreateCommand(null, alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different person -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public void setup(PersonListPanel personListPanel) {
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
        public Path getNotorFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNotorFilePath(Path notorFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void createPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void archivePerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void archiveAllPersons() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unarchivePerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person findPerson(String name) {
            return null;
        }

        @Override
        public boolean hasSuperGroup(SuperGroup superGroup) {
            return false;
        }

        @Override
        public void addSuperGroup(SuperGroup superGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSuperGroup(String superGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSuperGroup(SuperGroup superGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group findGroup(String name) {
            return null;
        }

        @Override
        public void setNotor(ReadOnlyNotor newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyNotor getNotor() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearNotorNote() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override public boolean hasArchive(Person person) {
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
        public ObservableList<SuperGroup> getFilteredGroupList() {
            return null;
        }

        @Override
        public void listSuperGroup() {

        }

        @Override
        public void listSubGroup(Index i) {

        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void displayPersons() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void displayPersonArchive() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSubGroup(SubGroup subGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSubGroup(Index index, SubGroup subGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isPersonList() {
            return true;
        }

        @Override
        public boolean isSuperGroupList() {
            return false;
        }

        @Override
        public boolean isArchiveList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private static class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSame(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private static class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSame);
        }

        @Override
        public void createPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyNotor getNotor() {
            return new Notor();
        }
    }

}
