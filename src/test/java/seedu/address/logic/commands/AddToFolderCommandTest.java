package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderName;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddToFolderCommandTest {

    @Test
    public void constructor_nullFolderNameAndIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddToFolderCommand(null, null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        FolderName folderName = new FolderName("Folder 1");
        Folder validFolder = new Folder(folderName);
        Index index = ParserUtil.parseIndex("1");
        CommandResult commandResult = new AddToFolderCommand(index, folderName).execute(modelStub);

        assertEquals(String.format(AddToFolderCommand.MESSAGE_SUCCESS, validFolder), commandResult.getFeedbackToUser());
    }


    @Test
    public void equals() throws ParseException {
        FolderName cs2100FolderName = new FolderName("CS2100");
        Folder cs2100 = new Folder(cs2100FolderName);
        Index indexOne = ParserUtil.parseIndex("1");

        FolderName cs2103FolderName = new FolderName("CS2103");
        Folder cs2103 = new Folder(cs2103FolderName);
        Index indexTwo = ParserUtil.parseIndex("2");

        AddToFolderCommand addCS2100Command = new AddToFolderCommand(indexOne, cs2100FolderName);
        AddToFolderCommand addCS2103Command = new AddToFolderCommand(indexTwo, cs2103FolderName);

        // same object -> returns true
        assertTrue(addCS2100Command.equals(addCS2100Command));

        // same values -> returns true
        AddToFolderCommand addCS2100CommandDuplicate = new AddToFolderCommand(indexOne, cs2100FolderName);
        assertTrue(addCS2100Command.equals(addCS2100CommandDuplicate));

        // different types -> returns false
        assertFalse(addCS2100Command.equals(1));

        // null -> returns false
        assertFalse(addCS2100Command.equals(null));

        // different folder -> returns false
        assertFalse(addCS2100Command.equals(addCS2103Command));
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
     * A Model stub with one Person inside Folder.
     */
    private class ModelStubAcceptingPersonAdded extends AddToFolderCommandTest.ModelStub {

        final ObservableList<Person> personsAdded = FXCollections.observableArrayList(new PersonBuilder().build());

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addContactToFolder(Person target, FolderName folderName) {
            requireAllNonNull(target, folderName);
            personsAdded.add(target);
        }

        @Override
        public boolean folderContainsPerson(Person target, FolderName name) {
            requireAllNonNull(target, name);
            return false;
        }

        @Override
        public boolean hasFolderName(FolderName folderName) {
            requireNonNull(folderName);
            return true;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return personsAdded;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
