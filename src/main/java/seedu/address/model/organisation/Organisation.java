package seedu.address.model.organisation;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

import static java.util.Objects.requireNonNull;

public class Organisation {

    private final Name name;
    private final UniquePersonList persons;

    public Organisation(String n) {
        name = new Name(n);
        persons = new UniquePersonList();
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameOrganisation(Organisation otherOrganisation) {
        if (otherOrganisation == this) {
            return true;
        }

        return otherOrganisation != null
                && otherOrganisation.getName().equals(getName());
    }

    /**
     * Returns true if both organisations have the same identity and data fields.
     * This defines a stronger notion of equality between two organisations.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Organisation)) {
            return false;
        }

        Organisation otherOrganisation = (Organisation) other;
        return otherOrganisation.getName().equals(getName());
    }
}
