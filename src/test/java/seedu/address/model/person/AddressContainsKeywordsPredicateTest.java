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

public class AddressContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = List.of("123, Jurong West Ave 6, #08-111");
        List<String> secondPredicateKeywordList = List.of("124, Jurong East Ave 5, #09-99");

        AddressContainsKeywordsPredicate firstPredicate =
                new AddressContainsKeywordsPredicate(firstPredicateKeywordList);
        AddressContainsKeywordsPredicate secondPredicate =
                new AddressContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        AddressContainsKeywordsPredicate firstPredicateCopy =
                new AddressContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals("first"));

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different names -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        Person samplePerson = new PersonBuilder().withAddress("13, Jurong North Ave 2, #05-55").build();
        // One keyword
        AddressContainsKeywordsPredicate predicate =
                new AddressContainsKeywordsPredicate(new ArrayList<>(List.of("Jurong")));
        assertTrue(predicate.test(samplePerson));

        // Multiple keywords
        predicate = new AddressContainsKeywordsPredicate(new ArrayList<>(Arrays.asList("13", "Jurong")));
        assertTrue(predicate.test(samplePerson));

        // One abbreviated keyword
        predicate = new AddressContainsKeywordsPredicate(new ArrayList<>(List.of("Juro")));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords
        predicate = new AddressContainsKeywordsPredicate(new ArrayList<>(Arrays.asList("13", "Ju")));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords in random case
        predicate = new AddressContainsKeywordsPredicate(new ArrayList<>(Arrays.asList("13", "juRong")));
        assertTrue(predicate.test(samplePerson));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        AddressContainsKeywordsPredicate predicate;
        // Non-matching keyword
        predicate = new AddressContainsKeywordsPredicate(new ArrayList<>(List.of("Clementi")));
        assertFalse(predicate.test(new PersonBuilder().withAddress("555, Jurong South Ave 3, #11-11").build()));
    }

    @Test
    public void test_keywordsEmpty_throwsException() {
        AddressContainsKeywordsPredicate predicate;
        // Empty keyword
        predicate = new AddressContainsKeywordsPredicate(new ArrayList<>());
        assertThrows(
                IllegalArgumentException.class, (
                ) -> predicate.test(new PersonBuilder().withAddress("10, Clementi Ave 4, #13-55").build())
        );
    }
}
