package seedu.address.model.util;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import seedu.address.model.task.TaskListManager;

/**
 * Checks the status of all tasks periodically. Any changes detected
 * would be reflected to the statistics.
 */
public class TaskStatusChecker {
    private Toolkit toolkit;
    private Timer timer;
    private TaskListManager taskListManager;

    /**
     * Constructor for TaskStatusChecker.
     */
    public TaskStatusChecker(TaskListManager taskListManager) {
        this.taskListManager = taskListManager;
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new UpdateStatusTask(), 0, 5 * 1000);
    }

    /**
     * Updates the status of all tasks by comparing the system date and time with
     * all tasks' date and time.
     */
    class UpdateStatusTask extends TimerTask {
        public void run() {
            Platform.runLater(() -> {
                taskListManager.updateAllTaskStatus();
                taskListManager.updateStatistics();
            });
        }
    }

    public void stop() {
        timer.cancel();
    }
}
