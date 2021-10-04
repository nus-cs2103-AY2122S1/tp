package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Category;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client implements Category {
    // Identity fields
    private final ID id;

    // Data fields
    private final Name name;

    /**
     * Constructs a {@code Client}.
     * Every field must be present and not null.
     *
     * @param name Name of Client
     */
    public Client(Name name) {
        requireAllNonNull(name);

        id = new ID();
        this.name = name;
    }

    public ID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns true if both clients have the same id.
     * This defines a weaker notion of equality between two clients.
     *
     * @param otherClient Another Client being compared to.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null && otherClient.getId().equals(this.getId());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     *
     * @param other Any Object being compared to. May or may not be a instance of Client.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return this.getId() == otherClient.getId();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ID: ")
                .append(getId())
                .append("; Name: ")
                .append(getName());

        return builder.toString();
    }
}
