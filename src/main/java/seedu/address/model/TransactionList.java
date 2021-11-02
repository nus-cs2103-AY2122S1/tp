package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.order.TransactionRecord;

public class TransactionList implements ReadOnlyTransactionList {

    private ArrayList<TransactionRecord> transactionRecordList;

    public TransactionList(ArrayList<TransactionRecord> transactionRecordList) {
        this.transactionRecordList = transactionRecordList;
    }

    public TransactionList() {
        this(new ArrayList());
    }

    @Override
    public ArrayList<TransactionRecord> getTransactionRecordList() {
        return transactionRecordList;
    }
}
