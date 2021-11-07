package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.unify.model.tag.Tag;
import seedu.unify.testutil.TaskBuilder;

class TaskContainsTagPredicateTest {

    @Test
    void equals() {
        final Set<Tag> tagSetOne = new HashSet<>();
        tagSetOne.add(new Tag("first"));

        final Set<Tag> tagSetTwo = new HashSet<>();
        tagSetOne.add(new Tag("second"));

        TaskContainsTagPredicate firstPredicate = new TaskContainsTagPredicate(tagSetOne);
        TaskContainsTagPredicate secondPredicate = new TaskContainsTagPredicate(tagSetTwo);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsTagPredicate firstPredicateCopy =
                new TaskContainsTagPredicate(tagSetOne);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskContainsTag_returnsTrue() {
        final Set<Tag> tagSetOne = new HashSet<>();
        tagSetOne.add(new Tag("CS1234"));
        TaskContainsTagPredicate predicate =
                new TaskContainsTagPredicate(tagSetOne);
        assertTrue(predicate.test(new TaskBuilder().withTags("CS1234", "Homework").build()));
    }

    @Test
    public void test_taskDoesNotContainTag_returnsFalse() {
        // Non-matching tag
        final Set<Tag> tagSetOne = new HashSet<>();
        tagSetOne.add(new Tag("CS1234"));
        TaskContainsTagPredicate predicate =
                new TaskContainsTagPredicate(tagSetOne);
        assertFalse(predicate.test(new TaskBuilder().withTags("GE1000").build()));

        // Wrong case
        assertFalse(predicate.test(new TaskBuilder().withName("cs1234").build()));
    }

}