package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;
import seedu.address.model.person.Person;

public class DeleteFolderCommandTest {

    @Test
    public void constructor_nullFolder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteFolderCommand(null));
    }

    @Test
    public void execute_deleteFolderFromModel_success() throws Exception {
        Folder validFolder = new Folder(new FolderName("Folder 1"));
        ModelStubWithFolder modelWithFolderStub = new ModelStubWithFolder();

        modelWithFolderStub.addNewFolder(validFolder);

        CommandResult commandResult = new DeleteFolderCommand(validFolder).execute(modelWithFolderStub);

        assertEquals(String.format(DeleteFolderCommand.MESSAGE_SUCCESS, validFolder),
                commandResult.getFeedbackToUser());
        assertEquals(new ArrayList<>(), modelWithFolderStub.existingFolders);
    }

    @Test
    public void execute_deleteNonExistingFolder_throwsCommandException() {
        Folder firstFolder = new Folder(new FolderName("Folder 1"));
        Folder otherFolder = new Folder(new FolderName("Folder 2"));
        ModelStubWithFolder modelWithFolderStub = new ModelStubWithFolder();

        modelWithFolderStub.addNewFolder(firstFolder);
        DeleteFolderCommand deleteFolderCommand = new DeleteFolderCommand(otherFolder);

        assertThrows(CommandException.class, () -> deleteFolderCommand.execute(modelWithFolderStub));
    }

    @Test
    public void execute_deleteFromEmptyListOfFolders_throwsCommandException() {
        Folder validFolder = new Folder(new FolderName("Folder 1"));
        ModelStubWithFolder modelWithFolderStub = new ModelStubWithFolder();

        DeleteFolderCommand deleteFolderCommand = new DeleteFolderCommand(validFolder);

        assertThrows(CommandException.class, () -> deleteFolderCommand.execute(modelWithFolderStub));
    }

    @Test
    public void equals() {
        Folder myFolder = new Folder(new FolderName("myFolder"));
        Folder otherFolder = new Folder(new FolderName("otherFolder"));
        DeleteFolderCommand myFolderCommand = new DeleteFolderCommand(myFolder);
        DeleteFolderCommand otherFolderCommand = new DeleteFolderCommand(otherFolder);

        // same Object -> returns true
        assertTrue(myFolderCommand.equals(myFolderCommand));
        assertTrue(otherFolderCommand.equals(otherFolderCommand));

        // same values -> returns true
        DeleteFolderCommand myFolderCommandDuplicate = new DeleteFolderCommand(myFolder);
        assertTrue(myFolderCommand.equals(myFolderCommandDuplicate));
        DeleteFolderCommand otherFolderCommandDuplicate = new DeleteFolderCommand(otherFolder);
        assertTrue(otherFolderCommand.equals(otherFolderCommandDuplicate));

        // different types -> returns false
        assertFalse(myFolderCommand.equals(1));
        assertFalse(otherFolderCommand.equals(1));

        // null -> returns false
        assertFalse(myFolderCommand.equals(null));
        assertFalse(otherFolderCommand.equals(null));

        // different folder -> returns false
        assertFalse(myFolderCommand.equals(otherFolderCommand));
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
        public boolean hasFolder(Folder folder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFolder(Folder folder) {
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
    }

    /**
     * A Model stub that contains a single folder.
     */
    private class ModelStubWithFolder extends DeleteFolderCommandTest.ModelStub {
        private final ArrayList<Folder> existingFolders = new ArrayList<>();

        @Override
        public boolean hasFolder(Folder folder) {
            requireNonNull(folder);
            return existingFolders.stream().anyMatch(folder::isSameFolder);
        }

        public void addNewFolder(Folder folder) {
            this.existingFolders.add(folder);
        }

        @Override
        public void deleteFolder(Folder folder) {
            requireNonNull(folder);
            this.existingFolders.remove(folder);
        }
    }

}
