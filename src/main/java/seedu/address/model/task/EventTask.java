package seedu.address.model.task;

import java.util.Set;

import seedu.address.model.tag.Tag;

public class EventTask extends Task {
    private TaskDate dueDate;

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

    //    @Override
    public TaskDate getTaskDate() {
        return dueDate;
    }

    /**
     * Returns true if both Tasks have the same name.
     * This defines a weaker notion of equality between two Task.
     */
    public boolean isSameTask(EventTask otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
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
        builder.append(getName())
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
    public EventTask clone() {
        EventTask clone = (EventTask) super.clone();
        clone.dueDate = this.dueDate;
        return clone;
    }
}
