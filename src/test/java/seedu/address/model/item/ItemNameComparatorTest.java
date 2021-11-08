package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.BAGEL;

import org.junit.jupiter.api.Test;

public class ItemNameComparatorTest {

    @Test
    public void test_compare() {

        // name1 > name2
        assertTrue(new ItemNameComparator().compare(APPLE_PIE, BAGEL) < 0);

        // name1 == name2
        assertTrue(new ItemNameComparator().compare(APPLE_PIE, APPLE_PIE) == 0);

        // name1 < name2
        assertTrue(new ItemNameComparator().compare(BAGEL, APPLE_PIE) > 0);;
    }

}
