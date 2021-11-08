package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.task.Task;

/**
 * Tests that a {@code Person}'s {@code List<Task>} matches any of the keywords given.
 */
public class TasksContainKeywordsPredicate extends AttributeContainsKeywordsPredicate {

    /**
     * Constructor for a TasksContainKeywordsPredicate.
     *
     * @param keywords The {@code List<String>} that contains keywords used to find a person.
     */
    public TasksContainKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return tasksContainWords(person, keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TasksContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TasksContainKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Checks if a person's tasks contains the specified keywords.
     *
     * @param person A {@code Person} whose tasks might match the keywords.
     * @param keywords A {@code List<String>} that might contain words that match the tasks of the person.
     * @throws java.lang.NullPointerException if person or keywords is null
     */
    public boolean tasksContainWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);
        checkArgument(!keywords.stream().allMatch(String::isEmpty));

        return tasksMatchKeyword(person.getTasks(), keywords);
    }

    /**
     * Checks if a keyword matches any tag in a set of tags.
     *
     * @param tasks A {@code List<Task>} that might contain a tasks which has a name that matches the keyword.
     * @param keywords A {@code List<String>} that might match any task in the tasks given.
     */
    public boolean tasksMatchKeyword(List<Task> tasks, List<String> keywords) {
        List<String> preppedTaskNames = new ArrayList<>();
        for (Task task : tasks) {
            preppedTaskNames.add(task.getTaskName().taskName.toLowerCase());
        }
        return preppedTaskNames.stream().anyMatch(s -> StringUtil.containsWordsInOrderIgnoreCase(s, keywords));
    }
}
