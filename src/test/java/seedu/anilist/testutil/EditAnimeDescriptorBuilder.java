package seedu.anilist.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.anilist.logic.commands.EditCommand.EditAnimeDescriptor;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.tag.Tag;

/**
 * A utility class to help with building EditAnimeDescriptor objects.
 */
public class EditAnimeDescriptorBuilder {

    private EditAnimeDescriptor descriptor;

    public EditAnimeDescriptorBuilder() {
        descriptor = new EditAnimeDescriptor();
    }

    public EditAnimeDescriptorBuilder(EditAnimeDescriptor descriptor) {
        this.descriptor = new EditAnimeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAnimeDescriptor} with fields containing {@code anime}'s details
     */
    public EditAnimeDescriptorBuilder(Anime anime) {
        descriptor = new EditAnimeDescriptor();
        descriptor.setName(anime.getName());
        descriptor.setEpisode(anime.getEpisode());
        descriptor.setStatus(anime.getStatus());
        descriptor.setTags(anime.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditAnimeDescriptor} that we are building.
     */
    public EditAnimeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditAnimeDescriptor}
     * that we are building.
     */
    public EditAnimeDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditAnimeDescriptor build() {
        return descriptor;
    }
}
