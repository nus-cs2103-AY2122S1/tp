package seedu.address.model.order;

import java.util.Comparator;

public class TransactionIdComparator implements Comparator<TransactionRecord> {
    @Override
    public int compare(TransactionRecord o1, TransactionRecord o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
