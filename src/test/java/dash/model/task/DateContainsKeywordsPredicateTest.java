package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.testutil.TaskBuilder;

public class DateContainsKeywordsPredicateTest {
    public void equals() {
        TaskDate firstPredicateKeyword = new TaskDate("14-10-2021", false);
        TaskDate secondPredicateKeyword = new TaskDate("1900", false);

        DateContainsKeywordsPredicate firstPredicate = new DateContainsKeywordsPredicate(
                firstPredicateKeyword);
        DateContainsKeywordsPredicate secondPredicate = new DateContainsKeywordsPredicate(
                secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DateContainsKeywordsPredicate firstPredicateCopy = new DateContainsKeywordsPredicate(
                firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different taskDate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // dd/MM/yyyy
        TaskDate sampleDate01 = new TaskDate("14/10/2021", false);
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(sampleDate01);
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("14/10/2021").build()));

        // dd-MM-yyyy
        TaskDate sampleDate02 = new TaskDate("14-10-2021", false);
        predicate = new DateContainsKeywordsPredicate(sampleDate02);
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("14/10/2021").build()));

        // yyyy/MM/dd
        TaskDate sampleDate03 = new TaskDate("2021/10/14", false);
        predicate = new DateContainsKeywordsPredicate(sampleDate03);
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("14/10/2021").build()));

        // yyyy-MM-dd
        TaskDate sampleDate04 = new TaskDate("2021-10-14", false);
        predicate = new DateContainsKeywordsPredicate(sampleDate04);
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("14/10/2021").build()));

        // dd MMM yyyy
        TaskDate sampleDate05 = new TaskDate("14 Oct 2021", false);
        predicate = new DateContainsKeywordsPredicate(sampleDate05);
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("14/10/2021").build()));

        // HHmm
        TaskDate sampleTime01 = new TaskDate("1900", false);
        predicate = new DateContainsKeywordsPredicate(sampleTime01);
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("07:00 PM").build()));

        // hh:mm a
        TaskDate sampleTime02 = new TaskDate("07:00 PM", false);
        predicate = new DateContainsKeywordsPredicate(sampleTime02);
        assertTrue(predicate.test(new TaskBuilder().withTaskDate("1900").build()));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // Different date, same time
        TaskDate sampleDate01 = new TaskDate("15/10/2021", false);
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(sampleDate01);
        assertFalse(predicate.test(new TaskBuilder().withTaskDate("14/10/2021").build()));

        // Different time, different date
        TaskDate sampleTime01 = new TaskDate("14/10/2021, 1500", false);
        predicate = new DateContainsKeywordsPredicate(sampleTime01);
        assertFalse(predicate.test(new TaskBuilder().withTaskDate("15/10/2021, 1900").build()));

    }
}
