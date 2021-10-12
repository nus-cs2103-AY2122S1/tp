package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalItems.BAGEL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class ItemCountComparatorTest {

    @Test
    public void test_compare() {

        Item oneItem = new ItemBuilder(BAGEL).withCount("1").build();
        Item twoItems = new ItemBuilder(BAGEL).withCount("2").build();
        // name1 > name2
        assertTrue(new ItemCountComparator().compare(oneItem, twoItems) < 0);

        // name1 == name2
        assertTrue(new ItemCountComparator().compare(twoItems, twoItems) == 0);

        // name1 < name2
        assertTrue(new ItemCountComparator().compare(twoItems, oneItem) > 0);;
    }

}
