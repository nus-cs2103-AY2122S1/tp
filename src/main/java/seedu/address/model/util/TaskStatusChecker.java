package seedu.address.model.util;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Checks the status of the tasks at regular intervals. Any changes detected
 * would be reflected to the statistics.
 */
public class TaskStatusChecker {
    Toolkit toolkit;
    Timer timer;
    Model model;

    /**
     * Constructor for TaskStatusChecker
     */
    public TaskStatusChecker(Model model) {
        this.model = model;
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new UpdateStatusTask(), 0, 5 * 1000);
        
    }

    /**
     * Updates the status of all tasks by comparing the system date and time with
     * the tasks' date and time.
     */
    class UpdateStatusTask extends TimerTask {
        public void run() {
            // TODO: get a unfiltered list.
            Platform.runLater(() -> {
                    ObservableList<Person> personList = model.getAddressBook().getPersonList();
                    personList.stream()
                            .flatMap(person -> person.getTasks().stream())
                            .forEach(Task::updateDueDate);
                    });
        }
    }
}
