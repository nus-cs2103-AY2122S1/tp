package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.TestUtil;
import seedu.address.testutil.TypicalPersons;


class CsvAddressBookImportExportTest {


    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "CsvAddressBookImportExport");
    private static final Path EMPTY_IMPORT = TEST_DATA_FOLDER.resolve("emptyAddressBook.csv");
    private static final Path TWO_DUPLICATE = TEST_DATA_FOLDER.resolve("twoDuplicatePerson.csv");

    // Misc
    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CsvAddressBookImportExport(
                null, TEST_DATA_FOLDER));
    }

    @Test
    public void getImportExportPath_path_path() {
        ImportExport importExport = new CsvAddressBookImportExport(EMPTY_IMPORT, TEST_DATA_FOLDER);
        assertEquals(EMPTY_IMPORT, importExport.getImportExportPath());
    }

    @Test
    public void importIntoAddressBook_missingImportFile_emptyOptional() throws DataConversionException {
        ImportExport importExport = new CsvAddressBookImportExport(
                TestUtil.getFilePathInSandboxFolder("doesNotExist.csv"), TEST_DATA_FOLDER);
        Model model = new ModelManager(new AddressBookStub(), new UserPrefs());
        assertEquals(Optional.empty(), importExport.importIntoAddressBook(model));
    }

    @Test
    public void importIntoAddressBook_duplicateUpdateBenson_emptyOptional() throws DataConversionException {
        ImportExport importExport = new CsvAddressBookImportExport(TWO_DUPLICATE, TEST_DATA_FOLDER);
        // setup
        // Benson was previously not called. import called
        // Alice was previously not called. import not called
        assertFalse(TypicalPersons.ALICE.getIsCalled().value);
        assertFalse(TypicalPersons.BENSON.getIsCalled().value);

        List<Person> peopleAlreadyInDatabase = Arrays.asList(TypicalPersons.ALICE, TypicalPersons.BENSON);
        AddressBookStub database = new AddressBookStub();
        database.setPersons(peopleAlreadyInDatabase);
        Model model = new ModelManager(database, new UserPrefs());
        importExport.importIntoAddressBook(model);
        ObservableList<Person> updated = model.getFilteredPersonList();

        assert(updated.size() == 3);

        Person updatedAlice = updated.get(0);
        Person updatedBenson = updated.get(1);
        Person addedIda = updated.get(2);


        // Since import called, benson updated call status
        // Since import not called, alice not updated
        assertFalse(updatedAlice.getIsCalled().value);
        assertTrue(updatedBenson.getIsCalled().value);
        assertTrue(addedIda.getName().toString().equals("Ida Mueller"));
    }

    @Test
    public void importIntoAddressBook_emptyImports_emptyOptional() throws DataConversionException {
        ImportExport importExport = new CsvAddressBookImportExport(EMPTY_IMPORT, TEST_DATA_FOLDER);
        Model model = new ModelManager(new AddressBookStub(), new UserPrefs());
        assertEquals(0, importExport.importIntoAddressBook(model).get().size());
    }

    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        public void setPersons(List<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }


}
