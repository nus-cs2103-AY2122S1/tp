package seedu.address.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.order.TransactionRecord;

public class TransactionList implements ReadOnlyTransactionList {

    private ArrayList<TransactionRecord> transactionRecordList;

    public TransactionList(ArrayList<TransactionRecord> transactionRecordList) {
        this.transactionRecordList = transactionRecordList;
    }

    public TransactionList(ReadOnlyTransactionList tobeCopied) {
        this.transactionRecordList = new ArrayList<>(tobeCopied.getTransactionRecordList());
    }

    public TransactionList() {
        this(new ArrayList());
    }

    @Override
    public ObservableList<TransactionRecord> getTransactionRecordList() {
        ObservableList<TransactionRecord> observableList =
                FXCollections.observableList(transactionRecordList);
        return FXCollections.unmodifiableObservableList(observableList);
    }

    /**
     * Adds a {@code TransactionRecord} to the TransactionList.
     */
    public void add(TransactionRecord transactionRecord) {
        this.transactionRecordList.add(transactionRecord);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof TransactionList)) {
            return false;
        }

        // state check
        TransactionList other = (TransactionList) obj;
        return transactionRecordList.equals(other.transactionRecordList);
    }

    /**
     * Checking whether transacted items are the same.
     * Only considers transacted information (i.e. not cost nor tags)
     */
    public boolean equalTestsTransactionLists (Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof TransactionList)) {
            return false;
        }

        // state check
        TransactionList other = (TransactionList) obj;
        if (this.transactionRecordList.size() != other.transactionRecordList.size()) {
            return false;
        }
        for (int i = 0; i < this.transactionRecordList.size(); i += 1) {
            if (!this.transactionRecordList.get(i).isSameTransactionInfo(
                    other.transactionRecordList.get(i).getOrderItems())) {
                return false;
            }
        }
        return true;
    }
}
