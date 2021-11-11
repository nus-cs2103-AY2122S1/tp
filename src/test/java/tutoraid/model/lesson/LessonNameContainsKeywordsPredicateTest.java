package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.LessonBuilder;

public class LessonNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        LessonNameContainsKeywordsPredicate firstPredicate =
                new LessonNameContainsKeywordsPredicate(firstPredicateKeywordList);
        LessonNameContainsKeywordsPredicate secondPredicate =
                new LessonNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LessonNameContainsKeywordsPredicate firstPredicateCopy =
                new LessonNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Lesson -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        LessonNameContainsKeywordsPredicate predicate =
                new LessonNameContainsKeywordsPredicate(Collections.singletonList("Math"));
        assertTrue(predicate.test(new LessonBuilder().withLessonName("H2 Math").build()));

        // Multiple keywords
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("H2", "Math"));
        assertTrue(predicate.test(new LessonBuilder().withLessonName("H2 Math").build()));

        // Only one matching keyword
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("H2", "Math"));
        assertTrue(predicate.test(new LessonBuilder().withLessonName("H3 Math").build()));

        // Mixed-case keywords
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("h2", "MaTh"));
        assertTrue(predicate.test(new LessonBuilder().withLessonName("H2 Math").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LessonNameContainsKeywordsPredicate predicate =
                new LessonNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new LessonBuilder().withLessonName("H2 Math").build()));

        // Non-matching keyword
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("H2", "Physics"));
        assertFalse(predicate.test(new LessonBuilder().withLessonName("H3 Biology").build()));

        // Keywords match price, capacity and timing, but does not match Lesson name
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("20", "10", "Monday", "8am-10am"));
        assertFalse(predicate.test(new LessonBuilder().withLessonName("English").withPrice("20")
                .withCapacity("10").withTiming("Monday 8am-10am").build()));
    }
}
