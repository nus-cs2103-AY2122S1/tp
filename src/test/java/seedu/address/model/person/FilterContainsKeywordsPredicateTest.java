package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;
import seedu.address.testutil.StudentBuilder;


public class FilterContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StudentInvolvementContainsKeywordsPredicate firstPredicate =
                new StudentInvolvementContainsKeywordsPredicate(firstPredicateKeywordList);
        StudentInvolvementContainsKeywordsPredicate secondPredicate =
                new StudentInvolvementContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentInvolvementContainsKeywordsPredicate firstPredicateCopy =
                new StudentInvolvementContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_involvementTag() {
        List<String> predicateKeywordList = Arrays.asList("first", "t/second");
        StudentInvolvementContainsKeywordsPredicate predicate =
                new StudentInvolvementContainsKeywordsPredicate(predicateKeywordList);
        assertFalse(predicate.test(new StudentBuilder().withTags("second").build()));

        assertFalse(predicate.test(new StudentBuilder().withInvolvement("first").build()));

        assertTrue(predicate.test(new StudentBuilder().withInvolvement("first").withTags("second").build()));

        List<String> secondPredicateKeywordList = Arrays.asList("t/first", "second");

        StudentInvolvementContainsKeywordsPredicate predicate2 =
                new StudentInvolvementContainsKeywordsPredicate(secondPredicateKeywordList);

        assertTrue(predicate2.test(new StudentBuilder().withTags("first", "second").build()));

        assertFalse(predicate2.test(new StudentBuilder().withTags("first").build()));

        assertFalse(predicate2.test(new StudentBuilder().withInvolvement("first").build()));

        List<String> thirdPredicateKeywordList = Arrays.asList("first", "second");

        StudentInvolvementContainsKeywordsPredicate predicate3 =
                new StudentInvolvementContainsKeywordsPredicate(thirdPredicateKeywordList);

        assertTrue(predicate3.test(new StudentBuilder().withInvolvement("firstsecond").build()));

        assertFalse(predicate3.test(new StudentBuilder().withInvolvement("first").build()));

        assertFalse(predicate3.test(new StudentBuilder().withTags("firstsecond").build()));

        assertFalse(predicate3.test(new StudentBuilder().withTags("first", "second").build()));
    }
}
