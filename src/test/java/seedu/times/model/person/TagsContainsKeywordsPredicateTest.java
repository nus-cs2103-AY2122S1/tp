package seedu.times.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.times.model.person.predicates.TagsContainsKeywordsPredicate;
import seedu.times.testutil.PersonBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Maths");
        List<String> secondPredicateKeywordList = Arrays.asList("Maths", "Physics");

        TagsContainsKeywordsPredicate firstPredicate = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        TagsContainsKeywordsPredicate secondPredicate = new TagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate which has different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsMatchesKeywords_returnsTrue() {
        // One tag matches with one keyword
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Collections.singletonList("Maths"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Maths").build()));

        // Multiple tags match with multiple keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Maths", "Physics"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Maths", "Physics").build()));

        // Only one tag matches with one keyword among many keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Maths", "Physics"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Maths").build()));

        // Mixed-case tag matches with Mixed-case keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("pHySics"));
        assertTrue(predicate.test(new PersonBuilder().withTags("PhYsICS").build()));
    }

}
