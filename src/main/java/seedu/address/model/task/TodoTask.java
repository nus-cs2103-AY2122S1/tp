package seedu.address.model.task;

import java.time.LocalDate;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class TodoTask extends Task {

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid TaskName.
     * @param tags A valid Set of Tags.
     */
    public TodoTask(TaskName name, Set<Tag> tags, boolean isDone, Description description, Priority priority) {
        super(name, tags, isDone, description, priority);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("[TODO]")
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

    @Override
    public TodoTask clone() {
        return (TodoTask) super.clone();
    }
}
