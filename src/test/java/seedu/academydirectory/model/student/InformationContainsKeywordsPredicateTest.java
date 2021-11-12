package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.testutil.StudentBuilder;

public class InformationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("mission");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        InformationContainsKeywordsPredicate firstPredicate =
                new InformationContainsKeywordsPredicate(firstPredicateKeywordList);
        InformationContainsKeywordsPredicate secondPredicate =
                new InformationContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InformationContainsKeywordsPredicate firstPredicateCopy =
                new InformationContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameOrTagContainsKeywords_returnsTrue() {
        // One keyword matching the tag
        InformationContainsKeywordsPredicate predicate =
                new InformationContainsKeywordsPredicate(Collections.singletonList("Mission"));
        assertTrue(predicate.test(new StudentBuilder().withTags("Mission").build()));

        // One keyword matching the name
        predicate = new InformationContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").withTags("Mission").build()));

        // Multiple keywords matching the name and tag
        predicate = new InformationContainsKeywordsPredicate(Arrays.asList("mission", "Alice"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").withTags("mission").build()));

        // Only one matching keyword
        predicate = new InformationContainsKeywordsPredicate(Arrays.asList("mission", "stream"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").withTags("mission").build()));

        // Mixed-case keywords
        predicate = new InformationContainsKeywordsPredicate(Arrays.asList("miSsIon", "Bob"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").withTags("mission", "stream").build()));
    }

    @Test
    public void test_nameOrTagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        InformationContainsKeywordsPredicate predicate =
                new InformationContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withTags("mission").build()));

        // Non-matching keyword
        predicate = new InformationContainsKeywordsPredicate(Arrays.asList("mission"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withTags("envModel").build()));

        // Keywords match phone, email, and telegram but does not match name or tag
        predicate = new InformationContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "@alice",
                "Main", "Street"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withTags("mission").withPhone("12345")
                .withEmail("alice@email.com").withTelegram("@alice").build()));
    }
}
