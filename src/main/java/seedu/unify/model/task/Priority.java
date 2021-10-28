package seedu.unify.model.task;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class Priority {
    private static final Integer PRIORITY = Integer.MIN_VALUE;
    private static final Integer YEAR = 2021;
    private static final Integer YEAR_OFFSET = 3; // The first full week of 2021 begins on the 4th
    private static final Integer DAYS_IN_WEEK = 7;

    private final SimpleIntegerProperty priorityNumber;
    private final ObservableList<Task> allTaskList;

    /**
     * Constructs a {@code WeeklyTasks}.
     * For the INITIAL_WEEK of the YEAR.
     *
     * @param taskList A valid task list.
     */
    public Priority(ObservableList<Task> taskList) {
        this(taskList, PRIORITY);
    }

    /**
     * Constructs a {@code WeeklyTasks}.
     * For the given week of the YEAR.
     *
     * @param taskList A valid task list.
     * @param priority A valid default priority of the class.
     */
    public Priority(ObservableList<Task> taskList, Integer priority) {
        allTaskList = taskList;
        priorityNumber = new SimpleIntegerProperty(priority);
    }
}
