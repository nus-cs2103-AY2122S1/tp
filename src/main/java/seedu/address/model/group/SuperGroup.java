package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a group that has many subgroups.
 */
public class SuperGroup extends Group {

    protected HashMap<String, SubGroup> subGroups;

    /**
     * Creates a new SuperGroup where name is the name of the group.
     *
     * @param name the name of the SuperGroup.
     */
    public SuperGroup(String name) {
        super(name);
        subGroups = new HashMap<>();
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
        subGroups.put(subGroup.name, subGroup);
    }

    public ArrayList<String> getDisplaySubGroups() {
        return new ArrayList<>(subGroups.keySet());
    }

    public HashMap<String, SubGroup> getSubGroups() {
        return subGroups;
    }

    @Override
    public String toString() {
        return name;
    }
}
