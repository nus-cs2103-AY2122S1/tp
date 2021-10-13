package seedu.anilist.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.tag.Tag;
import seedu.anilist.model.util.SampleDataUtil;

/**
 * A utility class to help with building Anime objects.
 */
public class AnimeBuilder {

    public static final String DEFAULT_NAME = "Attack on Titan";

    private Name name;
    private Episode episode;
    private Set<Tag> tags;

    /**
     * Creates a {@code AnimeBuilder} with the default details.
     */
    public AnimeBuilder() {
        name = new Name(DEFAULT_NAME);
        episode = new Episode("0");
        tags = new HashSet<>();
    }

    /**
     * Initializes the AnimeBuilder with the data of {@code animeToCopy}.
     */
    public AnimeBuilder(Anime animeToCopy) {
        name = animeToCopy.getName();
        episode = animeToCopy.getEpisode();
        tags = new HashSet<>(animeToCopy.getTags());
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Anime} that we are building.
     */
    public AnimeBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Anime build() {
        return new Anime(name, episode, tags);
    }

}
