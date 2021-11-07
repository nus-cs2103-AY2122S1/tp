package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_SECOND_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FIRST_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_SECOND_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.vendor.VendorBuilder;

public class VendorIdContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        VendorIdContainsKeywordsPredicate firstPredicate =
                new VendorIdContainsKeywordsPredicate(firstPredicateKeywordList);
        VendorIdContainsKeywordsPredicate secondPredicate =
                new VendorIdContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        VendorIdContainsKeywordsPredicate firstPredicateCopy =
                new VendorIdContainsKeywordsPredicate(firstPredicateKeywordList);
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
        VendorIdContainsKeywordsPredicate predicate = new VendorIdContainsKeywordsPredicate(
                Collections.singletonList(VENDOR_ID_FIRST_PERSON.toString()));
        assertTrue(predicate.test(
                new VendorBuilder().withVendorId(VENDOR_ID_FIRST_PERSON.toString()).build()));

        predicate = new VendorIdContainsKeywordsPredicate(
                Collections.singletonList(VENDOR_ID_SECOND_PERSON.toString()));
        assertTrue(predicate.test(
                new VendorBuilder().withVendorId(VENDOR_ID_SECOND_PERSON.toString()).build()));

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        VendorIdContainsKeywordsPredicate predicate =
                new VendorIdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new VendorBuilder().withVendorId(VENDOR_ID_FIRST_PERSON.toString()).build()));

        predicate = new VendorIdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(
                new VendorBuilder().withVendorId(VENDOR_ID_FIRST_PERSON.toString()).build()));

        // Non-matching keyword
        predicate = new VendorIdContainsKeywordsPredicate(Arrays.asList(VENDOR_ID_FIRST_PERSON.toString()));
        assertFalse(predicate.test(new VendorBuilder().withVendorId(VENDOR_ID_SECOND_PERSON.toString()).build()));

        predicate = new VendorIdContainsKeywordsPredicate(
                Collections.singletonList(VENDOR_ID_FIRST_PERSON.toString()));
        assertFalse(predicate.test(new VendorBuilder().withVendorId(
                PASSPORT_NUMBER_SECOND_PERSON.toString()).build()));

        // Keywords match name, email and other respective fields, but does not match identifier
        predicate = new VendorIdContainsKeywordsPredicate(Arrays.asList("Alice", "12345",
                "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new VendorBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withVendorId("123").build()));
    }
}
