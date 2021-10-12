package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a group that has many subgroups.
 */
public class SuperGroup extends Group {

    protected HashSet<String> subGroups;

    /**
     * Creates a new SuperGroup where name is the name of the group.
     *
     * @param name the name of the SuperGroup.
     */
    public SuperGroup(String name) {
        super(name);
        subGroups = new HashSet<>();
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
        subGroups.add(subGroup.name);
    }

    public ArrayList<String> getSubGroups() {
        return new ArrayList<>(subGroups);
    }

    @Override
    public String toString() {
        return name;
    }
}
