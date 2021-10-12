package seedu.unify.model.task;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import javafx.collections.ObservableList;

public class WeeklyTaskList implements Iterable<Task>{

    private ObservableList<Task> weeklyTasks;
    private int weekNumber;

    public WeeklyTaskList(ObservableList<Task> weeklyTasks, int weekNumber) {
        this.weeklyTasks = weeklyTasks;
        this.weekNumber = weekNumber;
    }

    @Override
    public Iterator<Task> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Task> action) {

    }

    @Override
    public Spliterator<Task> spliterator() {
        return null;
    }
}
