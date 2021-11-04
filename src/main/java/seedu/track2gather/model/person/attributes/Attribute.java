package seedu.track2gather.model.person.attributes;

import static java.util.Objects.requireNonNull;

public abstract class Attribute<T> {
    public final T value;

    /**
     * Constructs an {@code Attribute}.
     *
     * @param value A valid attribute value.
     */
    public Attribute(T value) {
        requireNonNull(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attribute // instanceof handles nulls
                && this.value.equals(((Attribute<?>) other).value)); // state check
    }
}
