package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;
import seedu.address.testutil.TypicalClients;

public class JsonSerializableClientAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");

    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientsAddressBook.json");
    private static final Path INVALID_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("invalidClientsAddressBook.json");
    private static final Path DUPLICATE_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("duplicateClientsAddressBook.json");

    @Test
    public void toModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalClientsAddressBook = TypicalClients.getTypicalAddressBook();
        AddressBook typicalClientsAddressBookUpdated = new AddressBook();
        for (int i = 0; i < TypicalClients.getTypicalClients().size(); i++) {
            typicalClientsAddressBookUpdated.addClient(Client.updateClient(
                    addressBookFromFile.getClientList().get(i),
                    typicalClientsAddressBook.getClientList().get(i).getName(),
                    typicalClientsAddressBook.getClientList().get(i).getPhoneNumber(),
                    typicalClientsAddressBook.getClientList().get(i).getEmail(),
                    typicalClientsAddressBook.getClientList().get(i).getAddress()
            ));
        }
        assertEquals(addressBookFromFile, typicalClientsAddressBookUpdated);
    }

    @Test
    public void toModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENTS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalClientsAddressBook = TypicalClients.getTypicalAddressBook();
        AddressBook typicalClientsAddressBookUpdated = new AddressBook();
        for (int i = 0; i < 2; i++) {
            typicalClientsAddressBookUpdated.addClient(Client.updateClient(
                    addressBookFromFile.getClientList().get(i),
                    typicalClientsAddressBook.getClientList().get(0).getName(),
                    typicalClientsAddressBook.getClientList().get(0).getPhoneNumber(),
                    typicalClientsAddressBook.getClientList().get(0).getEmail(),
                    typicalClientsAddressBook.getClientList().get(0).getAddress()
            ));
        }
        assertEquals(addressBookFromFile, typicalClientsAddressBookUpdated);
    }
}
