package seedu.address.model.module.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentIdEqualsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "A1234567A";
        String secondPredicateKeyword = "A7654321A";

        StudentIdEqualsKeywordPredicate firstPredicate = new StudentIdEqualsKeywordPredicate(firstPredicateKeyword);
        StudentIdEqualsKeywordPredicate secondPredicate = new StudentIdEqualsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentIdEqualsKeywordPredicate firstPredicateCopy = new StudentIdEqualsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different id -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentIdEqualsKeywords_returnsTrue() {
        // Correct keyword
        StudentIdEqualsKeywordPredicate predicate = new StudentIdEqualsKeywordPredicate("A1234567A");
        assertTrue(predicate.test(new StudentBuilder().withStudentId("A1234567A").build()));
    }

    @Test
    public void test_studentIdDoesNotEqualKeywords_returnsFalse() {
        // No keywords
        StudentIdEqualsKeywordPredicate predicate = new StudentIdEqualsKeywordPredicate("");
        assertFalse(predicate.test(new StudentBuilder().withStudentId("A1234567A").build()));

        // Non-matching keyword
        predicate = new StudentIdEqualsKeywordPredicate("A2345671A");
        assertFalse(predicate.test(new StudentBuilder().withStudentId("A1234567A").build()));
    }
}
