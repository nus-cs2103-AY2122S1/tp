package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTransactionList;

public class JsonTransactionStorage implements TransactionStorage {

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
    public Optional<ReadOnlyTransactionList> readTransactionList(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableTransaction> jsonTransaction = JsonUtil.readJsonFile(
                filePath, JsonSerializableTransaction.class);
        if (!jsonTransaction.isPresent()) {
            System.out.println("yo empty bro");
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTransaction.get().toModelType());
        } catch (IllegalValueException e) {
            throw new DataConversionException(e);
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
