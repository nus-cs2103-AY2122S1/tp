package dash.model.task;

import static dash.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import dash.model.person.Person;
import dash.model.tag.Tag;

/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskDescription taskDescription;
    private final CompletionStatus completionStatus;
    private final TaskDate taskDate;
    private final Set<Person> people = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Task}.
     * @param taskDescription A valid task description
     * @param completionStatus Completion status of task
     * @param taskDate Date assigned to task
     * @param people People assigned to task
     * @param tags A valid tag
     */
    public Task(TaskDescription taskDescription, CompletionStatus completionStatus, TaskDate taskDate,
                Set<Person> people, Set<Tag> tags) {
        requireAllNonNull(taskDescription, completionStatus, taskDate, tags);
        this.taskDescription = taskDescription;
        this.completionStatus = completionStatus;
        this.taskDate = taskDate;
        this.people.addAll(people);
        this.tags.addAll(tags);
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public TaskDate getTaskDate() {
        return taskDate;
    }

    public Set<Person> getPeople() {
        return Collections.unmodifiableSet(people);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns a boolean of whether or not this task contains the given person.
     *
     * @param person The given person.
     * @return If the task contains the given person.
     */
    public boolean containsPerson(Person person) {
        for (Person p : getPeople()) {
            if (p.equals(person)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both tasks have the same task description and tags.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTaskDescription().equals(getTaskDescription())
                && otherTask.getCompletionStatus().equals(getCompletionStatus())
                && otherTask.getTaskDate().equals(getTaskDate())
                && otherTask.getTags().equals(getTags())
                && otherTask.getPeople().equals(getPeople());
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskDescription, completionStatus, taskDate, people, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (completionStatus.get()) {
            builder.append(getTaskDescription())
                    .append(" (completed)");
        } else {
            builder.append(getTaskDescription())
                    .append(" (not completed)");
        }

        if (taskDate.hasDate()) {
            builder.append("; Date: ")
                    .append(getTaskDate().toDateString());
        }

        if (taskDate.hasTime()) {
            builder.append("; Time: ")
                    .append(getTaskDate().toTimeString());
        }

        Set<Person> people = getPeople();
        if (!people.isEmpty()) {
            builder.append("; People: ");
            people.forEach(p -> builder.append("[" + p.getName() + "]"));
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
