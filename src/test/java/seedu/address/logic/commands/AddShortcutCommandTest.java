package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCountdown;
import seedu.address.model.ReadOnlyShortcut;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Shortcut;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class AddShortcutCommandTest {

    @Test
    public void constructor_nullKey_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddShortcutCommand(null, "list"));
    }

    @Test
    public void constructor_nullCommandString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddShortcutCommand("key", null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() {
        ModelStubWithShortcut modelStub = new ModelStubWithShortcut(new Shortcut());

        CommandResult commandResult = new AddShortcutCommand("key", "list").execute(modelStub);

        assertEquals("Added key: list", commandResult.getFeedbackToUser());
        assertEquals("list", modelStub.getShortcutFromKey("key"));
    }

    @Test
    public void equals() {
        AddShortcutCommand addkCommand = new AddShortcutCommand("k", "list");
        AddShortcutCommand addjCommand = new AddShortcutCommand("j", "find a");
        AddShortcutCommand addiCommand = new AddShortcutCommand("k", "find a");

        // same object -> returns true
        assertTrue(addkCommand.equals(addkCommand));

        // same values -> returns true
        AddShortcutCommand addkCommandCopy = new AddShortcutCommand("k", "list");
        assertTrue(addkCommand.equals(addkCommandCopy));

        // different types -> returns false
        assertFalse(addkCommand.equals(1));

        // null -> returns false
        assertFalse(addkCommand.equals(null));

        // different person -> returns false
        assertFalse(addkCommand.equals(addjCommand));
        assertFalse(addkCommand.equals(addiCommand));
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
        public int size() {
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
        public Path getCountdownFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getShortcutFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCountdownFilePath(Path countdownFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShortcutFilePath(Path shortcutFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addShortcut(String keyword, String command) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCountdown(ReadOnlyCountdown countdown) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setShortcut(ReadOnlyShortcut shortcut) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCountdown getCountdown() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyShortcut getShortcut() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String listShortcut() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String removeShortcut(String keyword) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getShortcutFromKey(String keyword) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonName(String name) {
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
        public void setDate(LocalDate newDate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public LocalDate getWeddingDate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Hashtable<Tag, Integer> getUniqueTagTable() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getUniqueTagList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithShortcut extends ModelStub {
        private final Shortcut shortcut;

        ModelStubWithShortcut(Shortcut shortcut) {
            requireNonNull(shortcut);
            this.shortcut = shortcut;
        }

        @Override
        public String getShortcutFromKey(String key) {
            return shortcut.getCommandFromKey(key);
        }

        @Override
        public void addShortcut(String key, String commandString) {
            shortcut.addShortcut(key, commandString);
        }
    }
}
