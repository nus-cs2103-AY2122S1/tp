package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagsContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = List.of("friend");
        List<String> secondPredicateKeywordList = Arrays.asList("buddy", "cousin");

        TagsContainKeywordsPredicate firstPredicate = new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        TagsContainKeywordsPredicate secondPredicate = new TagsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TagsContainKeywordsPredicate firstPredicateCopy = new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals("first"));

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different names -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        Person samplePerson = new PersonBuilder().withTags("buddy", "cousin").build();
        // One keyword
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(new ArrayList<>(List.of("buddy")));
        assertTrue(predicate.test(samplePerson));

        // Multiple keywords
        predicate = new TagsContainKeywordsPredicate(new ArrayList<>(Arrays.asList("buddy", "cousin")));
        assertTrue(predicate.test(samplePerson));

        // One abbreviated keyword
        predicate = new TagsContainKeywordsPredicate(new ArrayList<>(List.of("bud")));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords
        predicate = new TagsContainKeywordsPredicate(new ArrayList<>(Arrays.asList("bud", "cous")));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords in random case
        predicate = new TagsContainKeywordsPredicate(new ArrayList<>(Arrays.asList("Bud", "couS")));
        assertTrue(predicate.test(samplePerson));
    }

    @Test
    public void test_tagsDoNotContainKeywords_returnsFalse() {
        TagsContainKeywordsPredicate predicate;
        // Non-matching keyword
        predicate = new TagsContainKeywordsPredicate(new ArrayList<>(List.of("boss")));
        assertFalse(predicate.test(new PersonBuilder().withTags("buddy", "cousin").build()));
    }

    @Test
    public void test_keywordsEmpty_throwsException() {
        TagsContainKeywordsPredicate predicate;
        // Empty keyword
        predicate = new TagsContainKeywordsPredicate(new ArrayList<>());
        assertThrows(
                IllegalArgumentException.class, (
                ) -> predicate.test(new PersonBuilder().withTags("buddy", "cousin").build())
        );
    }
}
