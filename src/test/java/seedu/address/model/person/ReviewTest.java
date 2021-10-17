package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ReviewTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Review(null));
    }

    @Test
    public void execute_isValidReviewEmptyReview_success() {
        assertTrue(Review.isValidReview(Review.EMPTY_REVIEW));
    }

    @Test
    public void execute_isEmptyReview_success() {
        Review review = new Review(Review.EMPTY_REVIEW);
        assertTrue(review.isEmptyReview());
    }
}
