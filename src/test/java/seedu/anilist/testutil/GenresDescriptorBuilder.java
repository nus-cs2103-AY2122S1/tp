package seedu.anilist.testutil;

import java.util.Set;

import seedu.anilist.logic.commands.GenreCommand;
import seedu.anilist.model.genre.Genre;
import seedu.anilist.model.util.SampleDataUtil;


/**
 * A utility class to help with building GenresDescriptor objects.
 */

public class GenresDescriptorBuilder {

    private GenreCommand.GenresDescriptor descriptor;

    public GenresDescriptorBuilder() {
        descriptor = new GenreCommand.GenresDescriptor();
    }

    public GenresDescriptorBuilder(GenreCommand.GenresDescriptor descriptor) {
        this.descriptor = new GenreCommand.GenresDescriptor(descriptor);
    }

    /**
     * Sets the {@code Genres} of the {@code GenreDescriptor} that we are building.
     */
    public seedu.anilist.testutil.GenresDescriptorBuilder withGenre(String ... genres) {
        descriptor.setGenres(SampleDataUtil.getGenreSet(genres));
        return this;
    }

    /**
     * Sets the {@code Genres} of the {@code GenreDescriptor} that we are building.
     */
    public seedu.anilist.testutil.GenresDescriptorBuilder withGenre(Set<Genre> genres) {
        descriptor.setGenres(genres);
        return this;
    }

    public GenreCommand.GenresDescriptor build() {
        return descriptor;
    }
}
