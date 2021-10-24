package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;
import seedu.address.testutil.TypicalItems;

public class TransactionRecordTest {
    @Test
    public void constructor_nullUniqueItemList_throwException() {
        UniqueItemList currList = null;
        assertThrows(NullPointerException.class, () -> new TransactionRecord(currList));
    }

    @Test
    public void constructor_nullList_throwException() {
        List<Item> currList = null;
        assertThrows(NullPointerException.class, () -> new TransactionRecord(currList));
    }

    @Test
    public void getItems_typicalItems_sameListOfItems() {
        TransactionRecord transaction = new TransactionRecord(TypicalItems.getTypicalItems());
        assertEquals(transaction.getItems(), TypicalItems.getTypicalItems());
    }

    @Test
    public void getItems_differedCountItems_differentListOfItems() {
        List<Item> items = TypicalItems.getTypicalItems();
        List<Item> updatedItems = new ArrayList<>();
        for (Item item : items) {
            updatedItems.add(item.updateCount(item.getCount() + 1));
        }

        TransactionRecord transaction = new TransactionRecord(updatedItems);
        assertNotEquals(transaction.getItems(), TypicalItems.getTypicalItems());
    }

    @Test
    public void equals_sameItems_returnFalse() {
        TransactionRecord transaction1 = new TransactionRecord(TypicalItems.getTypicalItems());
        TransactionRecord transaction2 = new TransactionRecord(TypicalItems.getTypicalItems());

        assertNotEquals(transaction1, transaction2);
    }

}
