package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.testutil.ApplicationBuilder;

public class InterviewDateAndTimePredicateTest {

    @Test
    public void equals() {
        InterviewDateAndTimePredicate firstPredicate = new InterviewDateAndTimePredicate(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // other InterviewDateAndTimePredicate -> returns true
        assertTrue(firstPredicate.equals(new InterviewDateAndTimePredicate(Index.fromZeroBased(2))));
    }

    @Test
    public void test_interviewDateAndTime_returnsTrue() {
        // Soon InterviewDateAndTime
        InterviewDateAndTimePredicate predicate = new InterviewDateAndTimePredicate(Index.fromZeroBased(1));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmm");
        Date currentDate = new Date();
        String testDate = formatter.format(currentDate);
        assertTrue(predicate.test(new ApplicationBuilder().withInterviewDateAndTime(testDate).build()));
    }

    @Test
    public void test_notInterviewDateAndTime_returnsFalse() {
        InterviewDateAndTimePredicate predicate = new InterviewDateAndTimePredicate(Index.fromZeroBased(1));

        // Far InterviewDateAndTime
        String farInterviewDateAndTime = "2030-12-10 1000";
        assertFalse(predicate.test(new ApplicationBuilder().withInterviewDateAndTime(farInterviewDateAndTime).build()));

        // Past InterviewDateAndTime
        String pastInterviewDateAndTime = "2000-12-10 1000";
        assertFalse(predicate.test(new ApplicationBuilder()
                .withInterviewDateAndTime(pastInterviewDateAndTime).build()));
    }
}
