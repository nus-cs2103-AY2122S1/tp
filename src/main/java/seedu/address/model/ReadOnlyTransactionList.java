package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.order.TransactionRecord;

public interface ReadOnlyTransactionList {
    /**
     * Returns an unmodifiable view of the items list.
     * This list will not contain any duplicate items.
     */
    ArrayList<TransactionRecord> getTransactionRecordList();
}
