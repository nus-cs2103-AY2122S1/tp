package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class TaskMatchesKeywordPredicate implements Predicate<Task> {
    protected final List<String> keywords;

    public TaskMatchesKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return taskContainsWords(task, keywords);
    }

    /**
     * Checks if a Task's name contains the specified keywords.
     *
     * @param task A {@code Task} with a name that might match the keywords.
     * @param keywords A {@code List<String>} that might contain words that match the name of the task.
     * @throws java.lang.NullPointerException if task or keywords is null
     */
    public boolean taskContainsWords(Task task, List<String> keywords) {
        requireNonNull(task);
        requireNonNull(keywords);

        String preppedName = task.getTaskName().taskName.toLowerCase();
        return StringUtil.containsWordsInOrderIgnoreCase(preppedName, keywords);
    }
}
