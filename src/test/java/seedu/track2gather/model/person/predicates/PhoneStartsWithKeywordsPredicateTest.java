package seedu.track2gather.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.track2gather.testutil.PersonBuilder;

public class PhoneStartsWithKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("11111111");
        List<String> secondPredicateKeywordList = Arrays.asList("11111111", "22222222");

        PhoneStartsWithKeywordsPredicate firstPredicate =
                new PhoneStartsWithKeywordsPredicate(firstPredicateKeywordList);
        PhoneStartsWithKeywordsPredicate secondPredicate =
                new PhoneStartsWithKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneStartsWithKeywordsPredicate firstPredicateCopy =
                new PhoneStartsWithKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(11111111));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneStartsWithKeywords_returnsTrue() {
        // One keyword
        PhoneStartsWithKeywordsPredicate predicate =
                new PhoneStartsWithKeywordsPredicate(Collections.singletonList("1234"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("12345678").build()));

        // Multiple keywords
        predicate = new PhoneStartsWithKeywordsPredicate(Arrays.asList("123", "1234"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("12345678").build()));

        // Only one matching keyword
        predicate = new PhoneStartsWithKeywordsPredicate(Arrays.asList("123", "666"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("12345678").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PhoneStartsWithKeywordsPredicate predicate = new PhoneStartsWithKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("12345678").build()));

        // Non-matching keyword
        predicate = new PhoneStartsWithKeywordsPredicate(Arrays.asList("111"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("12345678").build()));

        // Phone number contains keyword but does not start with keyword
        predicate = new PhoneStartsWithKeywordsPredicate(Arrays.asList("234"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("12345678").build()));

        // Keywords match name, email, case number, address, shn period's start date and shn period's end date, but
        // does not match phone
        predicate = new PhoneStartsWithKeywordsPredicate(Arrays.asList("Alice", "alice@email.com", "11111", "Main",
                "Street", "2000-01-01", "2000-01-02"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@email.com").withCaseNumber("601110").withHomeAddress("Main Street")
                .withShnPeriod("2000-01-01 => 2000-01-02").build()));
    }
}
