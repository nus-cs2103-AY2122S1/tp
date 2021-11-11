package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.residency.exceptions.DuplicatePersonRegException;
import seedu.address.model.residency.exceptions.DuplicateRoomRegException;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalRecordsBook;
import seedu.address.testutil.TypicalResidencyBook;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path TYPICAL_RESIDENCIES_FILE = TEST_DATA_FOLDER.resolve("typicalResidenciesAddressBook.json");
    private static final Path TYPICAL_RECORDS_FILE = TEST_DATA_FOLDER.resolve("typicalRecordsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path DUPLICATE_ROOM_RESIDENCIES_FILE =
            TEST_DATA_FOLDER.resolve("duplicateRoomResidenciesAddressBook.json");
    private static final Path DUPLICATE_PERSON_RESIDENCIES_FILE =
            TEST_DATA_FOLDER.resolve("duplicatePersonResidenciesAddressBook.json");


    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_typicalResidenciesFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_RESIDENCIES_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalResidenciesAddressBook = TypicalResidencyBook.getTypicalAddressBook();

        assertEquals(addressBookFromFile.getResidencyBook(), typicalResidenciesAddressBook.getResidencyBook());
    }

    @Test
    public void toModelType_typicalRecordsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_RECORDS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalRecordsAddressBook = TypicalRecordsBook.getTypicalAddressBook();

        assertEquals(addressBookFromFile.getRecordsBook(), typicalRecordsAddressBook.getRecordsBook());
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateRoomResidenciesFile_throwsDuplicateRoomRegException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ROOM_RESIDENCIES_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(DuplicateRoomRegException.class, DuplicateRoomRegException.MESSAGE,
                dataFromFile::toModelType);
    }

    @Test void toModelType_duplicatePersonsResidenciesFile_throwsDuplicatePersonRegException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_RESIDENCIES_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(DuplicatePersonRegException.class, dataFromFile::toModelType);
    }
}
