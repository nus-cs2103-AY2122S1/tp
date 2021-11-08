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

public class DescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Arrays.asList("is", "cousin");
        List<String> secondPredicateKeywordList = Arrays.asList("has", "cousin");

        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        AttributeContainsKeywordsPredicate otherPredicate = new AddressContainsKeywordsPredicate(List.of("asdas"));
        assertNotEquals(firstPredicate, otherPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different descriptions -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        Person samplePerson = new PersonBuilder().withDescription("very fantastic wow").build();
        // One keyword
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("wow"));
        assertTrue(predicate.test(samplePerson));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("very", "wow"));
        assertTrue(predicate.test(samplePerson));

        // One abbreviated keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("fan"));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("fan", "wo"));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords in random case
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("fAn", "WoW"));
        assertTrue(predicate.test(samplePerson));
    }

    @Test
    public void test_tagsDoNotContainKeywords_returnsFalse() {
        DescriptionContainsKeywordsPredicate predicate;
        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("blue"));
        assertFalse(predicate.test(new PersonBuilder().withDescription("orange").build()));
    }

    @Test
    public void test_keywordsEmpty_throwsException() {
        DescriptionContainsKeywordsPredicate predicate;
        // Empty keyword
        predicate = new DescriptionContainsKeywordsPredicate(new ArrayList<>());
        assertThrows(
                IllegalArgumentException.class, (
                ) -> predicate.test(new PersonBuilder().withDescription("red").build())
        );
    }
}
