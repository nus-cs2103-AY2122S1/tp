package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("a@b.com");
        List<String> secondPredicateKeywordList = Arrays.asList("b@c.abc", "abc@bcdfsdf.dsfsdf");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("hibro@hotmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("hibro@hotmail.com").build()));

        // Only one matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("hibro@hotmail.com", "hisis@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("hisis@gmail.com").build()));

        // Mixed-case keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("hIbRo@HoTmAiL.CoM"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("hibro@hotmail.com").build()));

        // Multiple keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("hibro@hotmail.com", "hisis@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("hibro@hotmail.comhisisgmail.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("hibro@hotmail.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("hibro@hotmail.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("hisis@gmail.com").build()));

        // Keywords match phone, email and nationality, but does not match name
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("alice@email.com"));
        assertFalse(predicate.test(new PersonBuilder().withName("Amy Bee").withPhone("12345")
                .withEmail("alice1@email.com").withNationality("North Korea").withTutorialGroup("19")
                .withTags("Meh").build()));
    }
}
