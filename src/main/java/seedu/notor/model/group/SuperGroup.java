package seedu.notor.model.group;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.person.Person;
import seedu.notor.model.tag.Tag;
import seedu.notor.model.util.Unique;
import seedu.notor.model.util.UniqueList;

/**
 * Represents a group that has many subgroups.
 */
public class SuperGroup extends Group implements Unique<SuperGroup> {
    protected UniqueList<SubGroup> subGroups;

    /**
     * Constructor for a SuperGroup instance.
     *
     * @param name Name of the SuperGroup to be created.
     */
    public SuperGroup(Name name) {
        super(name, new HashSet<>());
        subGroups = new UniqueList<>();
    }

    /**
     * Constructor for a SuperGroup instance.
     *
     * @param name Name of the SuperGroup to be created.
     * @param note The note of SuperGroup.
     */
    public SuperGroup(Name name, Note note) {
        super(name, new HashSet<>());
        subGroups = new UniqueList<>();
        this.note = note;
    }

    /**
     * Creates a new SuperGroup where name is the name of the group.
     *
     * @param name Name of the SuperGroup.
     * @param tags Tags in the SuperGroup.
     */
    public SuperGroup(Name name, Set<Tag> tags) {
        super(name, tags);
        subGroups = new UniqueList<>();
    }

    /**
     * Constructor for a SuperGroup instance.
     *
     * @param name Name of the SuperGroup.
     * @param tags Tags in the SuperGroup.
     * @param note Notes taken on the SuperGroup.
     */
    public SuperGroup(Name name, Set<Tag> tags, Note note) {
        super(name, tags, note);
        subGroups = new UniqueList<>();
    }

    public String getName() {
        return this.name.toString();
    }

    /**
     * Adds SubGroup into SuperGroup.
     *
     * @param subGroup the subGroup to be added into the SuperGroup.
     */
    public void addSubGroup(SubGroup subGroup) {
        requireNonNull(subGroup);
        subGroups.add(subGroup);
    }

    /**
     * Adds SubGroup into SuperGroup.
     * TODO: Discuss whether to transfer SuperGroup name to SubGroup.
     *
     * @param subGroup the String of the subGroup to be added into the SuperGroup.
     */
    public void addSubGroup(String subGroup) {
        requireNonNull(subGroup);
        subGroups.add(new SubGroup(name, tags, getName()));
    }

    public UniqueList<SubGroup> getSubGroups() {
        return subGroups;
    }

    /**
     * Finds the SubGroup given the SubGroup name.
     *
     * @param name the Name of the SubGroup.
     * @return SubGroup
     */
    public SubGroup findSubGroup(String name) {
        for (SubGroup subGroup : subGroups) {
            if (subGroup.name.toString().equals(name)) {
                return subGroup;
            }
        }
        return null;
    }

    /**
     * Deletes the SubGroup given the SubGroup.
     *
     * @param subGroup the SubGroup to be deleted.
     */
    public void deleteSubGroup(SubGroup subGroup) {
        for (Person person : subGroup.people.values()) {
            person.removeSubGroup(subGroup);
        }
        subGroups.remove(subGroup);
    }

    public void addPersonToSubGroup(String subGroupName, Person p) {
        findSubGroup(subGroupName).addPerson(p);
    }

    /**
     * Returns true if a given string is a valid group name.
     */
    public static boolean isValidGroupName(String test) {
        // TODO: Check if this is the only condition.
        return !test.matches(".*[:/_].*") && !test.isEmpty();
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SuperGroup group = (SuperGroup) o;
        return subGroups.equals(group.subGroups) && group.name.equals(name);
    }

    @Override
    public boolean isSame(SuperGroup other) {
        return other.name.equals(this.name);
    }
}
