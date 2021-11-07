package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Comparable<Person> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Description description;
    private final Boolean isImportant;

    // TaskList
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<Task> tasks,
                  Description description, boolean isImportant) {
        requireAllNonNull(name, phone, email, address, tags, tasks, description, isImportant);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.tasks.addAll(tasks);
        this.description = description;
        this.isImportant = isImportant;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isImportant() {
        return isImportant;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable task list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns the mutable task lists of this {@code Person}.
     */
    public List<Task> getModifiableTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks that are overdue.
     */
    public int getOverdueTasks() {
        int count = 0;
        for (Task task : tasks) {
            task.updateDueDate();
            if (task.getIsOverdue()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of tasks that are due soon.
     */
    public int getSoonDueTasks() {
        int count = 0;
        for (Task task : tasks) {
            task.updateDueDate();
            if (task.getIsDueSoon()) {
                count++;
            }
        }
        return count;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both persons have the same name and phone number.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns a {@code List<Task>} containing tasks that returned true
     * upon application of {@code predicates}'s test method.
     */
    public List<Task> filterTasks(Predicate<Task> predicate) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (predicate.test(task)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public static boolean isValidImportance(String input) {
        String lowerCaseInput = input.toLowerCase();
        return lowerCaseInput.equals("true") || lowerCaseInput.equals("false");
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getDescription().equals(getDescription())
                && otherPerson.isImportant() == isImportant();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, tasks, description, isImportant);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public int compareTo(Person other) {
        return this.name.toString().compareTo(other.name.toString());
    }

    /**
     * Creates a new {@code Person} object with the same attributes.
     */
    public Person makeClone() {
        return new Person(name, phone, email, address, tags, tasks, description, isImportant);
    }
}
