package seedu.programmer.model.student.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.programmer.testutil.TypicalClasses.CLASS_1;
import static seedu.programmer.testutil.TypicalClasses.CLASS_2;
import static seedu.programmer.testutil.TypicalClasses.CLASS_3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class SortClassIdTest {

    private static SortClassId sorter;

    @BeforeAll
    public static void setup() {
        sorter = new SortClassId();
    }

    @Test
    public void compare_sameLabs_returnZero() {
        assertEquals(0, sorter.compare(CLASS_1, CLASS_1));
    }

    @Test
    public void compare_labOneAndLabTwo_returnsNegative() {
        assertEquals(-1, sorter.compare(CLASS_1, CLASS_2));
    }

    @Test
    public void compare_labTwoAndLabThree_returnsPositive() {
        assertEquals(1, sorter.compare(CLASS_3, CLASS_2));
    }
}
