package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.DONUT;
import static seedu.address.testutil.TypicalItems.getTypicalItems;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TransactionRecordTest {

    private static final String id1 = "id1";
    private static final String id2 = "id2";
    private static final Instant timestamp1 = Instant.ofEpochSecond(1000);
    private static final Instant timestamp2 = Instant.ofEpochSecond(8047);

    private TransactionRecord transaction =
            new TransactionRecord(getTypicalItems(), id1, timestamp1);

    @Test
    public void constructor() {
        TransactionRecord t1 = new TransactionRecord(new Order(List.of(APPLE_PIE)));
        assertEquals(List.of(APPLE_PIE.justTransactedDetails()), t1.getOrderItems());

        t1 = new TransactionRecord(List.of(APPLE_PIE), id1, timestamp1);
        assertEquals(List.of(APPLE_PIE.justTransactedDetails()), t1.getOrderItems());
        assertEquals(id1, t1.getId());
        assertEquals(timestamp1, t1.getTimestamp());
    }

    @Test
    public void addItem_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> transaction.addItem(APPLE_PIE));
    }

    @Test
    public void removeItem_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> transaction.removeItem(APPLE_PIE, 1));
    }

    @Test
    public void getTimeStringTest() {
        TransactionRecord copy = new TransactionRecord(
                transaction.getOrderItems(),
                transaction.getId(),
                transaction.getTimestamp()
        );

        assertEquals(copy.getTimeString(), transaction.getTimeString());
    }

    @Test
    public void isSameTransactionInfo() {
        TransactionRecord other = new TransactionRecord(
                List.of(APPLE_PIE),
                transaction.getId(),
                transaction.getTimestamp()
        );

        TransactionRecord other2 = new TransactionRecord(
                List.of(DONUT),
                transaction.getId(),
                transaction.getTimestamp()
        );
        assertFalse(other2.isSameTransactionInfo(other.getOrderItems()));
    }

    @Test
    public void equals() {
        // same values -> returns true
        TransactionRecord copy = new TransactionRecord(
                transaction.getOrderItems(),
                transaction.getId(),
                transaction.getTimestamp()
        );
        assertTrue(transaction.equals(copy));

        // same object -> returns true
        assertTrue(transaction.equals(transaction));

        // null -> returns false
        assertFalse(transaction.equals(null));

        // different type -> returns false
        assertFalse(transaction.equals(5));

        // different items -> returns false
        TransactionRecord other = new TransactionRecord(
                List.of(APPLE_PIE),
                transaction.getId(),
                transaction.getTimestamp()
        );
        assertFalse(transaction.equals(other));

        // different items (same count) -> returns false
        TransactionRecord other2 = new TransactionRecord(
                List.of(DONUT),
                transaction.getId(),
                transaction.getTimestamp()
        );
        assertFalse(other2.isSameTransactionInfo(other.getOrderItems()));

        // different id -> returns false
        other = new TransactionRecord(
                transaction.getOrderItems(),
                id2,
                transaction.getTimestamp()
        );
        assertFalse(transaction.equals(other));

        // different timestamp -> returns false
        other = new TransactionRecord(
                transaction.getOrderItems(),
                transaction.getId(),
                timestamp2
        );
        assertFalse(transaction.equals(other));
    }

}
