package seedu.address.model.done;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class DoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DoneContainsKeywordsPredicate firstPredicate = new DoneContainsKeywordsPredicate(firstPredicateKeywordList);
        DoneContainsKeywordsPredicate secondPredicate = new DoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DoneContainsKeywordsPredicate firstPredicateCopy = new DoneContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_doneContainsOnlyMatchingKeywords_returnsTrue() {
        // Matching Done
        DoneContainsKeywordsPredicate predicate =
                new DoneContainsKeywordsPredicate(Collections.singletonList("Done"));
        assertTrue(predicate.test(new PersonBuilder().withDone("Done").build()));

        // Matching Not Done
        predicate = new DoneContainsKeywordsPredicate(Collections.singletonList("Not Done"));
        assertTrue(predicate.test(new PersonBuilder().withRole("Not Done").build()));

    }

    @Test
    public void test_doneContainsMismatchKeywords_returnsFalse() {
        // One mismatching keyword against single term done
        DoneContainsKeywordsPredicate predicate =
                new DoneContainsKeywordsPredicate(Collections.singletonList("Don"));
        assertFalse(predicate.test(new PersonBuilder().withDone("Done").build()));

        // One mismatching keyword against multiple term done
        predicate = new DoneContainsKeywordsPredicate(Collections.singletonList("Don"));
        assertFalse(predicate.test(new PersonBuilder().withDone("Not Done").build()));

        // Multiple keywords with partial mismatch against similar term done
        predicate = new DoneContainsKeywordsPredicate(Arrays.asList("No", "Done"));
        assertFalse(predicate.test(new PersonBuilder().withDone("Not Done").build()));

        // All mismatch keywords against multiple term done
        predicate = new DoneContainsKeywordsPredicate(Arrays.asList("Wrong", "Stuff"));
        assertFalse(predicate.test(new PersonBuilder().withDone("Not Done").build()));

        // Searching for Done will not match Not Done
        predicate = new DoneContainsKeywordsPredicate(Arrays.asList("Done"));
        assertFalse(predicate.test(new PersonBuilder().withDone("Not Done").build()));
    }

    @Test
    public void test_doneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DoneContainsKeywordsPredicate predicate = new DoneContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withDone("Done").build()));

        // Keywords match all other prefixes, but does not match done
        predicate = new DoneContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com",
                        "Programmer", "Full", "time", "3000", "High", "School", "3", "friend", "Done"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withRole("Programmer")
                .withEmploymentType("Full time").withExpectedSalary("3000")
                .withLevelOfEducation("High School").withExperience("3").withTags("friend")
                .withDone("Not Done").build()));
    }

}
