package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.GithubCommand.MESSAGE_SUCCESS;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.ui.PersonListPanel;

public class GithubCommandTest {

    @Test
    public void execute_github_success() {
        Model model = new ModelStub(1);
        Model expectedModel = new ModelStub(1);
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false, false, true);
        assertCommandSuccess(new GithubCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_github_failure() {
        Model model = new ModelStub(-1);
        CommandResult expectedCommandResult = new CommandResult(
                GithubCommand.MESSAGE_NO_USER_SELECTED, false, false, false, true);
        assertCommandFailure(new GithubCommand(), model, GithubCommand.MESSAGE_NO_USER_SELECTED);
    }

    /**
     * A stub Model which simulates getting index of selected person.
     */
    private static class ModelStub implements Model {

        private final int selectedIndex;

        ModelStub(int selectedIndex) {
            this.selectedIndex = selectedIndex;
        }

        @Override
        public Person getUserProfile() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isProfilePresent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserProfile(Person userProfile) {
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
        public Path getAddressBookFilePath() {
            return null;
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public boolean hasPerson(Person person) {
            return false;
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void favoritePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unfavoritePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return new AddressBook().getPersonList();
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedIndex(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getSelectedIndex() {
            return selectedIndex;
        }

        @Override
        public void setPersonListControl(PersonListPanel personListPanel) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PersonListPanel getPersonListControl() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTabIndex(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ModelStub)) {
                return false;
            }
            return getSelectedIndex() == ((ModelStub) other).getSelectedIndex();
        }

        @Override
        public int hashCode() {
            return Objects.hash(selectedIndex);
        }
    }
}
