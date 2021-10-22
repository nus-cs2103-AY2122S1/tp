package seedu.unify.model.task;

import java.time.LocalDate;
import java.util.Iterator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * A weekly task list filtered from the given taskList.
 * It is used for the updating of UI through observables.
 * Supports a minimal set of list operations.
 */
public class WeeklyTasks implements Iterable<Task> {
    private static final Integer INITIAL_WEEK = 1;
    private static final Integer YEAR = 2021;
    private static final Integer YEAR_OFFSET = 3; // The first full week of 2021 begins on the 4th
    private static final Integer DAYS_IN_WEEK = 7;

    private final SimpleIntegerProperty weekNumber;
    private final ObservableList<Task> allTaskList;
    private final ObservableList<FilteredList<Task>> weeklyTaskList;

    /**
     * Constructs a {@code WeeklyTasks}.
     * For the INITIAL_WEEK of the YEAR.
     *
     * @param taskList A valid task list.
     */
    public WeeklyTasks(ObservableList<Task> taskList) {
        this(taskList, INITIAL_WEEK);
    }

    public WeeklyTasks(ObservableList<Task> taskList, Integer week) {
        weeklyTaskList = FXCollections.observableArrayList();
        allTaskList = taskList;
        weekNumber = new SimpleIntegerProperty(week);
        // Populate the weeklyTaskList with 7 dailyTaskList
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            weeklyTaskList.add(new FilteredList<>(allTaskList));
        }
        updateWeeklyList();
    }

    /** Returns a list of daily task list for the week */
    public ObservableList<FilteredList<Task>> getWeeklyList() {
        return weeklyTaskList;
    }

    /** Returns a task list filtered by the day of week */
    public ObservableList<Task> getDailyTaskList(Integer dayOfWeek) {
        return weeklyTaskList.get(dayOfWeek);
    }

    /** Returns the local date of the first day of the week */
    public LocalDate getFirstDayOfWeek() {
        LocalDate firstDay = LocalDate.ofYearDay(YEAR, getWeekNumber() * DAYS_IN_WEEK - YEAR_OFFSET);
        return firstDay;
    }

    /** Returns an integer value of the week number */
    public Integer getWeekNumber() {
        return weekNumber.getValue();
    }

    /** Returns an observable value of the week number */
    public SimpleIntegerProperty getObservableWeekNumber() {
        return weekNumber;
    }

    /** Set the week number of the year */
    public void setWeek(Integer week) {
        weekNumber.set(week);
        updateWeeklyList();
    }

    /** Update the filtered list to the week set */
    private void updateWeeklyList() {
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            weeklyTaskList.get(i).setPredicate(new TaskContainsDatePredicate(getFirstDayOfWeek().plusDays(i)));
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return allTaskList.iterator();
    }
}
