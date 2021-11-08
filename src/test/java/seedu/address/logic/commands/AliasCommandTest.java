package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ADD_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.CommandAliases;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AliasCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AliasCommand(null, null, null));
    }

    @Test
    public void execute_validAliasAddCommand_addSuccessful() throws Exception {
        ModelStub modelStub = new ModelStub();
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        Command expectedCommand = new AddCommand(expectedPerson);
        Map<String, Command> expectedMap = new HashMap<>();
        expectedMap.put(VALID_ALIAS, expectedCommand);

        CommandResult commandResult = new AliasCommand(VALID_ALIAS, expectedCommand, ADD_COMMAND).execute(modelStub);

        assertEquals(String.format(AliasCommand.MESSAGE_SUCCESS, VALID_ALIAS, ADD_COMMAND),
                commandResult.getFeedbackToUser());
        assertEquals(expectedMap, CommandAliases.getMap());
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        AliasCommand aliasAddAlice = new AliasCommand(VALID_ALIAS, new AddCommand(alice), ADD_COMMAND);
        AliasCommand differentAliasAddAlice = new AliasCommand("diff", new AddCommand(alice), ADD_COMMAND);

        // same object -> returns true
        assertTrue(aliasAddAlice.equals(aliasAddAlice));

        // same values -> returns true
        AliasCommand addAliceCommandCopy = new AliasCommand(VALID_ALIAS, new AddCommand(alice), ADD_COMMAND);
        assertTrue(aliasAddAlice.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(aliasAddAlice.equals(1));

        // null -> returns false
        assertFalse(aliasAddAlice.equals(null));

        // different alias -> returns false
        assertFalse(aliasAddAlice.equals(differentAliasAddAlice));
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
        public int importAddressBook(ReadOnlyAddressBook addressBook) {
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
        public ObservableList<Person> getSortedPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
