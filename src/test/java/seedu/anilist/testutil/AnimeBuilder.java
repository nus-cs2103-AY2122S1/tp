package seedu.anilist.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.genre.Genre;
import seedu.anilist.model.util.SampleDataUtil;

/**
 * A utility class to help with building Anime objects.
 */
public class AnimeBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";

    private Name name;
    private Episode episode;
    private Set<Genre> genres;

    /**
     * Creates a {@code AnimeBuilder} with the default details.
     */
    public AnimeBuilder() {
        name = new Name(DEFAULT_NAME);
        episode = new Episode("0");
        genres = new HashSet<>();
    }

    /**
     * Initializes the AnimeBuilder with the data of {@code animeToCopy}.
     */
    public AnimeBuilder(Anime animeToCopy) {
        name = animeToCopy.getName();
        episode = animeToCopy.getEpisode();
        genres = new HashSet<>(animeToCopy.getGenres());
    }

    /**
     * Sets the {@code Name} of the {@code Anime} that we are building.
     */
    public AnimeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Anime} that we are building.
     */
    public AnimeBuilder withEpisode(String episode) {
        this.episode = new Episode(episode);
        return this;
    }

    /**
     * Parses the {@code genres} into a {@code Set<Genre>} and set it to the {@code Anime} that we are building.
     */
    public AnimeBuilder withGenres(String ... genres) {
        this.genres = SampleDataUtil.getGenreSet(genres);
        return this;
    }

    public Anime build() {
        return new Anime(name, episode, genres);
    }

}
