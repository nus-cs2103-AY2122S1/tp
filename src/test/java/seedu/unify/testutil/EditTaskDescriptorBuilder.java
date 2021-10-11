package seedu.unify.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.unify.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.unify.model.task.Tag;
import seedu.unify.model.task.Date;
import seedu.unify.model.task.Name;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.Time;

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
        descriptor.setName(task.getName());
        descriptor.setTime(task.getTime());
        descriptor.setDate(task.getDate());
        descriptor.setTags(task.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTime(String time) {
        descriptor.setTime(new Time(time));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
