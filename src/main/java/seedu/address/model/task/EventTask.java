package seedu.address.model.task;

import java.time.LocalDate;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class EventTask extends Task {

    private final TaskDate dueDate;

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid TaskName.
     * @param tags A valid Set of Tags.
     */
    public EventTask(TaskName name, Set<Tag> tags, boolean isDone,
                     TaskDate date, Description description, Priority priority) {
        super(name, tags, isDone, description, priority);
        this.dueDate = date;
    }

    /**
     * Constructs a {@code EventTask}.
     *
     * @param name A valid TaskName.
     * @param tags A valid Set of Tags.
     * @param description A valid Description.
     * @param priority A valid Priority.
     * @param dueDate A valid TaskDate.
     */
    public EventTask(TaskName name, Set<Tag> tags, Description description, Priority priority, TaskDate dueDate) {
        super(name, tags, description, priority);
        this.dueDate = dueDate;
    }

    public TaskDate getTaskDate() {
        return dueDate;
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventTask)) {
            return false;
        }

        EventTask otherTask = (EventTask) other;
        return otherTask.getName().equals(getName())
                && otherTask.getTaskDate().equals(getTaskDate())
                && otherTask.getTags().equals(getTags());
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[EVENT] ")
                .append(getName())
                .append("\nEvent on: ")
                .append(getTaskDate().toString())
                .append("\nStatus: ")
                .append(getStatusString())
                .append("\nPriority: ")
                .append(getPriority().toString());
        if (!this.getDescription().isEmpty()) {
            builder.append("\nDescription: ")
                    .append(getDescription());
        }
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    @Override
    public LocalDate getDate() {
        return this.dueDate.getDeadline();
    }

}
