package seedu.anilist.testutil;

import seedu.anilist.logic.commands.RenameCommand;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Name;

public class NameDescriptorBuilder {

    private RenameCommand.NameDescriptor descriptor;

    public NameDescriptorBuilder() {
        descriptor = new RenameCommand.NameDescriptor();
    }

    public NameDescriptorBuilder(RenameCommand.NameDescriptor descriptor) {
        this.descriptor = new RenameCommand.NameDescriptor(descriptor);
    }

    /**
     * Returns an {@code NameDescriptor} with fields containing {@code anime}'s details
     */
    public NameDescriptorBuilder(Anime anime) {
        descriptor = new RenameCommand.NameDescriptor();
        descriptor.setName(anime.getName());
    }

    /**
     * Sets the {@code Name} of the {@code NameDescriptor} that we are building.
     */
    public NameDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    public RenameCommand.NameDescriptor build() {
        return descriptor;
    }
}
