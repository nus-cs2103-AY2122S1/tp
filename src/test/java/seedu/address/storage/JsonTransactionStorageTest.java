package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.PathUtil.addToPath;
import static seedu.address.testutil.TypicalOrders.getTypicalTransaction;
import static seedu.address.testutil.TypicalOrders.getTypicalTransactionList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTransactionList;
import seedu.address.model.TransactionList;

public class JsonTransactionStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTransactionStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTransactions_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTransactions(null));
    }

    private java.util.Optional<ReadOnlyTransactionList> readTransactions(String filePath) throws Exception {
        return new JsonTransactionStorage(Paths.get(filePath))
                .readTransactionList(addToPath(TEST_DATA_FOLDER, filePath));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTransactions("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTransactions("notJsonFormatTransactions.json"));
    }

    @Test
    public void readTransactions_invalidItem_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTransactions("invalidItemTransactions.json"));
    }

    @Test
    public void readAndSaveInventory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInventory.json");
        TransactionList original = getTypicalTransactionList();
        JsonTransactionStorage jsonTransactionStorage = new JsonTransactionStorage(filePath);

        // Save in new file and read back
        jsonTransactionStorage.saveTransactionList(original, filePath);
        ReadOnlyTransactionList readBack = jsonTransactionStorage.readTransactionList(filePath).get();
        boolean test = original.equalTestsTransactionLists(new TransactionList(readBack));
        assertTrue(test);

        // Modify data, overwrite exiting file, and read back
        original.add(getTypicalTransaction());
        jsonTransactionStorage.saveTransactionList(original, filePath);
        readBack = jsonTransactionStorage.readTransactionList(filePath).get();
        boolean test2 = original.equalTestsTransactionLists(new TransactionList(readBack));
        assertTrue(test2);

        // Save and read without specifying file path
        original.add(getTypicalTransaction());
        jsonTransactionStorage.saveTransactionList(original); // file path not specified
        readBack = jsonTransactionStorage.readTransactionList().get(); // file path not specified
        boolean test3 = original.equalTestsTransactionLists(new TransactionList(readBack));
        assertTrue(test3);

    }

    @Test
    public void saveTransactionList_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTransactions(null, "SomeFile.json"));
    }

    /**
     * Saves {@code transactionList} at the specified {@code filePath}.
     */
    private void saveTransactions(ReadOnlyTransactionList transactionList, String filePath) {
        try {
            new JsonTransactionStorage(Paths.get(filePath))
                    .saveTransactionList(transactionList, addToPath(TEST_DATA_FOLDER, filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTransactionList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTransactions(getTypicalTransactionList(), null));
    }
}
