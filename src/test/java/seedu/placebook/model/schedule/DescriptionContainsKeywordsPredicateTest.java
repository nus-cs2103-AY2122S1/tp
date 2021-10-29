package seedu.placebook.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.placebook.model.person.Person;
import seedu.placebook.testutil.AppointmentBuilder;
import seedu.placebook.testutil.PersonBuilder;

class DescriptionContainsKeywordsPredicateTest {

    @Test
    void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("meeting"));
        assertTrue(predicate.test(new AppointmentBuilder().withDescription("zoom meeting").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("zoom", "meeting"));
        assertTrue(predicate.test(new AppointmentBuilder().withDescription("zoom meeting").build()));

        // Only one matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("zoom", "meeting"));
        assertTrue(predicate.test(new AppointmentBuilder().withDescription("zoom").build()));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("zoOm", "mEetIng"));
        assertTrue(predicate.test(new AppointmentBuilder().withDescription("Zoom Meeting").build()));
    }


    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AppointmentBuilder().withDescription("zoom meeting").build()));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Conference"));
        assertFalse(predicate.test(new AppointmentBuilder().withDescription("zoom meeting").build()));

        // Keywords match person's name, address, date, time, but does not match description
        Person mockClient = new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build();
        predicate = new DescriptionContainsKeywordsPredicate(Arrays
                .asList("12345", "Alice", "alice@email.com", "Main", "Street", "Building", "15-12-2020", "2103"));
        assertFalse(predicate.test(new AppointmentBuilder().withDescription("meeting").withClient(mockClient)
                .withLocation("Main Building").withTimePeriod("25-12-2021 1000", "25-12-2021 1100").build()));
    }
}
