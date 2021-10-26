package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.order.TransactionRecord;

public class TransactionStorage {
    /**
     * Saves list of TransactionRecord to transaction.json
     *
     * @param orderList list of TransactionRecord. Cannot be null.
     * @param filePath path of Transaction. Cannot be null.
     * @throws IOException if transaction cannot be saved.
     */
    public void saveTransaction(ArrayList<TransactionRecord> orderList, Path filePath) throws IOException {
        requireNonNull(orderList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTransaction(orderList.stream()), filePath);
    }

    /**
     * Reads list of TransactionRecords from transaction.json
     * @param filePath path of Transaction. Cannot be null.
     * @return The TransactionRecord List enclosed in an Optional.
     * @throws DataConversionException If json file's format is not correct.
     */
    public Optional<List<TransactionRecord>> readTransaction(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTransaction> jsonTransaction = JsonUtil.readJsonFile(
                filePath, JsonSerializableTransaction.class);
        if (!jsonTransaction.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTransaction.get().toModelType());
        } catch (IllegalValueException e) {
            throw new DataConversionException(e);
        }
    }
}
