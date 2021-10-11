package seedu.address.commons.core.id;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

/**
 * A class that represents unique ids for tasks/students using UUID.
 */
public class UniqueId {
    /**
     * The owner of the id. It can be a task or a student.
     */
    private final IdOwner owner;
    private final UUID id;

    private UniqueId(IdOwner owner) {
        requireNonNull(owner);
        this.owner = owner;
        this.id = UUID.randomUUID();
    }

    public UniqueId(String id) {
        requireNonNull(id);
        this.owner = null;
        this.id = UUID. fromString(id);
    }

    public UniqueId(UUID id) {
        requireNonNull(id);
        this.owner = null;
        this.id = id;
    }

    /**
     * Generates a unique id for a task.
     *
     * @return A unique id for a task.
     */
    public static UniqueId generateTaskId() {
        return new UniqueId(IdOwner.TASK);
    }

    /**
     * Generates a unique id for a student.
     *
     * @return A unique id for a student.
     */
    public static UniqueId generateStudentId() {
        return new UniqueId(IdOwner.STUDENT);
    }

    public UUID getUUID() {
        return this.id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof UniqueId)) {
            return false;
        }

        UniqueId otherId = (UniqueId) other;
        //return this.id.equals(otherId.id) && this.owner.equals(otherId.owner);
        return this.id.equals(otherId.id);
    }

    @Override
    public String toString() {
        switch (this.owner) {
        case TASK:
            return "T-" + this.id.toString();
        case STUDENT:
            return "S-" + this.id.toString();
        default:
            // should not reach here
            assert false : "The unique id doesn't have the valid owner.";
            return "#INVALID_ID";
        }
    }

    private enum IdOwner {
        TASK, STUDENT
    }
}
