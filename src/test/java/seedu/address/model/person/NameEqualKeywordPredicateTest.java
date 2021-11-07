package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class NameEqualKeywordPredicateTest {
    @Test
    public void equals() {
        String firstKeyword = "keyword";
        String secondKeyword = "Keyword";
        String thirdKeyword = "keyword ";

        NameEqualKeywordPredicate firstPredicate = new NameEqualKeywordPredicate(new Name(firstKeyword));
        NameEqualKeywordPredicate secondPredicate = new NameEqualKeywordPredicate(new Name(secondKeyword));
        NameEqualKeywordPredicate thirdPredicate = new NameEqualKeywordPredicate(new Name(thirdKeyword));

        // equivalence partitions testing
        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameEqualKeywordPredicate firstPredicateCopy = new NameEqualKeywordPredicate(new Name(firstKeyword));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // extra spacing -> returns false
        assertFalse(thirdPredicate.equals(firstPredicate));
    }

    @Test
    public void test_nameMatchKeyword_returnsTrue() {
        // exact 1 word match
        NameEqualKeywordPredicate predicate = new NameEqualKeywordPredicate(new Name("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // exact 2 word match
        predicate = new NameEqualKeywordPredicate(new Name("Alice Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotMatchKeyword_returnsFalse() {
        // non-matching keyword
        NameEqualKeywordPredicate predicate = new NameEqualKeywordPredicate(new Name("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // partial match
        predicate = new NameEqualKeywordPredicate(new Name("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Caroline").build()));

        // partial match 2 words
        predicate = new NameEqualKeywordPredicate(new Name("Bob"));
        assertFalse(predicate.test(new PersonBuilder().withName("Bob boy").build()));

        // predicate with more words than person
        predicate = new NameEqualKeywordPredicate(new Name("Bob the builder"));
        assertFalse(predicate.test(new PersonBuilder().withName("Bob").build()));
    }
}
