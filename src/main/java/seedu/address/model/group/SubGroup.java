package seedu.address.model.group;

import java.util.Objects;

import seedu.address.model.util.Unique;

/**
 * Represents a group that can be part of a SuperGroup.
 */
public class SubGroup extends Group implements Unique<SubGroup> {

    protected String parent;
    /**
     * Creates a new subGroup where name is the name of the subGroup.
     *
     * @param name the name of the SubGroup.
     * @param parent the parent of the SubGroup.
     */
    public SubGroup(String name, String parent) {
        super(name);
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, name);
    }

    @Override
    public String toString() {
        return parent + "_" + this.name;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }


    @Override
    public boolean isSame(SubGroup other) {
        return other.toString().equals(this.toString());
    }
}
