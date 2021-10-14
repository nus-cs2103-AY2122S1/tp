package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.Person;
import seedu.address.model.util.Unique;
import seedu.address.model.util.UniqueList;

/**
 * Represents a group that has many subgroups.
 */
public class SuperGroup extends Group implements Unique<SuperGroup> {
    protected UniqueList<SubGroup> subGroups;

    /**
     * Creates a new SuperGroup where name is the name of the group.
     *
     * @param name the name of the SuperGroup.
     */
    public SuperGroup(String name) {
        super(name);
        subGroups = new UniqueList<>();
    }

    public String getName() {
        return this.name;
    }

    /**
     * Adds SubGroup into SuperGroup.
     * @param subGroup the subGroup to be added into the SuperGroup.
     */
    public void addSubGroup(SubGroup subGroup) {
        requireNonNull(subGroup);
        subGroups.add(subGroup);
    }

    public UniqueList<SubGroup> getSubGroups() {
        return subGroups;
    }

    /**
     * Finds the SubGroup given the SubGroup name.
     * @param name the Name of the SubGroup.
     * @return SubGroup
     */
    public SubGroup findSubGroup(String name) {
        for (SubGroup subGroup: subGroups) {
            if (subGroup.name.equals(name)) {
                return subGroup;
            }
        }
        return null;
    }

    public void addPersonToSubGroup(String subGroupName, Person p) {
        findSubGroup(subGroupName).addPerson(p);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean isSame(SuperGroup other) {
        return other.name.equals(this.name);
    }
}
