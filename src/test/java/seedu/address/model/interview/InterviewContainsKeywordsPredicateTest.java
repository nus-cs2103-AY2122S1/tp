package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class InterviewContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        InterviewContainsKeywordsPredicate firstPredicate =
                new InterviewContainsKeywordsPredicate(firstPredicateKeywordList);
        InterviewContainsKeywordsPredicate secondPredicate =
                new InterviewContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InterviewContainsKeywordsPredicate firstPredicateCopy =
                new InterviewContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_interviewContainsKeywords_returnsTrue() {
        // One keyword
        InterviewContainsKeywordsPredicate predicate =
                new InterviewContainsKeywordsPredicate(Collections.singletonList("2021"));
        assertTrue(predicate.test(new PersonBuilder().withInterview("2021-10-10, 10:00").build()));

        // Multiple keywords
        predicate = new InterviewContainsKeywordsPredicate(Arrays.asList("2021", "Mar"));
        assertTrue(predicate.test(new PersonBuilder().withInterview("2021-03-21, 20:00").build()));

        // Only one matching keyword
        predicate = new InterviewContainsKeywordsPredicate(Arrays.asList("2021", "Mar"));
        assertTrue(predicate.test(new PersonBuilder().withInterview("2021-04-21, 10:00").build()));

        // Mixed-case keywords
        predicate = new InterviewContainsKeywordsPredicate(Arrays.asList("maR"));
        assertTrue(predicate.test(new PersonBuilder().withInterview("2021-03-21, 10:00").build()));
    }

    @Test
    public void test_interviewDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        InterviewContainsKeywordsPredicate predicate =
                new InterviewContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withInterview("2021-10-10, 10:00").build()));

        // Non-matching keyword
        predicate = new InterviewContainsKeywordsPredicate(Arrays.asList("2022"));
        assertFalse(predicate.test(new PersonBuilder().withInterview("2021-10-31, 10:00").build()));

        // Keywords match name phone, email, applied role, employment type, expected salary,
        // level of education, years of experience, and tag, but does not match interview
        predicate = new InterviewContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com",
                "Engineer", "Temporary", "4000", "PhD", "5", "old"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withRole("Engineer").withEmploymentType("Temporary")
                .withExpectedSalary("4000").withLevelOfEducation("PhD").withExperience("5")
                .withInterview("2021-10-30, 10:00").build()));
    }

}
