package seedu.address.model.group;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.id.HasUniqueId;
import seedu.address.model.id.UniqueId;

/**
 * Represents a Group in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Group implements HasUniqueId {

    // Identity fields
    private final GroupName name;

    // The id of the task
    private final UniqueId id;

    /**
     * Every field must be present and not null.
     */
    public Group(GroupName name) {
        this.id = UniqueId.generateId(this);
        requireAllNonNull(name);
        this.name = name;
    }

    /**
     * Every field must be present and not null.
     */
    public Group(GroupName name, UniqueId id) {
        this.id = id;
        requireAllNonNull(name, id);
        this.name = name;
        id.setOwner(this);
    }

    public GroupName getName() {
        return name;
    }

    public UniqueId getId() {
        return id;
    }

    /**
     * Returns true if both groups have the same name.
     * This defines a weaker notion of equality between two groups.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getName().equals(getName());
    }

    /**
     * Returns true if both groups have the same identity and data fields.
     * This defines a stronger notion of equality between two groups.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return otherGroup.getId().equals(getId()) && isSameGroup(otherGroup);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        return builder.toString();
    }
}
