package seedu.address.testutil;

import java.util.UUID;

import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.id.UniqueId;

public class GroupBuilder {
    public static final String DEFAULT_NAME = "CS2103T";

    private GroupName name;
    private UniqueId uniqueId;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        this.name = new GroupName(DEFAULT_NAME);
        this.uniqueId = UniqueId.generateId(UUID.randomUUID().toString());
    }

    /**
     * Initializes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        name = groupToCopy.getName();
        uniqueId = groupToCopy.getId();
    }

    /**
     * Sets the {@code Description} of the {@code Group} that we are building.
     */
    public GroupBuilder withName(String name) {
        this.name = new GroupName(name);
        return this;
    }

    /**
     * Sets the {@code UniqueId} of the {@code Group} that we are building.
     */
    public GroupBuilder withUniqueId(String uniqueId) {
        this.uniqueId = UniqueId.generateId(uniqueId);
        return this;
    }



    /**
     * Builds a {@code Group} object from the {@code GroupBuilder}.
     *
     * @return A {@code Group} object.
     */
    public Group build() {
        Group group = new Group(name);
        uniqueId.setOwner(group);
        return group;
    }

    /**
     * Builds a {@code Group} object with ID from the {@code GroupBuilder}.
     *
     * @return A {@code Group} object.
     */
    public Group buildWithID() {
        Group anotherGroup = new Group(name, uniqueId);
        uniqueId.setOwner(anotherGroup);
        return anotherGroup;
    }

    /**
     * Builds a {@code Group} object to copy from the {@code GroupBuilder}.
     *
     * @return A {@code Group} object.
     */
    public Group buildToCopy() {
        Group groupToCopy = new Group(name, uniqueId);
        Group originalGroup = new Group(groupToCopy);
        return originalGroup;
    }

}
