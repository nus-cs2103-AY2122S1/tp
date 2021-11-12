package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FILENAME_TXT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXISTING_FILE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILENAME_CSV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILENAME_JSON;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.ui.PersonListPanel;

/**
 * Test class for {@link ExportCommand}.
 * Test exports are done in {@code src/test/data/ExportImportCommandTest/EmptyFolder/} and
 * {@code src/test/data/ExportImportCommandTest/TestFiles/}.
 */
public class ExportCommandTest {
    private static final String PATH_EMPTY_FOLDER = "src/test/data/ExportImportCommandTest/EmptyFolder/";
    private static final String PATH_TEST_FILES = "src/test/data/ExportImportCommandTest/TestFiles/";

    private final ModelStub modelStub = new ModelStubForExport();
    private final ModelStub expectedModelStub = new ModelStubForExport();

    @Test
    public void equals() {
        ExportCommand exportFirstCommand = new ExportCommand("first");
        ExportCommand exportSecondCommand = new ExportCommand("second");

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same filename -> returns true
        assertTrue(exportFirstCommand.equals(new ExportCommand("first")));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // different filename -> returns false
        assertFalse(exportFirstCommand.equals(exportSecondCommand));
    }

    /**
     * Tests if ExportCommand creates a new JSON file.
     * Test file is deleted after creation.
     * Test occurs in src/test/data/ExportImportCommandTest/EmptyFolder.
     */
    @Test
    public void execute_fileNameJson_fileCreated() {
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_SUCCESS, VALID_FILENAME_JSON);

        ExportCommand exportCommand = new ExportCommand(PATH_EMPTY_FOLDER, VALID_FILENAME_JSON);
        assertCommandSuccess(exportCommand, modelStub, expectedMessage, expectedModelStub);
        try {
            FileUtil.deleteFileIfExists(Path.of(PATH_EMPTY_FOLDER + VALID_FILENAME_JSON));
        } catch (IOException ioe) {
            System.out.println("Error deleting importNewPerson.json");
        }
    }

    /**
     * Tests if ExportCommand creates a new CSV file.
     * Test file is deleted after creation.
     * Test occurs in src/test/data/ExportImportCommandTest/EmptyFolder.
     */
    @Test
    public void execute_fileNameCsv_fileCreated() {
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_SUCCESS, VALID_FILENAME_CSV);

        ExportCommand exportCommand = new ExportCommand(PATH_EMPTY_FOLDER, VALID_FILENAME_CSV);
        assertCommandSuccess(exportCommand, modelStub, expectedMessage, expectedModelStub);
        try {
            FileUtil.deleteFileIfExists(Path.of(PATH_EMPTY_FOLDER + VALID_FILENAME_CSV));
        } catch (IOException ioe) {
            System.out.println("Error deleting importNewPerson.csv");
        }
    }

    /**
     * Tests if Export command fails when there is an existing file with the given name.
     * Test occurs in src/test/data/ExportImportCommandTest/TestFiles.
     */
    @Test
    public void execute_fileName_fileExists() {
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_FAILURE, VALID_EXISTING_FILE);
        ExportCommand exportCommand = new ExportCommand(PATH_TEST_FILES, VALID_EXISTING_FILE);
        assertCommandFailure(exportCommand, modelStub, expectedMessage);
    }

    /**
     * Tests if Export command fails when there is the filename provided is of the wrong type.
     * This should not happen as file type should be verified in ExportCommandParser.
     * However, it is still checked here for completeness.
     */
    @Test
    public void execute_fileNameWrongFileType_importFail() {
        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_FILE_WRONG_TYPE, INVALID_FILENAME_TXT);
        ExportCommand exportCommand = new ExportCommand(PATH_TEST_FILES, INVALID_FILENAME_TXT);
        assertCommandFailure(exportCommand, modelStub, expectedMessage);
    }

    /**
     * A model stub for export command.
     */
    private class ModelStubForExport extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

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
            if (!(other instanceof ModelStubForExport)) {
                return false;
            }
            return personsAdded.equals(((ModelStubForExport) other).personsAdded);
        }

        @Override
        public int hashCode() {
            return Objects.hash(personsAdded);
        }
    }

    /**
     * Stub for {@link Model} class.
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
