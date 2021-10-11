package seedu.address.model.group;

import java.util.Objects;

/**
 * Represents a group that can be part of a SuperGroup.
 */
public class SubGroup extends Group {

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
}
