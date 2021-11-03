package seedu.unify.model.task;

import java.util.Set;
import java.util.function.Predicate;

import seedu.unify.model.tag.Tag;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class TaskContainsTagPredicate implements Predicate<Task> {
    private final Set<Tag> tags;

    public TaskContainsTagPredicate(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Task task) {
        return task.getTags().containsAll(tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsTagPredicate // instanceof handles nulls
                && tags.equals(((TaskContainsTagPredicate) other).tags)); // state check
    }

}
