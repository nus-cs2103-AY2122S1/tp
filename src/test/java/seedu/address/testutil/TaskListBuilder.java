package seedu.address.testutil;

import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TaskListBuilder {

    private TaskBook taskList;

    public TaskListBuilder() {
        taskList = new TaskBook();
    }

    public TaskListBuilder(TaskBook taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskListManager} that we are building.
     */
    public TaskListBuilder withTask(Task task) {
        taskList.addTask(task);
        return this;
    }

    public TaskBook build() {
        return taskList;
    }
}
