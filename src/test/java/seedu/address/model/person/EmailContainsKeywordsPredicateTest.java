package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy = new EmailContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(
                Collections.singletonList("test@123.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("test@123.com").build()));

        // Multiple keywords with one matching email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("test1@email.com", "test2@email.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("test1@email.com").build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("tEst1@emaIl.com", "TESt2@email.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("test1@email.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("test1@email.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("test2@email.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("test1@email.com").build()));


        // Keywords match all other prefixes, but does not match email
        predicate = new EmailContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com",
                        "Programmer", "Full", "time", "3000", "High", "School", "3", "friend"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("bob@email.com").withRole("Cat Whisperer")
                .withEmploymentType("Full time").withExpectedSalary("3000")
                .withLevelOfEducation("High School").withExperience("3")
                .build()));
    }
}
