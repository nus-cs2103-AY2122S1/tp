package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalAddressBook;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_MEMBERS_FILE = TEST_DATA_FOLDER.resolve("typicalMembersAddressBook.json");
    private static final Path TYPICAL_FACILITIES_FILE = TEST_DATA_FOLDER.resolve("typicalFacilitiesAddressBook.json");
    private static final Path INVALID_MEMBER_FILE = TEST_DATA_FOLDER.resolve("invalidMemberAddressBook.json");
    private static final Path INVALID_FACILITY_FILE = TEST_DATA_FOLDER.resolve("invalidFacilityAddressBook.json");
    private static final Path DUPLICATE_MEMBER_FILE = TEST_DATA_FOLDER.resolve("duplicateMemberAddressBook.json");
    private static final Path DUPLICATE_FACILITY_FILE = TEST_DATA_FOLDER.resolve("duplicateFacilityAddressBook.json");

    @Test
    public void toModelType_typicalMembersFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEMBERS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalMembersAddressBook = TypicalAddressBook.getTypicalAddressBookEmptyFacilityList();
        assertEquals(addressBookFromFile, typicalMembersAddressBook);
    }

    @Test
    public void toModelType_typicalFacilitiesFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_FACILITIES_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalFacilitiesAddressBook = TypicalAddressBook.getTypicalAddressBookEmptyMemberList();
        assertEquals(addressBookFromFile, typicalFacilitiesAddressBook);
    }

    @Test
    public void toModelType_invalidMemberFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_MEMBER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidFacilitiesFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_FACILITY_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEMBER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_MEMBER,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFacilities_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FACILITY_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_FACILITY,
                dataFromFile::toModelType);
    }

}
