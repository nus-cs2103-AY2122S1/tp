package seedu.address.model.id;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * A class that represents unique ids for tasks/students using UUID.
 */
public class UniqueId {
    /**
     * The owner of the id. It can be a task or a student.
     */
    private final HasUniqueId owner;
    private final UUID id;

    private UniqueId(HasUniqueId owner) {
        requireNonNull(owner);
        this.owner = owner;
        this.id = UUID.randomUUID();
    }

    /**
     * Creates a UniqueId object.
     *
     * @param id String representation of the UUID of a task.
     */
    public UniqueId(String id) {
        requireNonNull(id);
        this.owner = null;
        this.id = UUID.fromString(id);
    }

    /**
     * Generates a unique id for a task.
     *
     * @return A unique id for a task.
     */
    public static UniqueId generateId(HasUniqueId owner) {
        return new UniqueId(owner);
    }

    public HasUniqueId getOwner() {
        return owner;
    }

    public UUID getUuid() {
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
        if (owner instanceof Task) {
            return "T-" + this.id.toString();
        }
        if (owner instanceof Person) {
            return "S-" + this.id.toString();
        }

        return "#INVALID";
    }
}
