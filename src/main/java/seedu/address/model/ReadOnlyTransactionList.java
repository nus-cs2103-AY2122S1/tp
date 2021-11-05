package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.order.TransactionRecord;

public interface ReadOnlyTransactionList {
    /**
     * Returns an unmodifiable view of the items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<TransactionRecord> getTransactionRecordList();
}
