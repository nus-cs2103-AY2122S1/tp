package seedu.address.testutil;

import seedu.address.model.TaskListManager;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TaskListBuilder {

    private TaskListManager taskList;

    public TaskListBuilder() {
        taskList = new TaskListManager();
    }

    public TaskListBuilder(TaskListManager taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskListManager} that we are building.
     */
    public TaskListBuilder withTask(Task task) {
        taskList.addTask(task);
        return this;
    }

    public TaskListManager build() {
        return taskList;
    }
}
