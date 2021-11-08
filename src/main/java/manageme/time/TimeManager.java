package manageme.time;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Platform;
import manageme.model.ReadOnlyManageMe;
import manageme.model.task.Task;



public class TimeManager implements Time {
    private ReminderWindow reminderWindow;

    private ArrayList<Task> allTasks;

    private boolean isRunning = true;

    private Thread runTime = new Thread(() -> {
        while (isRunning) {
            try {
                Platform.runLater(() -> checkAlarm());
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Error with timer.");
            }
        }
    });

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public TimeManager(ReadOnlyManageMe manageMe) {
        this.allTasks = manageMe.getModifiableTaskList();
    }

    /**
     * Searches the current sorted task list for any tasks that may begin or end at the current time
     */
    public void checkAlarm() {
        ArrayList<Task> copy = new ArrayList<>();
        for (Task task: allTasks) {
            // Addition function to set how much time before actual task that you want the reminder to pop out.
            LocalDateTime time = task.getFirstOccurrence();
            LocalDateTime end = task.getEnd().getTime();
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(time) && !now.isAfter(end)) {
                copy.add(task);
            }
        }
        reminderWindow.display(copy);
    }

    /**
     * Updates the current list of tasks based on the newest model. This is called whenever changes to model is made.
     * @param manageMe is the latest version of manageMe with the update tasks.
     */
    @Override
    public void updateTasks(ReadOnlyManageMe manageMe) {
        requireNonNull(manageMe);
        this.allTasks = manageMe.getModifiableTaskList();
    }

    /**
     * Starts an asynchronous thread that checks the task list every X seconds.
     * Whenever a task is due, a pop-up is launched.
     */
    @Override
    public void startTime() {
        reminderWindow = new ReminderWindow();
        runTime.start();
    }

    @Override
    public void stopTime() {
        isRunning = false;
    }
}
