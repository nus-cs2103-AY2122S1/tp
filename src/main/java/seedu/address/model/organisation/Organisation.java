package seedu.address.model.organisation;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class Organisation {

    private final Name name;
    private final UniquePersonList persons;

    /**
     * Constructor for Organisation.
     * @param n name of organisation
     */
    public Organisation(String n) {
        name = new Name(n);
        persons = new UniquePersonList();
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(Person person) {
        persons.remove(person);
    }

    public Name getName() {
        return name;
    }

    public boolean containsPerson(Person person) {
        return persons.contains(person);
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
