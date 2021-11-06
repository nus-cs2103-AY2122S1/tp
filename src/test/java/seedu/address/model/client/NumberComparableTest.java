package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.client.SortDirection.SORT_ASCENDING;
import static seedu.address.model.client.SortDirection.SORT_DESCENDING;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class NumberComparableTest {
    private static class NumberComparableStub extends NumberComparable<NumberComparableStub> {
        private final String value;
        public NumberComparableStub(Double d) {
            this.value = Optional.ofNullable(d).map(x -> Double.toString(x)).orElse("");
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    private final NumberComparableStub s1 = new NumberComparableStub(-55.5);
    private final NumberComparableStub s2 = new NumberComparableStub(100.56);
    private final NumberComparableStub s3 = new NumberComparableStub(null);

    @Test
    public void compareWithDirection_returnNegative() {
        // both non-null
        assertTrue(s1.compareWithDirection(s2, SORT_ASCENDING) < 0);
        assertTrue(s1.compareTo(s2) < 0);

        // both one-null
        assertTrue(s1.compareWithDirection(s3, SORT_ASCENDING) < 0);
        assertTrue(s1.compareTo(s3) < 0);

        // compare descending
        assertTrue(s2.compareWithDirection(s1, SORT_DESCENDING) < 0);
    }

    @Test
    public void compareWithDirection_returnPositive() {
        // both non-null
        assertTrue(s2.compareWithDirection(s1, SORT_ASCENDING) > 0);
        assertTrue(s2.compareTo(s1) > 0);

        // both one-null
        assertTrue(s3.compareWithDirection(s1, SORT_ASCENDING) > 0);
        assertTrue(s3.compareTo(s1) > 0);

        // compare descending
        assertTrue(s1.compareWithDirection(s2, SORT_DESCENDING) > 0);
    }

    @Test
    public void compareWithDirection_returnZero() {
        // both non-null, same object
        assertEquals(0, s1.compareWithDirection(s1, SORT_ASCENDING));
        assertEquals(0, s1.compareWithDirection(s1, SORT_DESCENDING));
        assertEquals(0, s1.compareTo(s1));

        // both non-null, different object
        NumberComparableStub d1 = new NumberComparableStub(Double.parseDouble(s1.toString()));
        assertEquals(0, s1.compareWithDirection(d1, SORT_ASCENDING));
        assertEquals(0, s1.compareWithDirection(d1, SORT_DESCENDING));
        assertEquals(0, s1.compareTo(d1));

        // both null
        assertEquals(0, s3.compareWithDirection(s3, SORT_ASCENDING));
        assertEquals(0, s3.compareWithDirection(s3, SORT_DESCENDING));
        assertEquals(0, s3.compareTo(s3));
    }
}
