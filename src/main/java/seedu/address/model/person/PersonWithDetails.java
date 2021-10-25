package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.group.Group;

/**
 * Association class that can transport a person with all details related to him.
 * Generally a Person only hold uniqueIds, so this class is required to associate other related details.
 */
public class PersonWithDetails {

    private Person person;
    private Set<Group> groups;

    //TODO add Task details?

    /**
     * Creates an object that holds a person with some of his other details!
     *
     * @param person to hold
     * @param groups details to hold
     */
    public PersonWithDetails(Person person, Set<Group> groups) {
        this.person = person;
        this.groups = groups;
    }

    public Person getPerson() {
        return person;
    }

    public Set<Group> getGroups() {
        return groups;
    }
}
