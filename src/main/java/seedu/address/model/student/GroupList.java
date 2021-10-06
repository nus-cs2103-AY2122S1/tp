package seedu.address.model.student;

import seedu.address.model.student.exceptions.DuplicateGroupException;
import seedu.address.model.student.exceptions.GroupNotFoundException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupList {

    public static final String MESSAGE_CONSTRAINTS =
            "Number of Groups should be a positive integer";
    public static final String VALIDATION_REGEX = "\\d+";
    public final List<Group> Groups = new ArrayList<>();

    /**
     * Returns true if a given string is a valid number of Groups.
     */
    public static boolean isValidNumOfGroups(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the list contains an equivalent Group as the given argument.
     */
    public boolean contains(Group group) {
        requireNonNull(group);
        return Groups.stream().anyMatch(group::equals);
    }

    /**
     * Adds a Group into the list.
     */
    public void add(Group group) {
        requireNonNull(group);
        if (contains(group)) {
            return;
        }
        Groups.add(group);
    }

    /**
     * Replaces the Group {@code target} in the list with {@code editedGroup}.
     * {@code target} must exist in the list.
     * The Group identity of {@code editedGroup} must not be the same as another existing Group in the list.
     */
    public void setGroup(Group target, Group editedGroup) {
        requireAllNonNull(target, editedGroup);

        int index = Groups.indexOf(target);
        if (index == -1) {
            throw new GroupNotFoundException();
        }

        if (!target.equals(editedGroup) && contains(editedGroup)) {
            throw new DuplicateGroupException();
        }

        Groups.set(index, editedGroup);
    }

    /**
     * Removes the equivalent Group from the list.
     * The Group must exist in the list.
     */
    public void remove(Group group) {
        requireNonNull(group);
        if (!Groups.remove(group)) {
            throw new GroupNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code Group}.
     */
    public void setGroups(GroupList group) {
        requireNonNull(group);
        Collections.copy(Groups, group.Groups);
    }

    /**
     * Replaces the contents of this list with {@code Groups}.
     * {@code Groups} must not contain duplicate Groups.
     */
    public void setGroups(List<Group> Groups) {
        requireAllNonNull(Groups);
        if (!GroupsAreUnique(Groups)) {
            throw new DuplicateGroupException();
        }

        Collections.copy(this.Groups, Groups);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupList // instanceof handles nulls
                && Groups.equals(((GroupList) other).Groups));
    }

    @Override
    public int hashCode() {
        return Groups.hashCode();
    }

    /**
     * Returns true if {@code Groups} contains only unique Groups.
     */
    private boolean GroupsAreUnique(List<Group> Groups) {
        for (int i = 0; i < Groups.size() - 1; i++) {
            for (int j = i + 1; j < Groups.size(); j++) {
                if (Groups.get(i).equals(Groups.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
