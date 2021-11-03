package seedu.address.testutil;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskTime;
import seedu.address.model.task.Venue;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {
    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setTaskName(task.getTaskName());
        descriptor.setTaskDate(task.getDate());
        descriptor.setTaskTime(task.getTime());
        descriptor.setTaskVenue(task.getVenue());
    }

    /**
     * Sets the {@code TaskName} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskName(String taskName) {
        descriptor.setTaskName(new TaskName(taskName));
        return this;
    }

    /**
     * Sets the {@code TaskDate} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskDate(String taskDate) {
        descriptor.setTaskDate(new TaskDate(taskDate));
        return this;
    }

    /**
     * Sets the {@code TaskTime} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskTime(String taskTime) {
        descriptor.setTaskTime(new TaskTime(taskTime));
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withVenue(String venue) {
        descriptor.setTaskVenue(new Venue(venue));
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
