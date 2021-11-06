package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.group.GroupWithDetails;
import seedu.address.model.task.Task;

/**
 * Association class that can transport a person with all details related to him.
 * Generally a Person only hold uniqueIds, so this class is required to associate other related details.
 */
public class PersonWithDetails {

    private Person person;
    private Set<GroupWithDetails> groupsWithDetails;
    private Set<Task> tasks;

    //TODO add Task details?

    /**
     * Creates an object that holds a person with some of his other details!
     *
     * @param person to hold
     * @param groupsWithDetails to hold
     * @param tasks to hold
     */
    public PersonWithDetails(Person person, Set<GroupWithDetails> groupsWithDetails, Set<Task> tasks) {
        requireAllNonNull(person, groupsWithDetails, tasks);
        this.person = person;
        this.groupsWithDetails = groupsWithDetails;
        this.tasks = tasks;
    }

    public Person getPerson() {
        return person;
    }

    public Set<GroupWithDetails> getGroups() {
        return groupsWithDetails;
    }

    public Set<Task> getTasks() {
        return tasks;
    }
}
