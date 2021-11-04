package seedu.track2gather.model.person.attributes;

import java.util.Optional;

public class WorkAddress extends Attribute<Optional<Address>> {

    /**
     * Constructs an {@code Attribute}.
     *
     * @param value A valid attribute value.
     */
    public WorkAddress(Optional<Address> value) {
        super(value);
    }

    public WorkAddress(Address value) {
        super(Optional.ofNullable(value));
    }

    public WorkAddress() {
        super(Optional.empty());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WorkAddress // instanceof handles nulls
                && value.equals(((WorkAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
