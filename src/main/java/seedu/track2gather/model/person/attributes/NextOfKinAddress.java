package seedu.track2gather.model.person.attributes;

import java.util.Optional;

public class NextOfKinAddress extends Attribute<Optional<Address>> {

    /**
     * Constructs an {@code Attribute}.
     *
     * @param value A valid attribute value.
     */
    public NextOfKinAddress(Optional<Address> value) {
        super(value);
    }

    public NextOfKinAddress(Address value) {
        super(Optional.ofNullable(value));
    }

    public NextOfKinAddress() {
        super(Optional.empty());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NextOfKinAddress // instanceof handles nulls
                && value.equals(((NextOfKinAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
