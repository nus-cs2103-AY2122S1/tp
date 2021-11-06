package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTransactions.getTypicalTransactionList;
import static seedu.address.testutil.TypicalTransactions.getTypicalTransactionList2;
import static seedu.address.testutil.TypicalTransactions.getTypicalTransactionList3;

import org.junit.jupiter.api.Test;

public class TransactionListTest {

    private TransactionList transactionList1 = getTypicalTransactionList();
    private TransactionList transactionList2 = getTypicalTransactionList2();
    private TransactionList transactionList3 = getTypicalTransactionList3();
    private TransactionList transactionList1Copy = getTypicalTransactionList();

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
    public void unequalTestTransactionList_nonTransaction() {
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
