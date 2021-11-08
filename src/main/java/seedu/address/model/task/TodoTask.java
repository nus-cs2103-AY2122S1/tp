package seedu.address.model.task;

import java.time.LocalDate;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class TodoTask extends Task {

    /**
     * Constructs a {@code TodoTask}.
     *
     * @param name A valid TaskName.
     * @param tags A valid Set of Tags.
     * @param description A valid Description.
     * @param priority A valid Priority.
     */
    public TodoTask(TaskName name, Set<Tag> tags, boolean isDone, Description description, Priority priority) {
        super(name, tags, isDone, description, priority);
    }

    /**
     * Constructs a {@code TodoTask}.
     *
     * @param name A valid TaskName.
     * @param tags A valid Set of Tags.
     * @param description A valid Description.
     * @param priority A valid Priority.
     */
    public TodoTask(TaskName name, Set<Tag> tags, Description description, Priority priority) {
        super(name, tags, description, priority);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[TODO] ")
                .append(getName())
                .append("\nStatus: ")
                .append(getStatusString())
                .append("\nPriority: ")
                .append(getPriority().toString());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.MAX;
    }

}
