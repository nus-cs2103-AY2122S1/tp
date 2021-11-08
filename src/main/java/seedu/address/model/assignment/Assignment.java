package seedu.address.model.assignment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Assignment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assignment implements Comparable<Assignment> {

    private final Description description;
    private final DueDate dueDate;
    private final Status status;

    /**
     * Initialises Assignment with every field needing to be present and not null.
     */
    public Assignment(Description description, DueDate dueDate, Status status) {
        requireAllNonNull(description, dueDate, status);
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    /**
     * Constructor used for loading assignments
     */
    public Assignment(String[] strings) {
        this.description = new Description(strings[0]);
        this.dueDate = DueDate.createDueDate(strings[1]);
        this.status = Status.createStatus(strings[2]);

    }

    public Description getDescription() {
        return description;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns true if both assignments have the same description.
     * This defines a weaker notion of equality between two assignments.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }

        return otherAssignment != null
                && otherAssignment.getDescription().equals(getDescription());
    }

    public boolean isSameDueDate(Assignment otherAssignment) {
        return getDueDate().equals(otherAssignment.getDueDate());
    }

    public boolean isCompleted() {
        return this.status.equals(Status.createCompletedStatus());
    }
    /**
     * Returns true if both assignments have the same identity and data fields.
     * This defines a stronger notion of equality between two assignments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return otherAssignment.getDescription().equals(getDescription())
                && otherAssignment.getDueDate().equals(getDueDate())
                && otherAssignment.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, dueDate, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(", DueDate: ")
                .append(getDueDate())
                .append(", Status: ")
                .append(getStatus());
        return builder.toString();
    }

    @Override
    public int compareTo(Assignment a) {
        int statusCompare = this.getStatus().compareTo(a.getStatus());
        int dueDateCompare = this.getDueDate().compareTo(a.getDueDate());
        return (statusCompare == 0)
                ? dueDateCompare
                : statusCompare;
    }

    /**
     * Creates another copy of this assignment object
     *
     * @return a separate object of the current assignment object
     */
    public Assignment copyAssignment() {
        return new Assignment(description, dueDate, status);
    }
}

