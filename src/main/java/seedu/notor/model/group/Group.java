package seedu.notor.model.group;

import java.util.HashMap;
import java.util.Objects;

import seedu.notor.model.exceptions.ItemNotFoundException;
import seedu.notor.model.person.Person;

/**
 * Represents a group which a person can belong to.
 */
public class Group {

    public static final String MESSAGE_CONSTRAINTS = "Group should not contain colon or slash";

    protected String name;

    protected HashMap<String, Person> people;

    /**
     * Creates a new group where name is the name of the group.
     * @param name the name of the group.
     */
    public Group(String name) {
        this.name = name;
        people = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public HashMap<String, Person> getPeople() {
        return people;
    }

    /**
     * Returns true if a given string is a valid group name.
     */
    public static boolean isValidGroupName(String test) {
        // TODO: Check if this is the only condition.
        return !test.matches(".*[:/].*");
    }

    public void addPerson(Person p) {
        people.put(p.getName().toString(), p);
    }

    /**
     * Remvoes the person from the group.
     * @param p The person object to be removed.
     * @throws ItemNotFoundException if Person is not found.
     */
    public void removePerson(Person p) throws ItemNotFoundException {
        if (!people.containsKey(p.getName().toString())) {
            throw new ItemNotFoundException();
        }
        people.remove(p.getName().toString());
    }
}
