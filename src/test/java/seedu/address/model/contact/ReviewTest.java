package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ReviewTest {

    private static final String EXACTLY_499_CHARS_REVIEW =
            "testingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingte"
            + "testingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingte"
            + "testingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingte"
            + "testingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingte"
            + "testingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingtestingt";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Review(null));
    }

    @Test
    public void execute_isValidReviewEmptyReview_success() {
        assertTrue(Review.isValidReview(Review.EMPTY_REVIEW));
    }

    @Test
    public void execute_isValidReview_success() {
        assertTrue(Review.isValidReview(Review.EMPTY_REVIEW)); // All empty reviews are valid
        assertTrue(Review.isValidReview(" ")); // whitespace
        assertTrue(Review.isValidReview("a")); // one char

        assertFalse(Review.isValidReview(EXACTLY_499_CHARS_REVIEW + "ab")); // 501 chars
        assertTrue(Review.isValidReview(EXACTLY_499_CHARS_REVIEW + 'a')); // 500 chars
        assertTrue(Review.isValidReview(EXACTLY_499_CHARS_REVIEW)); // 499 chars
        assertTrue(Review.isValidReview("!@#@!$@!%$$%&^&(*)_+:<>?")); // special characters

    }

    @Test
    public void execute_isEmptyReview_success() {
        Review review = new Review(Review.EMPTY_REVIEW);
        assertTrue(review.isEmptyReview());
    }
}
