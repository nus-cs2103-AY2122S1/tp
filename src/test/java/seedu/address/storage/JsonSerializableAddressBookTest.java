package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.LastUpdatedDate;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path CONTACTLESS_PERSON_FILE = TEST_DATA_FOLDER.resolve("contactlessPersonAddressBook.json");
    private static final Path EMPTY_LAST_UPDATED_FILE = TEST_DATA_FOLDER.resolve("emptyLastUpdatedAddressBook.json");
    private static final Path INVALID_LAST_UPDATED_FILE =
            TEST_DATA_FOLDER.resolve("invalidLastUpdatedAddressBook.json");
    private static final Path CLASHING_LESSON_FILE = TEST_DATA_FOLDER.resolve("clashingLessonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
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
    public void toModelType_contactlessPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(CONTACTLESS_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_emptyLastUpdatedAddressBook_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dateFromFile = JsonUtil.readJsonFile(EMPTY_LAST_UPDATED_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonAdaptedLastUpdated.MISSING_FIELD_MESSAGE_FORMAT,
                dateFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidLastUpdatedAddressBook_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dateFromFile = JsonUtil.readJsonFile(INVALID_LAST_UPDATED_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, LastUpdatedDate.MESSAGE_CONSTRAINTS,
                dateFromFile::toModelType);
    }

    @Test
    public void toModelType_clashingLessons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(CLASHING_LESSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonAdaptedPerson.MESSAGE_CLASHING_LESSON,
                dataFromFile::toModelType);
    }
}
