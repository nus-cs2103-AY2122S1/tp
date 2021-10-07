package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class IdContainsNumberPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("#140121");
        List<String> secondPredicateKeywordList = Arrays.asList("#140252", "#124535");

        IdContainsNumberPredicate firstPredicate = new IdContainsNumberPredicate(firstPredicateKeywordList);
        IdContainsNumberPredicate secondPredicate = new IdContainsNumberPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdContainsNumberPredicate firstPredicateCopy = new IdContainsNumberPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different item -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_idContainsNumber_returnsTrue() {
        // exact
        IdContainsNumberPredicate predicate = new IdContainsNumberPredicate(Collections.singletonList("#140121"));
        assertTrue(predicate.test(new ItemBuilder().withId("#140121").build()));
    }

    @Test
    public void test_idDoesNotContainNumber_returnsFalse() {
        // empty id
        IdContainsNumberPredicate predicate = new IdContainsNumberPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ItemBuilder().withId("#147564").build()));

        // partial match
        predicate = new IdContainsNumberPredicate(Arrays.asList("#140342", "#140812"));
        assertFalse(predicate.test(new ItemBuilder().withId("140").build()));

        // completely doesn't match
        predicate = new IdContainsNumberPredicate(Arrays.asList("#140242", "#243812"));
        assertFalse(predicate.test(new ItemBuilder().withId("203523").build()));

        // Keywords match name and tag, but does not match id
        predicate = new IdContainsNumberPredicate(Arrays.asList("#12345", "baked"));
        assertFalse(predicate.test(new ItemBuilder().withName("Apple Pie").withId("#12346").withTags("baked").build()));
    }
}

