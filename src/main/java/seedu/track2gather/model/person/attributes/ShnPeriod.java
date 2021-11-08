package seedu.track2gather.model.person.attributes;

import java.util.Optional;

/**
 * Represents a Person's SHN period in the contacts list.
 * Guarantees: immutable; is valid as declared in Period.
 */
public class ShnPeriod extends Attribute<Optional<Period>> {

    /**
     * Constructs an {@code Attribute}.
     *
     * @param value A valid attribute value.
     */
    public ShnPeriod(Optional<Period> value) {
        super(value);
    }

    public ShnPeriod(Period value) {
        super(Optional.ofNullable(value));
    }

    public ShnPeriod() {
        super(Optional.empty());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShnPeriod // instanceof handles nulls
                && value.equals(((ShnPeriod) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
