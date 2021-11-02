package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

public class RatingTest {

    @Test
    public void constructor_noArguments_emptyRating() {
        assertDoesNotThrow((ThrowingSupplier<Rating>) Rating::new);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rating(null));
    }

    @Test
    public void constructor_invalidRating_throwsIllegalArgumentException() {
        String invalidRating = "0.5";
        assertThrows(IllegalArgumentException.class, () -> new Rating(invalidRating));
    }

    @Test
    public void isValidRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isValidRating(null));

        // invalid ratings
        assertFalse(Rating.isValidRating(" ")); // spaces only
        assertFalse(Rating.isValidRating("31")); // more than 2 digits
        assertFalse(Rating.isValidRating("r")); // non-numeric
        assertFalse(Rating.isValidRating("0")); // out of range
        assertFalse(Rating.isValidRating("6")); // out of range
        assertFalse(Rating.isValidRating("2.1")); // not integer
        assertFalse(Rating.isValidRating("3.0")); // non-integer

        // valid ratings
        assertTrue(Rating.isValidRating("1")); // exactly 1 number, minimum of 1
        assertTrue(Rating.isValidRating("5")); // exactly 1 number, maximum of 5
    }

    @Test
    public void isEmptyRating() {
        // null rating
        assertThrows(NullPointerException.class, () -> Rating.isEmptyRating(null));

        // invalid ratings
        assertFalse(Rating.isEmptyRating(" ")); // whitespace only
        assertFalse(Rating.isEmptyRating("")); // empty string
        assertFalse(Rating.isEmptyRating("0")); // out of range
        assertFalse(Rating.isEmptyRating("no rating")); // non-integer

        // valid ratings
        assertTrue(Rating.isEmptyRating(Rating.EMPTY_RATING));
    }
}
