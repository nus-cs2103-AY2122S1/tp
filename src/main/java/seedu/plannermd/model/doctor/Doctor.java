package seedu.plannermd.model.doctor;

import java.util.Set;

import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.model.tag.Tag;

/**
 * Represents a Doctor in the plannermd. Guarantees: details are present and not
 * null, field values are validated, immutable.
 */
public class Doctor extends Person {

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Address address, BirthDate birthDate, Remark remark,
            Set<Tag> tags) {
        super(name, phone, email, address, birthDate, remark, tags);
    }

    /**
     * Returns true if both doctors have the same identity and data fields. This
     * defines a stronger notion of equality between two doctors.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Doctor)) {
            return false;
        }

        Doctor otherDoctor = (Doctor) other;
        return super.equals(otherDoctor);
    }

}
