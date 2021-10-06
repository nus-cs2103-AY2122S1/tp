package seedu.plannermd.model.patient;

import java.util.Objects;
import java.util.Set;

import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.model.tag.Tag;

/**
 * Represents a Patient in the plannermd.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {

    // Data fields
    private final Risk risk;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags, Risk risk) {
        super(name, phone, email, address, remark, tags);
        this.risk = risk;
    }

    public Risk getRisk() {
        return risk;
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return super.equals(otherPatient)
                && otherPatient.getRisk().equals(getRisk());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(),
                getAddress(), getTags(), risk);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(super.toString());

        if (!risk.isUnclassified()) {
            builder.append("; Risk: ")
                    .append(getRisk());
        }

        return builder.toString();
    }
}
