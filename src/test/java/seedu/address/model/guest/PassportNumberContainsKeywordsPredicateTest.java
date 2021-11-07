package seedu.address.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_SECOND_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.guest.GuestBuilder;

public class PassportNumberContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PassportNumberContainsKeywordsPredicate firstPredicate =
                new PassportNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        PassportNumberContainsKeywordsPredicate secondPredicate =
                new PassportNumberContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PassportNumberContainsKeywordsPredicate firstPredicateCopy =
                new PassportNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_identifierContainsKeywords_returnsTrue() {
        // Test for Guest
        PassportNumberContainsKeywordsPredicate predicate = new PassportNumberContainsKeywordsPredicate(
                Collections.singletonList(PASSPORT_NUMBER_FIRST_PERSON.toString()));
        assertTrue(predicate.test(
                new GuestBuilder().withPassportNumber(PASSPORT_NUMBER_FIRST_PERSON.toString()).build()));

        predicate = new PassportNumberContainsKeywordsPredicate(
                Collections.singletonList(PASSPORT_NUMBER_SECOND_PERSON.toString()));
        assertTrue(predicate.test(
                new GuestBuilder().withPassportNumber(PASSPORT_NUMBER_SECOND_PERSON.toString()).build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PassportNumberContainsKeywordsPredicate predicate =
                new PassportNumberContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(
                predicate.test(new GuestBuilder().withPassportNumber(PASSPORT_NUMBER_FIRST_PERSON.toString()).build()));

        predicate = new PassportNumberContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(
                new GuestBuilder().withPassportNumber(PASSPORT_NUMBER_FIRST_PERSON.toString()).build()));

        // Non-matching keyword
        predicate = new PassportNumberContainsKeywordsPredicate(Arrays.asList(PASSPORT_NUMBER_FIRST_PERSON.toString()));
        assertFalse(predicate.test(
                new GuestBuilder().withPassportNumber(PASSPORT_NUMBER_SECOND_PERSON.toString()).build()));

        predicate = new PassportNumberContainsKeywordsPredicate(
                Collections.singletonList(PASSPORT_NUMBER_FIRST_PERSON.toString()));
        assertFalse(predicate.test(new GuestBuilder().withPassportNumber(
                PASSPORT_NUMBER_SECOND_PERSON.toString()).build()));

        // Keywords match name, email and other respective fields, but does not match identifier
        predicate = new PassportNumberContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com"));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice").withRoomNumber("12345")
                .withEmail("alice@email.com").withPassportNumber("T12312123E").build()));
    }
}
