package seedu.track2gather.model.person.attributes;

import java.util.Optional;

public class QuarantineAddress extends Attribute<Optional<Address>> {

    /**
     * Constructs an {@code Attribute}.
     *
     * @param value A valid attribute value.
     */
    public QuarantineAddress(Optional<Address> value) {
        super(value);
    }

    public QuarantineAddress(Address value) {
        super(Optional.ofNullable(value));
    }

    public QuarantineAddress() {
        super(Optional.empty());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuarantineAddress // instanceof handles nulls
                && value.equals(((QuarantineAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
