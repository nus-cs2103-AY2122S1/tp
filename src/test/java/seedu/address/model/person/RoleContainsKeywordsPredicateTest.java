package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class RoleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RoleContainsKeywordsPredicate firstPredicate = new RoleContainsKeywordsPredicate(firstPredicateKeywordList);
        RoleContainsKeywordsPredicate secondPredicate = new RoleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RoleContainsKeywordsPredicate firstPredicateCopy = new RoleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_roleContainsKeywords_returnsTrue() {
        // One keyword
        RoleContainsKeywordsPredicate predicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("Engineer"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Software Engineer").build()));

        // Multiple matching keywords
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Store", "Assistant"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Store Assistant").build()));

        // Only one matching keyword
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Store", "Manager"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Store Assistant").build()));

        // mixed-case keywords
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("stORe", "aSSistant"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Store Assistant").build()));
    }

    @Test
    public void test_roleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RoleContainsKeywordsPredicate predicate = new RoleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withRole("Cook").build()));

        // Non-matching keyword
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Manager"));
        assertFalse(predicate.test(new PersonBuilder().withRole("Dog Whisperer").build()));

        // Keywords match all other prefixes, but does not match role
        predicate = new RoleContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com",
                        "Programmer", "Full", "time", "3000", "High", "School", "3", "friend"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withRole("Cat Whisperer")
                .withEmploymentType("Full time").withExpectedSalary("3000")
                .withLevelOfEducation("High School").withExperience("3").withTags("friend")
                .build()));
    }

}
