package seedu.smartnus.model.question.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.testutil.QuestionBuilder;

public class ShowQuestionIndexPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ShowQuestionIndexPredicate firstPredicate = new ShowQuestionIndexPredicate(Index.fromOneBased(1));
        ShowQuestionIndexPredicate secondPredicate = new ShowQuestionIndexPredicate(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ShowQuestionIndexPredicate firstPredicateCopy = new ShowQuestionIndexPredicate(Index.fromOneBased(1));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different question -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_correctIndex_returnsTrue() {
        // One keyword
        ShowQuestionIndexPredicate predicate = new ShowQuestionIndexPredicate(Index.fromOneBased(1));
        assertTrue(predicate.test(new QuestionBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ShowQuestionIndexPredicate predicate = new ShowQuestionIndexPredicate(Index.fromOneBased(2));
        assertFalse(predicate.test(new QuestionBuilder().withName("Alice").build()));
    }
}
