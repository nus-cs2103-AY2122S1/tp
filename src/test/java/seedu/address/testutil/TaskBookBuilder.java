package seedu.address.testutil;

import seedu.address.model.TaskBook;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TaskBookBuilder {

    private TaskBook taskBook;

    public TaskBookBuilder() {
        taskBook = new TaskBook();
    }

    public TaskBookBuilder(TaskBook taskList) {
        this.taskBook = taskList;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskListManager} that we are building.
     */
    public TaskBookBuilder withTask(Task task) {
        taskBook.addTask(task);
        return this;
    }

    public TaskBook build() {
        return taskBook;
    }
}
