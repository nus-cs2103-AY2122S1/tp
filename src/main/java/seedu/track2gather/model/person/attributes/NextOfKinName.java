package seedu.track2gather.model.person.attributes;

import java.util.Optional;

public class NextOfKinName extends Attribute<Optional<Name>> {

    /**
     * Constructs an {@code Attribute}.
     *
     * @param value A valid attribute value.
     */
    public NextOfKinName(Optional<Name> value) {
        super(value);
    }

    public NextOfKinName(Name value) {
        super(Optional.ofNullable(value));
    }

    public NextOfKinName() {
        super(Optional.empty());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NextOfKinName // instanceof handles nulls
                && value.equals(((NextOfKinName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
