package dash.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dash.logic.commands.taskcommand.EditTaskCommand;
import dash.model.person.Person;
import dash.model.tag.Tag;
import dash.model.task.Task;
import dash.model.task.TaskDescription;

public class EditTaskDescriptorBuilder {
    private EditTaskCommand.EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskCommand.EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskCommand.EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskCommand.EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskCommand.EditTaskDescriptor();
        descriptor.setTaskDescription(task.getTaskDescription());
        descriptor.setCompletionStatus(task.getCompletionStatus());
        descriptor.setTags(task.getTags());
        descriptor.setPeople(task.getPeople());
        descriptor.setTaskDate(task.getTaskDate());
    }

    /**
     * Sets the {@code TaskDescription} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskDescription(String taskDescription) {
        descriptor.setTaskDescription(new TaskDescription(taskDescription));
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

    /**
     * Parses the {@code people} into a {@code Set<Person>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withPeople(Person... people) {
        Set<Person> personSet = Stream.of(people).collect(Collectors.toSet());
        descriptor.setPeople(personSet);
        return this;
    }



    public EditTaskCommand.EditTaskDescriptor build() {
        return descriptor;
    }
}
