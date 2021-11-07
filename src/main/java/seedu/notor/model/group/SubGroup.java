package seedu.notor.model.group;

import java.util.Objects;
import java.util.Set;

import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.tag.Tag;
import seedu.notor.model.util.Unique;

/**
 * Represents a group that can be part of a SuperGroup.
 */
public class SubGroup extends Group implements Unique<SubGroup> {

    protected String parent;

    public SubGroup(Name name, Set<Tag> tags) {
        super(name, tags);
    }

    /**
     * Creates a new subGroup where name is the name of the subGroup.
     *
     * @param name the name of the SubGroup.
     * @param parent the parent of the SubGroup.
     */
    public SubGroup(Name name, Set<Tag> tags, String parent) {
        super(name, tags);
        this.parent = parent;
    }

    /**
     * Creates a new subGroup where name is the name of the subGroup.
     *
     * @param name the name of the SubGroup.
     * @param parent the parent of the SubGroup.
     * @param note the note of the subGroup
     */
    public SubGroup(Name name, Set<Tag> tags, String parent, Note note) {
        super(name, tags);
        this.parent = parent;
        this.note = note;
    }

    /**
     * Constructor for a SubGroup instance.
     *
     * @param name Name of the SubGroup.
     * @param tags Tags included in the SubGroup.
     * @param note Notes taken on the SubGroup.
     * @param parent Parent of the SubGroup.
     */
    public SubGroup(Name name, Set<Tag> tags, Note note, String parent) {
        super(name, tags, note);
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

    @Override
    public String getName() {
        return name.toString();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(SuperGroup parent) {
        this.parent = parent.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubGroup sg = (SubGroup) o;
        return name.equals(sg.name) && (parent == sg.parent || parent.equals(sg.parent));
    }

    @Override
    public boolean isSame(SubGroup other) {
        return other.toString().equals(this.toString());
    }
}
