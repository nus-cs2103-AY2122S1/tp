package seedu.anilist.testutil;

import seedu.anilist.logic.commands.UpdateStatusCommand;
import seedu.anilist.model.anime.Status;

/**
 * A utility class to help with building EditAnimeDescriptor objects.
 */
public class StatusDescriptorBuilder {

    private UpdateStatusCommand.StatusDescriptor descriptor;

    public StatusDescriptorBuilder() {
        descriptor = new UpdateStatusCommand.StatusDescriptor();
    }

    public StatusDescriptorBuilder(UpdateStatusCommand.StatusDescriptor descriptor) {
        this.descriptor = new UpdateStatusCommand.StatusDescriptor(descriptor);
    }

    /**
     * Sets the {@code Status} of the {@code StatusDescriptor} that we are building.
     */
    public StatusDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    public UpdateStatusCommand.StatusDescriptor build() {
        return descriptor;
    }
}
