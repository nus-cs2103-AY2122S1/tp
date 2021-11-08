package seedu.smartnus.model.question.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.question.Importance;
import seedu.smartnus.testutil.QuestionBuilder;

public class HasImportancePredicateTest {

    @Test
    public void equals() {
        Importance oneImportance = new Importance("1");
        Importance twoImportance = new Importance("2");

        HasImportancePredicate firstPredicate = new HasImportancePredicate(oneImportance);
        HasImportancePredicate secondPredicate = new HasImportancePredicate(twoImportance);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        HasImportancePredicate firstPredicateCopy = new HasImportancePredicate(oneImportance);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different importance -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_questionHasSameImportance_returnsTrue() {
        HasImportancePredicate predicate = new HasImportancePredicate(new Importance("1"));
        assertTrue(predicate.test(new QuestionBuilder().withImportance("1").build()));
    }

    @Test
    public void test_questionDoesNotHaveSameImportance_returnsFalse() {
        // Different importance
        HasImportancePredicate predicate = new HasImportancePredicate(new Importance("1"));
        assertFalse(predicate.test(new QuestionBuilder().withImportance("2").build()));
    }
}
