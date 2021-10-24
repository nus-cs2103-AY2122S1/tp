package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PhoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = List.of("98786451");
        List<String> secondPredicateKeywordList = List.of("97512727");

        PhoneContainsKeywordsPredicate firstPredicate = new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        PhoneContainsKeywordsPredicate secondPredicate = new PhoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PhoneContainsKeywordsPredicate firstPredicateCopy =
                new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals("first"));

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different phones -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        Person samplePerson = new PersonBuilder().withPhone("97857575").build();
        // One keyword
        PhoneContainsKeywordsPredicate predicate =
                new PhoneContainsKeywordsPredicate(new ArrayList<>(List.of("97857575")));
        assertTrue(predicate.test(samplePerson));

        // One abbreviated keyword
        predicate = new PhoneContainsKeywordsPredicate(new ArrayList<>(List.of("9785")));
        assertTrue(predicate.test(samplePerson));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        PhoneContainsKeywordsPredicate predicate;
        // Non-matching keyword
        predicate = new PhoneContainsKeywordsPredicate(new ArrayList<>(List.of("97857575")));
        assertFalse(predicate.test(new PersonBuilder().withPhone("97454542").build()));
    }

    @Test
    public void test_keywordsEmpty_throwsException() {
        PhoneContainsKeywordsPredicate predicate;
        // Empty keyword
        predicate = new PhoneContainsKeywordsPredicate(new ArrayList<>());
        assertThrows(
                IllegalArgumentException.class, (
                ) -> predicate.test(new PersonBuilder().withPhone("85757857").build())
        );
    }
}
