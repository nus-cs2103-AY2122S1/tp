package seedu.address.model.organisation;

import java.util.List;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class Organisation {

    private final Name name;
    private final Email email;
    private final UniquePersonList persons;

    /**
     * Constructor for Organisation.
     * @param n name of organisation
     */
    public Organisation(Name n, Email e) {
        name = n;
        email = e;
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
    public Email getEmail() {
        return email;
    }

    public UniquePersonList getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Returns true if both Organisations have the same name.
     * This defines a weaker notion of equality between two organisations.
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
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail());
        persons.forEach(person -> builder.append(person.toString()));
        return builder.toString();
    }
}
