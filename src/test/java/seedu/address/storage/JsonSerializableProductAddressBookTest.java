package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.product.Product;
import seedu.address.testutil.TypicalClients;
import seedu.address.testutil.TypicalProducts;

public class JsonSerializableProductAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");

    private static final Path TYPICAL_PRODUCTS_FILE = TEST_DATA_FOLDER.resolve("typicalProductsAddressBook.json");
    private static final Path INVALID_PRODUCTS_FILE = TEST_DATA_FOLDER.resolve("invalidProductsAddressBook.json");
    private static final Path DUPLICATE_PRODUCTS_FILE = TEST_DATA_FOLDER.resolve("duplicateProductsAddressBook.json");

    @Test
    public void toModelType_typicalProductsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PRODUCTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalProductsAddressBook = TypicalProducts.getTypicalAddressBook();
        AddressBook typicalProductsAddressBookUpdated = new AddressBook();
        for (int i = 0; i < TypicalClients.getTypicalClients().size(); i++) {
            typicalProductsAddressBookUpdated.addProduct(Product.updateProduct(
                    addressBookFromFile.getProductList().get(i),
                    typicalProductsAddressBook.getProductList().get(i).getName(),
                    typicalProductsAddressBook.getProductList().get(i).getUnitPrice(),
                    typicalProductsAddressBook.getProductList().get(i).getQuantity()
            ));
        }
        assertEquals(addressBookFromFile, typicalProductsAddressBookUpdated);
    }

    @Test
    public void toModelType_invalidProductFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PRODUCTS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProducts_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PRODUCTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalProductsAddressBook = TypicalProducts.getTypicalAddressBook();
        AddressBook typicalProductsAddressBookUpdated = new AddressBook();
        for (int i = 0; i < 2; i++) {
            typicalProductsAddressBookUpdated.addProduct(Product.updateProduct(
                    addressBookFromFile.getProductList().get(i),
                    typicalProductsAddressBook.getProductList().get(0).getName(),
                    typicalProductsAddressBook.getProductList().get(0).getUnitPrice(),
                    typicalProductsAddressBook.getProductList().get(0).getQuantity()
            ));
        }
        assertEquals(addressBookFromFile, typicalProductsAddressBookUpdated);
    }
}
