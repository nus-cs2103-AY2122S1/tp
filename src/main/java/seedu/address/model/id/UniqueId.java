package seedu.address.model.id;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.UUID;

import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * A class that represents unique ids for tasks/students/groups using UUID.
 */
public class UniqueId {
    public static final UniqueId DEFAULT_ID = UniqueId.generateId("00000000-0000-0000-0000-000000000000");

    /**
     * The owner of the id. It can be a task or a student.
     */
    private HasUniqueId owner;
    private final UUID id;

    private UniqueId(HasUniqueId owner) {
        requireNonNull(owner);
        this.owner = owner;
        this.id = UUID.randomUUID();
    }

    private UniqueId(String id) {
        // create a default hasUniqueId for the id's temporary owner.
        this.owner = () -> UniqueId.generateId(id);
        this.id = UUID.fromString(id);
    }

    /**
     * Creates a UniqueId object.
     *
     * @param id String representation of the UUID of a task.
     */
    public static UniqueId generateId(String id) {
        requireNonNull(id);
        return new UniqueId(id);
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

    public void setOwner(HasUniqueId owner) {
        this.owner = owner;
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

        // should not call this.owner.equals(otherId.owner), otherwise it will cause
        // StackOverflowException when running Task#equals since it also checks if the ids are equal!
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
        if (owner instanceof Group) {
            return "G-" + this.id.toString();
        }

        return "#INVALID";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
