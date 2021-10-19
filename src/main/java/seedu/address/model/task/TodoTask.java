package seedu.address.model.task;

import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TodoTask extends Task{
//    private final TaskName name;
//    private final Set<Tag> tags = new HashSet<>();
//    private boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid TaskName.
     * @param tags A valid Set of Tags.
     */
    public TodoTask(TaskName name, Set<Tag> tags, boolean isDone) {
        super(name, tags, isDone);
    }

//    public TaskName getName() {
//        return name;
//    }
//
//
//    public String getStatusString() {
//        return this.isDone ? "Completed" : "Pending";
//    }
//
//    public boolean checkIsDone() {
//        return isDone;
//    }
//
//    public void markTaskComplete() {
//        this.isDone = true;
//    }

//    /**
//     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
//     * if modification is attempted.
//     */
//    public Set<Tag> getTags() {
//        return Collections.unmodifiableSet(tags);
//    }
//
//    /**
//     * Returns true if both Tasks have the same name.
//     * This defines a weaker notion of equality between two Task.
//     */
//    public boolean isSameTask(TodoTask otherTask) {
//        if (otherTask == this) {
//            return true;
//        }
//
//        return otherTask != null
//                && otherTask.getName().equals(getName());
//    }
//
//    /**
//     * Returns true if both tasks have the same identity and data fields.
//     * This defines a stronger notion of equality between two tasks.
//     */
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        if (!(other instanceof Task)) {
//            return false;
//        }
//
//        TodoTask otherTask = (TodoTask) other;
//        return otherTask.getName().equals(getName())
//                && otherTask.getTags().equals(getTags());
//    }
//
//
//
//    @Override
//    public int hashCode() {
//        // use this method for custom fields hashing instead of implementing your own
//        return Objects.hash(name, tags, isDone);
//    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" [TODO]")
                .append("\nStatus: ")
                .append(getStatusString());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
