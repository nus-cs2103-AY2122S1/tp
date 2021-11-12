package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class CombinedPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AddressContainsKeywordsPredicate firstaddressPredicate = new AddressContainsKeywordsPredicate(
                firstPredicateKeywordList, false
        );
        AddressContainsKeywordsPredicate secondAddressPredicate = new AddressContainsKeywordsPredicate(
                secondPredicateKeywordList, false
        );
        EmailContainsKeywordsPredicate emailPredicate = new EmailContainsKeywordsPredicate(
                secondPredicateKeywordList, true
        );

        ArrayList<Predicate<Person>> firstPredicateList = new ArrayList<>();
        firstPredicateList.add(firstaddressPredicate);
        firstPredicateList.add(emailPredicate);

        ArrayList<Predicate<Person>> secondPredicateList = new ArrayList<>();
        secondPredicateList.add(secondAddressPredicate);
        secondPredicateList.add(emailPredicate);

        CombinedPredicate firstPredicate = new CombinedPredicate(firstPredicateList, false);

        CombinedPredicate secondPredicate = new CombinedPredicate(secondPredicateList, false);

        CombinedPredicate thirdPredicate = new CombinedPredicate(secondPredicateList, true);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CombinedPredicate firstPredicateCopy = new CombinedPredicate(
                firstPredicateList, false
        );
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicates -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different isFindAll -> returns false
        assertFalse(secondPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        ArrayList<Predicate<Person>> predicateList;
        CombinedPredicate combinedPredicate;

        // One predicate
        AddressContainsKeywordsPredicate predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );
        predicateList = new ArrayList<>();
        predicateList.add(predicate);
        combinedPredicate = new CombinedPredicate(predicateList, false);
        assertTrue(combinedPredicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Multiple predicates
        predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );
        EmailContainsKeywordsPredicate predicate2 = new EmailContainsKeywordsPredicate(
                Arrays.asList("al", "@"), false
        );

        predicateList = new ArrayList<>();
        predicateList.add(predicate);
        predicateList.add(predicate2);

        combinedPredicate = new CombinedPredicate(predicateList, false);
        assertTrue(combinedPredicate.test(new PersonBuilder().withAddress("Alice house")
                .withEmail("al@email.com").build()));

        // Only one matching predicate
        predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );
        predicate2 = new EmailContainsKeywordsPredicate(
                Arrays.asList("bob, example"), false
        );

        predicateList = new ArrayList<>();
        predicateList.add(predicate);
        predicateList.add(predicate2);

        combinedPredicate = new CombinedPredicate(predicateList, false);
        assertTrue(combinedPredicate.test(new PersonBuilder().withAddress("Alice house")
                .withEmail("al@email.com").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        ArrayList<Predicate<Person>> predicateList;
        CombinedPredicate combinedPredicate;

        // Zero predicates
        predicateList = new ArrayList<>();
        combinedPredicate = new CombinedPredicate(predicateList, false);

        assertFalse(combinedPredicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Non-matching predicate
        AddressContainsKeywordsPredicate predicate = new AddressContainsKeywordsPredicate(
                Arrays.asList("Carol"), false
        );
        assertFalse(predicate.test(new PersonBuilder().withAddress("Alice house").build()));
    }

    @Test
    public void test_addressContainsKeywordsIsFindAll() {
        ArrayList<Predicate<Person>> predicateList;
        CombinedPredicate combinedPredicate;

        // One predicate match -> true

        AddressContainsKeywordsPredicate predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );
        predicateList = new ArrayList<>();
        predicateList.add(predicate);
        combinedPredicate = new CombinedPredicate(predicateList, true);
        assertTrue(combinedPredicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // One predicate no match -> false
        predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("bob"), false
        );
        predicateList = new ArrayList<>();
        predicateList.add(predicate);
        combinedPredicate = new CombinedPredicate(predicateList, true);
        assertFalse(combinedPredicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Multiple predicates all match -> true
        predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );

        EmailContainsKeywordsPredicate predicate2 = new EmailContainsKeywordsPredicate(
                Arrays.asList("al", "@"), true
        );

        predicateList = new ArrayList<>();
        predicateList.add(predicate);
        predicateList.add(predicate2);

        combinedPredicate = new CombinedPredicate(predicateList, true);
        assertTrue(combinedPredicate.test(new PersonBuilder().withAddress("Alice house")
                .withEmail("al@email.com").build()));

        // Only one matching keyword -> false
        predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );

        predicate2 = new EmailContainsKeywordsPredicate(//Email predicate doesnt match
                Arrays.asList("aliii, @"), true
        );

        predicateList = new ArrayList<>();
        predicateList.add(predicate);
        predicateList.add(predicate2);

        combinedPredicate = new CombinedPredicate(predicateList, true);
        assertFalse(combinedPredicate.test(new PersonBuilder().withAddress("Alice house")
                .withEmail("al@email.com").build()));
    }
}

