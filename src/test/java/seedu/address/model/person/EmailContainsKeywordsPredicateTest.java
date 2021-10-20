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

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = List.of("asdf@example.com");
        List<String> secondPredicateKeywordList = List.of("fdsa@tmail.com");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals("first"));

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different emails -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        Person samplePerson = new PersonBuilder().withEmail("qwerty@bahoo.com").build();
        // One keyword
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(new ArrayList<>(List.of("qwerty@bahoo.com")));
        assertTrue(predicate.test(samplePerson));

        // One abbreviated keyword
        predicate = new EmailContainsKeywordsPredicate(new ArrayList<>(List.of("qwerty")));
        assertTrue(predicate.test(samplePerson));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        EmailContainsKeywordsPredicate predicate;
        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(new ArrayList<>(List.of("asdf")));
        assertFalse(predicate.test(new PersonBuilder().withEmail("zxcv@dotmail.com").build()));
    }

    @Test
    public void test_keywordsEmpty_throwsException() {
        EmailContainsKeywordsPredicate predicate;
        // Empty keyword
        predicate = new EmailContainsKeywordsPredicate(new ArrayList<>());
        assertThrows(
                IllegalArgumentException.class, (
                ) -> predicate.test(new PersonBuilder().withEmail("amazingspiderman@doodle.com").build())
        );
    }
}
