package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTransactionList;

/**
 * An Immutable TransactionList that is serializable to JSON format.
 */
public class JsonTransactionStorage implements TransactionStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTransactionStorage.class);

    private Path filePath;

    public JsonTransactionStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getTransactionFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTransactionList> readTransactionList() throws DataConversionException, IOException {
        return readTransactionList(filePath);
    }

    @Override
    public Optional<ReadOnlyTransactionList> readTransactionList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTransaction> jsonTransaction = JsonUtil.readJsonFile(
                filePath, JsonSerializableTransaction.class);
        if (!jsonTransaction.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTransaction.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTransactionList(ReadOnlyTransactionList transactionList) throws IOException {
        saveTransactionList(transactionList, filePath);
    }

    @Override
    public void saveTransactionList(ReadOnlyTransactionList transactionList, Path filePath) throws IOException {
        requireNonNull(transactionList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTransaction(transactionList.getTransactionRecordList().stream()),
                filePath);
    }
}
