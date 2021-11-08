package seedu.placebook.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.placebook.testutil.PersonBuilder;

public class PersonHasTagsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonHasTagsPredicate firstPredicate = new PersonHasTagsPredicate(firstPredicateKeywordList);
        PersonHasTagsPredicate secondPredicate = new PersonHasTagsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasTagsPredicate firstPredicateCopy = new PersonHasTagsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personContainsTags_returnsTrue() {
        // One keyword
        PersonHasTagsPredicate predicate = new PersonHasTagsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("friends").build()));

        // Multiple keywords
        predicate = new PersonHasTagsPredicate(Arrays.asList("friends", "colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("friends", "colleagues").build()));

        // Only one matching keyword
        predicate = new PersonHasTagsPredicate(Arrays.asList("friends", "family"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("friends").build()));

        // Mixed-case keywords
        predicate = new PersonHasTagsPredicate(Arrays.asList("friENds", "coLleagUes"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("friends", "colleagues").build()));
    }

    @Test
    public void test_personDoesNotContainTags_returnsFalse() {
        // Zero keywords
        PersonHasTagsPredicate predicate = new PersonHasTagsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PersonHasTagsPredicate(Arrays.asList("friends", "colleagues"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("client").build()));

        // Keywords match name, but does not match tags
        predicate = new PersonHasTagsPredicate(Arrays.asList("Alice", "Bob"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friends").build()));
    }
}
