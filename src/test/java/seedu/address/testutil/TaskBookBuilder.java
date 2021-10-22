package seedu.address.testutil;

import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TaskBookBuilder {

    private TaskBook taskList;

    public TaskBookBuilder() {
        taskList = new TaskBook();
    }

    public TaskBookBuilder(TaskBook taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskListManager} that we are building.
     */
    public TaskBookBuilder withTask(Task task) {
        taskList.addTask(task);
        return this;
    }

    public TaskBook build() {
        return taskList;
    }
}
