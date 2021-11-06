package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTransactionList;
import seedu.address.model.TransactionList;
import seedu.address.testutil.TypicalOrders;

public class JsonSerializableTransactionListTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableTransactionListTest");
    private static final Path TYPICAL_TRANSACTIONS_FILE =
            TEST_DATA_FOLDER.resolve("typicalTransactions.json");

    @Test
    public void toModelType_typicalTransactionsFile_success() throws Exception {
        JsonSerializableTransactionList dataFromFile = JsonUtil.readJsonFile(TYPICAL_TRANSACTIONS_FILE,
                JsonSerializableTransactionList.class).get();
        TransactionList listFromFile = dataFromFile.toModelType();
        TransactionList typicalTransactions = TypicalOrders.getTypicalTransactionList();
        boolean test = listFromFile.equalTestsTransactionLists(typicalTransactions);
        assertTrue(test);
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void saveInventory() {
        ReadOnlyTransactionList txnList = TypicalOrders.getTypicalTransactionList();

        try {
            new JsonTransactionStorage(Paths.get("typicalTransactions.json"))
                    .saveTransactionList(txnList, addToTestDataPathIfNotNull("typicalTransactions.json"));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }

    }
}
