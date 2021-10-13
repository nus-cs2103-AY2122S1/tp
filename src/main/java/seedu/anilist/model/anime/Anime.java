package seedu.anilist.model.anime;

import static seedu.anilist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.anilist.model.tag.Tag;

/**
 * Represents an Anime in the anime list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Anime {

    // Identity fields
    private final Name name;

    // Data fields
    private final Episode episode;
    private final Status status;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null
     * except Episode which defaults to 0,
     * and Status which defaults to "watching".
     */
    public Anime(Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.episode = new Episode("0");
        this.status = new Status("watching");
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null except Episode which defaults to 0.
     */
    public Anime(Name name, Status status, Set<Tag> tags) {
        requireAllNonNull(name, tags, status);
        this.name = name;
        this.episode = new Episode("0");
        this.status = status;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null except Status which defaults to "watching".
     */
    public Anime(Name name, Episode episode, Set<Tag> tags) {
        requireAllNonNull(name, tags, episode);
        this.name = name;
        this.episode = episode;
        this.status = new Status("watching");
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Anime(Name name, Episode episode, Status status, Set<Tag> tags) {
        requireAllNonNull(name, tags, episode, status);
        this.name = name;
        this.episode = episode;
        this.status = status;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Episode getEpisode() {
        return episode;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both anime have the same name.
     * This defines a weaker notion of equality between two anime.
     */
    public boolean isSameAnime(Anime otherAnime) {
        if (otherAnime == this) {
            return true;
        }

        return otherAnime != null
                && otherAnime.getName().equals(getName());
    }

    /**
     * Returns true if both anime have the same identity and data fields.
     * This defines a stronger notion of equality between two anime.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Anime)) {
            return false;
        }

        Anime otherAnime = (Anime) other;
        return otherAnime.getName().equals(getName())
                && otherAnime.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, episode, status, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        builder.append(String.format(" (Episode: %s)", getEpisode()));
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append(String.format("; (Status: %s)", getStatus()));
        return builder.toString();
    }

}
