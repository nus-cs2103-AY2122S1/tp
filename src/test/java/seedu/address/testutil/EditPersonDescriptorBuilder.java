package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditAnimeDescriptor;
import seedu.address.model.anime.Anime;
import seedu.address.model.anime.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditAnimeDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditAnimeDescriptor();
    }

    public EditPersonDescriptorBuilder(EditAnimeDescriptor descriptor) {
        this.descriptor = new EditAnimeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Anime anime) {
        descriptor = new EditAnimeDescriptor();
        descriptor.setName(anime.getName());
        descriptor.setTags(anime.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditAnimeDescriptor build() {
        return descriptor;
    }
}
