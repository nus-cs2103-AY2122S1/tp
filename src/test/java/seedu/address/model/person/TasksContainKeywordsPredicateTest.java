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

public class TasksContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = List.of("eat");
        List<String> secondPredicateKeywordList = Arrays.asList("sleep", "walk");

        TasksContainKeywordsPredicate firstPredicate = new TasksContainKeywordsPredicate(firstPredicateKeywordList);
        TasksContainKeywordsPredicate secondPredicate = new TasksContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TasksContainKeywordsPredicate firstPredicateCopy = new TasksContainKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals("first"));

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different names -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_tasksContainKeywords_returnsTrue() {
        Person samplePerson = new PersonBuilder().withTasks("eat| | | ", "sleep| | | ", "think| | | ").build();
        // One keyword
        TasksContainKeywordsPredicate predicate = new TasksContainKeywordsPredicate(new ArrayList<>(List.of("eat")));
        assertTrue(predicate.test(samplePerson));

        // Multiple keywords
        predicate = new TasksContainKeywordsPredicate(new ArrayList<>(Arrays.asList("eat", "sleep")));
        assertTrue(predicate.test(samplePerson));

        // One abbreviated keyword
        predicate = new TasksContainKeywordsPredicate(new ArrayList<>(List.of("sle")));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords
        predicate = new TasksContainKeywordsPredicate(new ArrayList<>(Arrays.asList("ea", "thin")));
        assertTrue(predicate.test(samplePerson));

        // Multiple abbreviated keywords in random case
        predicate = new TasksContainKeywordsPredicate(new ArrayList<>(Arrays.asList("eA", "tHIn")));
        assertTrue(predicate.test(samplePerson));
    }

    @Test
    public void test_tasksDoNotContainKeywords_returnsFalse() {
        TasksContainKeywordsPredicate predicate;
        // Non-matching keyword
        predicate = new TasksContainKeywordsPredicate(new ArrayList<>(List.of("run")));
        assertFalse(predicate.test(new PersonBuilder().withTags("work", "study", "exercise").build()));
    }

    @Test
    public void test_keywordsEmpty_throwsException() {
        TasksContainKeywordsPredicate predicate;
        // Empty keyword
        predicate = new TasksContainKeywordsPredicate(new ArrayList<>());
        assertThrows(
                IllegalArgumentException.class, (
                ) -> predicate.test(new PersonBuilder().withTags("work", "study", "run").build())
        );
    }
}
