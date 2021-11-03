package seedu.track2gather.model.person.attributes;

import java.util.Optional;

public class NextOfKinPhone extends Attribute<Optional<Phone>> {

    /**
     * Constructs an {@code Attribute}.
     *
     * @param value A valid attribute value.
     */
    public NextOfKinPhone(Optional<Phone> value) {
        super(value);
    }

    public NextOfKinPhone(Phone value) {
        super(Optional.ofNullable(value));
    }

    public NextOfKinPhone() {
        super(Optional.empty());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NextOfKinPhone // instanceof handles nulls
                && value.equals(((NextOfKinPhone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
