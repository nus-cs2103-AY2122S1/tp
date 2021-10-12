package dash.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dash.model.task.Task;
import dash.model.task.TaskList;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ASSIGNMENT = new TaskBuilder().withTaskDescription("Submit CS2100 Assignment by 23:59")
            .withTags("homework").build();
    public static final Task LECTURE = new TaskBuilder().withTaskDescription("Catch up with ST2334 lectures").build();
    public static final Task PR_REVIEW = new TaskBuilder().withTaskDescription("Do PR review")
            .withTags("groupwork").build();
    public static final Task QUIZ = new TaskBuilder().withTaskDescription("ST2334 quiz before Friday")
            .withTags("quiz").build();

    private TypicalTasks() {}

    /**
     * Returns a {@code TaskList} with all the typical tasks.
     */
    public static TaskList getTypicalTaskList() {
        TaskList tl = new TaskList();
        for(Task task : getTypicalTasks()) {
            tl.add(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ASSIGNMENT, LECTURE, PR_REVIEW, QUIZ));
    }
}
