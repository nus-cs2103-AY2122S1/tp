package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IsImportantPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Arrays.asList("true");
        List<String> secondPredicateKeywordList = Arrays.asList("false");

        IsImportantPredicate firstPredicate = new IsImportantPredicate(firstPredicateKeywordList);
        IsImportantPredicate secondPredicate = new IsImportantPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        IsImportantPredicate firstPredicateCopy = new IsImportantPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        AttributeContainsKeywordsPredicate otherPredicate = new AddressContainsKeywordsPredicate(List.of("asdas"));
        assertNotEquals(firstPredicate, otherPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different descriptions -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        Person samplePerson = new PersonBuilder().withImportance(true).build();
        // One keyword
        IsImportantPredicate predicate = new IsImportantPredicate(Arrays.asList("true"));
        assertTrue(predicate.test(samplePerson));
    }
}
