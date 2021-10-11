package seedu.address.testutil;

import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.task.Date;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskCommand.EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setLabel(task.getLabel());
        descriptor.setDate(task.getDate());
    }

    /**
     * Sets the {@code Label} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withLabel(String name) {
        descriptor.setLabel(new Label(name));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDate(String phone) {
        descriptor.setDate(new Date(phone));
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
