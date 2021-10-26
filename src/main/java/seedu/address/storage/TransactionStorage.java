package seedu.address.storage;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.order.Order;
import seedu.address.model.order.TransactionRecord;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class TransactionStorage {
    public void saveInventory(ArrayList<TransactionRecord> orderList, Path filePath) throws IOException {
        requireNonNull(orderList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTransaction(orderList.stream()), filePath);
    }

    public Optional<List<TransactionRecord>> readTransaction(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTransaction> jsonTransaction = JsonUtil.readJsonFile(
                filePath, JsonSerializableTransaction.class);
        if (!jsonTransaction.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTransaction.get().toModelType());
        } catch (IllegalValueException ive) {
//            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }
}
