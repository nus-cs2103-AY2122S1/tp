package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalOrders.getTypicalOrder;
import static seedu.address.testutil.TypicalOrders.getTypicalTransaction;
import static seedu.address.testutil.TypicalTransactions.getTypicalTransactionList;
import static seedu.address.testutil.TypicalTransactions.getTypicalTransactionList2;
import static seedu.address.testutil.TypicalTransactions.getTypicalTransactionList3;

import org.junit.jupiter.api.Test;
import seedu.address.model.order.TransactionRecord;

public class TransactionListTest {

    TransactionList transactionList1 = getTypicalTransactionList();
    TransactionList transactionList2 = getTypicalTransactionList2();
    TransactionList transactionList3 = getTypicalTransactionList3();
    TransactionList transactionList1Copy = getTypicalTransactionList();

    @Test
    public void equalSameObject() {
        assertTrue(transactionList1.equals(transactionList1));
    }

    @Test
    public void unequalNonTransaction() {
        assertFalse(transactionList1.equals(1));
    }

    @Test
    public void equalTestTransactionList() {
        assertTrue(transactionList1.equalTestsTransactionLists(transactionList1));
    }

    @Test
    public void unequalTestTransactionList_NonTransaction() {
        assertFalse(transactionList1.equalTestsTransactionLists(1));
    }

    @Test
    public void unequalTestTransactionList_diffSize() {
        assertFalse(transactionList1.equalTestsTransactionLists(transactionList2));
    }

    @Test
    public void unequalTestTransactionList_diffOrder() {
        assertFalse(transactionList2.equalTestsTransactionLists(transactionList3));
    }

    @Test
    public void equalTestTransactionList_diffObject() {
        assertTrue(transactionList1.equalTestsTransactionLists(transactionList1Copy));
    }

}
