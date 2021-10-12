package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.lesson.Price.PRICE_MESSAGE_CONSTRAINT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.LessonBuilder.DEFAULT_PRICE;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_validPrice_returnsPrice() {
        Price otherPrice = new Price(DEFAULT_PRICE.value);
        assertEquals(DEFAULT_PRICE, otherPrice);
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        double invalidPrice = -3.5;
        assertThrows(IllegalArgumentException.class, PRICE_MESSAGE_CONSTRAINT, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // negative price
        assertFalse(Price.isValidPrice(-0.9));
        // valid (non-negative) price
        assertTrue(Price.isValidPrice(0.9));
        // free lesson
        assertTrue(Price.isValidPrice(0));
    }

    @Test
    public void equals() {
        // this -> true
        assertEquals(DEFAULT_PRICE, DEFAULT_PRICE);

        // null -> false
        assertNotEquals(DEFAULT_PRICE, null);

        // different instance -> false
        assertNotEquals(DEFAULT_PRICE, 5);

        // diff value -> false
        Price differentPrice = new Price(3);
        assertNotEquals(DEFAULT_PRICE, differentPrice);

        // same value but diff instance -> true
        Price otherPrice = new Price(DEFAULT_PRICE.value);
        assertEquals(DEFAULT_PRICE, otherPrice);
    }
}
