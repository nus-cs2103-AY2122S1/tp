package seedu.anilist.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.anilist.logic.commands.EditCommand.EditAnimeDescriptor;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.genre.Genre;

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
        descriptor.setGenres(anime.getGenres());
    }

    /**
     * Sets the {@code Name} of the {@code EditAnimeDescriptor} that we are building.
     */
    public EditAnimeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code genres} into a {@code Set<Genre>} and set it to the {@code EditAnimeDescriptor}
     * that we are building.
     */
    public EditAnimeDescriptorBuilder withGenres(String... genres) {
        Set<Genre> genreSet = Stream.of(genres).map(Genre::new).collect(Collectors.toSet());
        descriptor.setGenres(genreSet);
        return this;
    }

    public EditAnimeDescriptor build() {
        return descriptor;
    }
}
