package seedu.address.model.group;

import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Association class that can transport a group with all details related to it.
 * Generally a Group only hold uniqueIds, so this class is required to associate other related details.
 */
public class GroupWithDetails {

    private Group group;
    private Set<Person> persons;
    private Set<Task> tasks;

    /**
     * Creates an object that holds a person with some of his other details!
     *
     * @param group to hold
     * @param persons details to hold
     * @param tasks to hold
     */
    public GroupWithDetails(Group group, Set<Person> persons, Set<Task> tasks) {
        this.group = group;
        this.tasks = tasks;
        this.persons = persons;
    }

    public Group getGroup() {
        return group;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public Set<Task> getTasks() {
        return tasks;
    }
}
