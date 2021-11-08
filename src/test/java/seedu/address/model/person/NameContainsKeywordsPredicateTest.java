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

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = List.of("first");
        List<String> secondPredicateKeywordList = List.of("second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals("first"));

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different names -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        Person samplePerson = new PersonBuilder().withName("John William Cena").build();
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(new ArrayList<>(List.of("John")));
        assertTrue(predicate.test(samplePerson));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(new ArrayList<>(Arrays.asList("John", "Cena")));
        assertTrue(predicate.test(samplePerson));

        // One abbreviated keyword
        predicate = new NameContainsKeywordsPredicate(new ArrayList<>(List.of("Will")));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords
        predicate = new NameContainsKeywordsPredicate(new ArrayList<>(Arrays.asList("Jo", "Will")));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords in random case
        predicate = new NameContainsKeywordsPredicate(new ArrayList<>(Arrays.asList("jO", "WIlL")));
        assertTrue(predicate.test(samplePerson));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        NameContainsKeywordsPredicate predicate;
        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(new ArrayList<>(List.of("Carol")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_keywordsEmpty_throwsException() {
        NameContainsKeywordsPredicate predicate;
        // Empty keyword
        predicate = new NameContainsKeywordsPredicate(new ArrayList<>());
        assertThrows(
                IllegalArgumentException.class, (
                ) -> predicate.test(new PersonBuilder().withName("Alice Bob").build())
        );
    }
}
