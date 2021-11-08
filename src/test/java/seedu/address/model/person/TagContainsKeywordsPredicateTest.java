package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    public static final String TAG_TYPE = "t/";
    public static final String FULL_TAG_TYPE = "t/full";

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList, TAG_TYPE);
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(secondPredicateKeywordList, TAG_TYPE);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList, TAG_TYPE);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // Full Tag
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Arrays.asList("t/", "friends"), TAG_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Partial tag
        predicate =
                new TagContainsKeywordsPredicate(Arrays.asList("t/", "frie"), TAG_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Full Tag with mixed-case
        predicate =
                new TagContainsKeywordsPredicate(Arrays.asList("t/", "frIends"), TAG_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsTrue() {
        // Non-Matching Tag
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Arrays.asList("t/", "colleagues"), TAG_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));
    }

    @Test
    public void test_tagContainsFullKeywords_returnsTrue() {
        // Full Tag
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Arrays.asList("t/full", "friends"), FULL_TAG_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Full Tag with mixed-case
        predicate =
                new TagContainsKeywordsPredicate(Arrays.asList("t/full", "frIends"), FULL_TAG_TYPE);
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));
    }

    @Test
    public void test_tagDoesNotContainFullKeywords_returnsTrue() {
        // Non-Matching Tag
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Arrays.asList("t/full", "colleagues"), FULL_TAG_TYPE);
        assertFalse(predicate.test(new PersonBuilder().withTags("family").build()));
    }
}
