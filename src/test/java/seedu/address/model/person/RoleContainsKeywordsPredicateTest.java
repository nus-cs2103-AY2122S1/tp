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
    public void test_roleContainsOnlyMatchingKeywords_returnsTrue() {
        // One matching keyword against single term role
        RoleContainsKeywordsPredicate predicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("Engineer"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Engineer").build()));

        // One matching keyword against multiple term role
        predicate = new RoleContainsKeywordsPredicate(Collections.singletonList("Store"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Store Assistant").build()));

        // All matching keywords against similar terms role
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Store", "Assistant"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Store Assistant").build()));

        // All matching keywords against role with more terms
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Store", "Assistant"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Senior Store Assistant").build()));

        // mixed-case and all matching keywords against similar terms role
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("stORe", "aSSistant"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Store Assistant").build()));
    }

    @Test
    public void test_roleContainsMismatchKeywords_returnsFalse() {
        // One mismatching keyword against single term role
        RoleContainsKeywordsPredicate predicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("Cleaner"));
        assertFalse(predicate.test(new PersonBuilder().withRole("Cook").build()));

        // One mismatching keyword against multiple term role
        predicate = new RoleContainsKeywordsPredicate(Collections.singletonList("Cleaner"));
        assertFalse(predicate.test(new PersonBuilder().withRole("Senior Cook").build()));

        // Multiple keywords with partial mismatch against similar term role
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Store", "Manager"));
        assertFalse(predicate.test(new PersonBuilder().withRole("Store Assistant").build()));

        // Multiple keywords with partial mismatch against role with more terms
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Store", "Manager"));
        assertFalse(predicate.test(new PersonBuilder().withRole("Senior Store Assistant").build()));

        // All mismatch keywords against multiple term role
        predicate = new RoleContainsKeywordsPredicate(Arrays.asList("Restaurant", "Manager"));
        assertFalse(predicate.test(new PersonBuilder().withRole("Senior Store Assistant").build()));
    }

    @Test
    public void test_roleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RoleContainsKeywordsPredicate predicate = new RoleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withRole("Cook").build()));

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
