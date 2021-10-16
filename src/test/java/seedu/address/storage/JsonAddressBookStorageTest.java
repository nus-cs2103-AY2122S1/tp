package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.HOON;
import static seedu.address.testutil.TypicalClients.IDA;
import static seedu.address.testutil.TypicalProducts.CALCULATOR;
import static seedu.address.testutil.TypicalProducts.IPHONE;
import static seedu.address.testutil.TypicalProducts.TISSUE;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.product.Product;
import seedu.address.testutil.TypicalClients;
import seedu.address.testutil.TypicalProducts;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAddressBook> readAddressBook(String filePath) throws Exception {
        return new JsonAddressBookStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidClientsAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()->readAddressBook("invalidClientsAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidProductAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()->readAddressBook("invalidProductsAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidClientsAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidClientsAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidProductsAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidProductsAddressBook.json"));
    }

    @Test
    public void readAndSaveClientAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempClientAddressBook.json");
        AddressBook original = TypicalClients.getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        AddressBook compare = new AddressBook();
        for (int i = 0; i < original.getClientList().size(); i++) {
            compare.addClient(Client.updateClient(
                    original.getClientList().get(i),
                    readBack.getClientList().get(i).getName(),
                    readBack.getClientList().get(i).getPhoneNumber(),
                    readBack.getClientList().get(i).getEmail(),
                    readBack.getClientList().get(i).getAddress()
            ));
        }
        assertEquals(original, compare);

        //Modify data, overwrite exiting file, and read back
        original.addClient(HOON);
        original.removeClient(ALICE);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        compare = new AddressBook();
        for (int i = 0; i < original.getClientList().size(); i++) {
            compare.addClient(Client.updateClient(
                    original.getClientList().get(i),
                    readBack.getClientList().get(i).getName(),
                    readBack.getClientList().get(i).getPhoneNumber(),
                    readBack.getClientList().get(i).getEmail(),
                    readBack.getClientList().get(i).getAddress()
            ));
        }
        assertEquals(original, compare);

        //Save and read without specifying file path
        original.addClient(IDA);
        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
        compare = new AddressBook();
        for (int i = 0; i < original.getClientList().size(); i++) {
            compare.addClient(Client.updateClient(
                    original.getClientList().get(i),
                    readBack.getClientList().get(i).getName(),
                    readBack.getClientList().get(i).getPhoneNumber(),
                    readBack.getClientList().get(i).getEmail(),
                    readBack.getClientList().get(i).getAddress()
            ));
        }
        assertEquals(original, compare);

    }

    @Test
    public void readAndSaveProductAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempProductAddressBook.json");
        AddressBook original = TypicalProducts.getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        AddressBook compare = new AddressBook();
        for (int i = 0; i < original.getProductList().size(); i++) {
            compare.addProduct(Product.updateProduct(
                    original.getProductList().get(i),
                    readBack.getProductList().get(i).getName(),
                    readBack.getProductList().get(i).getUnitPrice(),
                    readBack.getProductList().get(i).getQuantity()
            ));
        }
        assertEquals(original, compare);

        //Modify data, overwrite exiting file, and read back
        original.addProduct(CALCULATOR);
        original.removeProduct(IPHONE);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        compare = new AddressBook();
        for (int i = 0; i < original.getProductList().size(); i++) {
            compare.addProduct(Product.updateProduct(
                    original.getProductList().get(i),
                    readBack.getProductList().get(i).getName(),
                    readBack.getProductList().get(i).getUnitPrice(),
                    readBack.getProductList().get(i).getQuantity()
            ));
        }
        assertEquals(original, compare);

        //Save and read without specifying file path
        original.addProduct(TISSUE);
        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
        compare = new AddressBook();
        for (int i = 0; i < original.getProductList().size(); i++) {
            compare.addProduct(Product.updateProduct(
                    original.getProductList().get(i),
                    readBack.getProductList().get(i).getName(),
                    readBack.getProductList().get(i).getUnitPrice(),
                    readBack.getProductList().get(i).getQuantity()
            ));
        }
        assertEquals(original, compare);

    }

    /*@Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AddressBook original = getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));

    }*/

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null));
    }
}
