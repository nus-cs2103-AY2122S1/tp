package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DUPLICATE_PERSON_FILENAME_JSON;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FILENAME_TXT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILENAME_CSV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILENAME_JSON;
import static seedu.address.logic.commands.CommandTestUtil.WRONGLY_FORMATTED_CSV;
import static seedu.address.logic.commands.CommandTestUtil.WRONGLY_FORMATTED_JSON;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.nio.file.Path;
import java.util.ArrayList;
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
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;
import seedu.address.ui.PersonListPanel;

/**
 * Test class for {@link ImportCommand}.
 * Test exports are done in {@code src/test/data/ExportImportCommandTest/EmptyFolder/} and
 * {@code src/test/data/ExportImportCommandTest/TestFiles/}.
 */
public class ImportCommandTest {
    private static final String PATH_EMPTY_FOLDER = "src/test/data/ExportImportCommandTest/EmptyFolder/";
    private static final String PATH_TEST_FILES = "src/test/data/ExportImportCommandTest/TestFiles/";

    private final ModelStub modelStub = new ModelStubAcceptingPersonAdded();
    private final ModelStub expectedModelStub = new ModelStubAcceptingPersonAdded();

    @Test
    public void equals() {
        ImportCommand importFirstCommand = new ImportCommand("first");
        ImportCommand importSecondCommand = new ImportCommand("second");

        // same object -> returns true
        assertTrue(importFirstCommand.equals(importFirstCommand));

        // same filename -> returns true
        assertTrue(importFirstCommand.equals(new ImportCommand("first")));

        // different types -> returns false
        assertFalse(importFirstCommand.equals(1));

        // null -> returns false
        assertFalse(importFirstCommand.equals(null));

        // different filename -> returns false
        assertFalse(importFirstCommand.equals(importSecondCommand));

    }

    /**
     * Imports the JSON file with the filename provided.
     * Test occurs in src/test/data/ExportImportCommandTest/TestFiles.
     */
    @Test
    public void execute_fileNameJson_importSuccess() {
        Person newPerson = new PersonBuilder().build();
        expectedModelStub.addPerson(newPerson);
        ImportCommand importNewPerson = new ImportCommand(PATH_TEST_FILES, VALID_FILENAME_JSON);
        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_SUCCESS, VALID_FILENAME_JSON);
        assertCommandSuccess(importNewPerson, modelStub, expectedMessage, expectedModelStub);
    }

    /**
     * Imports the CSV file with the filename provided.
     * Test occurs in src/test/data/ExportImportCommandTest/TestFiles.
     */
    @Test
    public void execute_fileNameCsv_importSuccess() {
        Person newPerson = new PersonBuilder().build();
        expectedModelStub.addPerson(newPerson);
        ImportCommand importNewPerson = new ImportCommand(PATH_TEST_FILES, VALID_FILENAME_CSV);
        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_SUCCESS, VALID_FILENAME_CSV);
        assertCommandSuccess(importNewPerson, modelStub, expectedMessage, expectedModelStub);
    }

    /**
     * Tests if Import command fails when there is no file with the provided JSON file name.
     * Test occurs in src/test/data/ExportImportCommandTest/EmptyFolder.
     */
    @Test
    public void execute_jsonFileMissing_importFail() {
        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_FILE_NOT_FOUND, VALID_FILENAME_JSON);
        ImportCommand importNewPerson = new ImportCommand(PATH_EMPTY_FOLDER, VALID_FILENAME_JSON);
        assertCommandFailure(importNewPerson, modelStub, expectedMessage);
    }

    /**
     * Tests if Import command fails when there is no file with the provided CSV file name.
     * Test occurs in src/test/data/ExportImportCommandTest/EmptyFolder.
     */
    @Test
    public void execute_csvFileMissing_importFail() {
        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_FILE_NOT_FOUND, VALID_FILENAME_CSV);
        ImportCommand importNewPerson = new ImportCommand(PATH_EMPTY_FOLDER, VALID_FILENAME_CSV);
        assertCommandFailure(importNewPerson, modelStub, expectedMessage);
    }

    /**
     * Tests if Import command fails when there is the file provided is of the wrong type.
     * This should not happen as file type should be verified in ImportCommandParser.
     * However, it is still checked here for completeness.
     */
    @Test
    public void execute_fileNameWrongFileType_importFail() {
        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_FILE_WRONG_TYPE, INVALID_FILENAME_TXT);
        ImportCommand importNewPerson = new ImportCommand(PATH_TEST_FILES, INVALID_FILENAME_TXT);
        assertCommandFailure(importNewPerson, modelStub, expectedMessage);
    }

    /**
     * Tests if Import command fails when the json file is wrongly formatted.
     */
    @Test
    public void execute_wronglyFormattedJson_importFail() {
        String expectedMessage = String.format(
                ImportCommand.MESSAGE_IMPORT_FILE_WRONGLY_FORMATTED, WRONGLY_FORMATTED_JSON);
        ImportCommand importCommand = new ImportCommand(PATH_TEST_FILES, WRONGLY_FORMATTED_JSON);
        assertCommandFailure(importCommand, modelStub, expectedMessage);
    }

    /**
     * Tests if Import command fails when the csv file is wrongly formatted.
     */
    @Test
    public void execute_wronglyFormattedCsv_importFail() {
        String expectedMessage = String.format(
                ImportCommand.MESSAGE_IMPORT_FILE_WRONGLY_FORMATTED, WRONGLY_FORMATTED_CSV);
        ImportCommand importCommand = new ImportCommand(PATH_TEST_FILES, WRONGLY_FORMATTED_CSV);
        assertCommandFailure(importCommand, modelStub, expectedMessage);
    }

    /**
     * Checks for no change expected when importing a contact which already exists.
     */
    @Test
    public void execute_duplicatePersonImported_noChangeSuccess() {
        modelStub.addPerson(TypicalPersons.ALICE);
        expectedModelStub.addPerson(TypicalPersons.ALICE);
        String expectedMessage = String.format(ImportCommand.MESSAGE_IMPORT_SUCCESS, DUPLICATE_PERSON_FILENAME_JSON);
        ImportCommand importCommand = new ImportCommand(PATH_TEST_FILES, DUPLICATE_PERSON_FILENAME_JSON);
        assertCommandSuccess(importCommand, modelStub, expectedMessage, expectedModelStub);
    }

    /**
     * Model stub which accepts Person objects.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            if (personsAdded.contains(person)) {
                throw new DuplicatePersonException();
            }
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return new AddressBook().getPersonList();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ModelStubAcceptingPersonAdded)) {
                return false;
            }
            return personsAdded.equals(((ModelStubAcceptingPersonAdded) other).personsAdded);
        }

        @Override
        public int hashCode() {
            return Objects.hash(personsAdded);
        }
    }

    /**
     * Stub for the {@link Model} class.
     */
    private class ModelStub implements Model {

        @Override
        public Person getUserProfile() {
            return null;
        }

        @Override
        public boolean isProfilePresent() {
            return false;
        }

        @Override
        public void setUserProfile(Person userProfile) {

        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {

        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            return null;
        }

        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {

        }

        @Override
        public Path getAddressBookFilePath() {
            return null;
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {

        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {

        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return null;
        }

        @Override
        public boolean hasPerson(Person person) {
            return false;
        }

        @Override
        public void deletePerson(Person target) {

        }

        @Override
        public void favoritePerson(Person target) {

        }

        @Override
        public void unfavoritePerson(Person target) {

        }

        @Override
        public void addPerson(Person person) {

        }

        @Override
        public void setPerson(Person target, Person editedPerson) {

        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {

        }

        @Override
        public void setSelectedIndex(int index) {

        }

        @Override
        public int getSelectedIndex() {
            return 0;
        }

        @Override
        public void setPersonListControl(PersonListPanel personListPanel) {

        }

        @Override
        public PersonListPanel getPersonListControl() {
            return null;
        }

        @Override
        public void setTabIndex(int index) {

        }
    }
}

