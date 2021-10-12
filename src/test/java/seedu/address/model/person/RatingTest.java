package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RatingTest {

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
        assertFalse(Rating.isValidRating("91")); // more than 2 numbers
        assertFalse(Rating.isValidRating("r")); // non-numeric
        assertFalse(Rating.isValidRating("-1")); // out of range
        assertFalse(Rating.isValidRating("6")); // out of range
        assertFalse(Rating.isValidRating("2.5")); // not integer

        // valid ratings
        assertTrue(Rating.isValidRating("1")); // exactly 1 number, minimum of 1
        assertTrue(Rating.isValidRating("5")); // exactly 1 number, maximum of 5
    }
}
