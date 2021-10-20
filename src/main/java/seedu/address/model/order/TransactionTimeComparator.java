package seedu.address.model.order;

import java.util.Comparator;

public class TransactionTimeComparator implements Comparator<TransactionRecord> {
    @Override
    public int compare(TransactionRecord o1, TransactionRecord o2) {
        return o1.getTimestamp().compareTo(o2.getTimestamp());
    }
}
