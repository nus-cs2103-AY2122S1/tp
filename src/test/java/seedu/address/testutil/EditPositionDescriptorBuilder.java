package seedu.address.testutil;

import seedu.address.logic.position.EditPositionCommand;
import seedu.address.model.position.Position;
import seedu.address.model.position.Position.PositionStatus;
import seedu.address.model.position.Title;

/**
 * A utility class to help with building EditPositionDescriptor objects.
 */
public class EditPositionDescriptorBuilder {

    private EditPositionCommand.EditPositionDescriptor descriptor;

    public EditPositionDescriptorBuilder() {
        descriptor = new EditPositionCommand.EditPositionDescriptor();
    }

    public EditPositionDescriptorBuilder(EditPositionCommand.EditPositionDescriptor descriptor) {
        this.descriptor = new EditPositionCommand.EditPositionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPositionDescriptor} with fields containing {@code position}'s details
     */
    public EditPositionDescriptorBuilder(Position position) {
        descriptor = new EditPositionCommand.EditPositionDescriptor();
        descriptor.setTitle(position.getTitle());
        descriptor.setPositionStatus(position.getStatus());
    }

    /**
     * Sets the {@code Title} of the {@code EditPositionDescriptor} that we are building.
     */
    public EditPositionDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditPositionDescriptor} that we are building.
     */
    public EditPositionDescriptorBuilder withPositionStatus(PositionStatus status) {
        descriptor.setPositionStatus(status);
        return this;
    }

    public EditPositionCommand.EditPositionDescriptor build() {
        return descriptor;
    }
}
