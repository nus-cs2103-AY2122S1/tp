package seedu.address.testutil;

import seedu.address.logic.commands.AddAllocCommand.AllocDescriptor;
import seedu.address.model.student.Group;
import seedu.address.model.student.ID;
import seedu.address.model.student.Name;

/**
 * A utility class to help with building AllocDescriptor objects.
 */
public class AllocDescriptorBuilder {
    private AllocDescriptor descriptor;

    public AllocDescriptorBuilder() {
        descriptor = new AllocDescriptor();
    }

    public AllocDescriptorBuilder(AllocDescriptor descriptor) {
        this.descriptor = new AllocDescriptor(descriptor);
    }

    /**
     * Sets the {@code Name} of the {@code AllocDescriptorBuilder} that we are building.
     */
    public AllocDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code id} of the {@code AllocDescriptorBuilder} that we are building.
     */
    public AllocDescriptorBuilder withId(String id) {
        descriptor.setId(new ID(id));
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Group} that we are building.
     */
    public AllocDescriptorBuilder withGroup(String group) {
        descriptor.setGroup(new Group(group));
        return this;
    }

    public AllocDescriptor build() {
        return descriptor;
    }
}

