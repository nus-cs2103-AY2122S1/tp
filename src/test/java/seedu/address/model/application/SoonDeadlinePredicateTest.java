package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.testutil.ApplicationBuilder;

public class SoonDeadlinePredicateTest {

    @Test
    public void equals() {
        SoonDeadlinePredicate firstPredicate = new SoonDeadlinePredicate(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

    }

    @Test
    public void test_soonDeadline_returnsTrue() {
        // Soon deadline
        SoonDeadlinePredicate predicate = new SoonDeadlinePredicate(Index.fromZeroBased(1));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String date = formatter.format(currentDate);
        assertTrue(predicate.test(new ApplicationBuilder().withDeadline(date).build()));
    }

    @Test
    public void test_notSoonDeadline_returnsFalse() {
        SoonDeadlinePredicate predicate = new SoonDeadlinePredicate(Index.fromZeroBased(1));

        // Far deadline
        String farDeadline = "2030-12-10";
        assertFalse(predicate.test(new ApplicationBuilder().withDeadline(farDeadline).build()));

        // Past deadline
        String pastDeadline = "2000-12-10";
        assertFalse(predicate.test(new ApplicationBuilder().withDeadline(pastDeadline).build()));

    }
}
