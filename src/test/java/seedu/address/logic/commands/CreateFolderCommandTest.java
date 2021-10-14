package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;
import seedu.address.model.person.Person;

class CreateFolderCommandTest {

    @Test
    public void constructor_nullFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateFolderCommand(null));
    }

    @Test
    public void execute_folderAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFolderAdded modelStub = new ModelStubAcceptingFolderAdded();
        Folder validFolder = new Folder(new FolderName("Folder 1"));

        CommandResult commandResult = new CreateFolderCommand(validFolder).execute(modelStub);

        assertEquals(String.format(CreateFolderCommand.MESSAGE_SUCCESS, validFolder),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFolder), modelStub.foldersAdded);
    }

    @Test
    public void execute_duplicateFolder_throwsCommandException() {
        Folder validFolder = new Folder(new FolderName("Folder 1"));
        CreateFolderCommand createFolderCommand = new CreateFolderCommand(validFolder);
        ModelStub modelStub = new ModelStubWithFolder(validFolder);

        assertThrows(CommandException.class, () -> createFolderCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Folder nus = new Folder(new FolderName("NUS"));
        Folder cs = new Folder(new FolderName("CS"));
        CreateFolderCommand addNusCommand = new CreateFolderCommand(nus);
        CreateFolderCommand addCsCommand = new CreateFolderCommand(cs);

        // same object -> returns true
        assertTrue(addNusCommand.equals(addNusCommand));

        // same values -> returns true
        CreateFolderCommand addNusCommandDuplicate = new CreateFolderCommand(nus);
        assertTrue(addNusCommand.equals(addNusCommandDuplicate));

        // different types -> returns false
        assertFalse(addNusCommand.equals(1));

        // null -> returns false
        assertFalse(addNusCommand.equals(null));

        // different folder -> returns false
        assertFalse(addNusCommand.equals(addCsCommand));
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
        public ObservableList<Folder> getFilteredFolderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFolderList(Predicate<Folder> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFolder(Folder folder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContactToFolder(Person target, FolderName name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFolderName(FolderName name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean folderContainsPerson(Person target, FolderName name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFolder(Folder folder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFolder(Folder folder) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single folder.
     */
    private class ModelStubWithFolder extends CreateFolderCommandTest.ModelStub {
        private final Folder folder;

        ModelStubWithFolder(Folder folder) {
            requireNonNull(folder);
            this.folder = folder;
        }

        @Override
        public boolean hasFolder(Folder folder) {
            requireNonNull(folder);
            return this.folder.isSameFolder(folder);
        }
    }

    /**
     * A Model stub that always accept the folder being added.
     */
    private class ModelStubAcceptingFolderAdded extends CreateFolderCommandTest.ModelStub {
        final ArrayList<Folder> foldersAdded = new ArrayList<>();

        @Override
        public boolean hasFolder(Folder folder) {
            requireNonNull(folder);
            return foldersAdded.stream().anyMatch(folder::isSameFolder);
        }

        @Override
        public void addFolder(Folder folder) {
            requireNonNull(folder);
            foldersAdded.add(folder);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
