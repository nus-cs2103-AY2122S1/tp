package seedu.anilist.testutil;

import seedu.anilist.model.AnimeList;
import seedu.anilist.model.anime.Anime;

/**
 * A utility class to help with building AnimeList objects.
 * Example usage: <br>
 *     {@code AnimeList al = new AnimeListBuilder().withAnime("Angel Beats!", "Banana Fish").build();}
 */
public class AnimeListBuilder {

    private AnimeList animeList;

    public AnimeListBuilder() {
        animeList = new AnimeList();
    }

    public AnimeListBuilder(AnimeList animeList) {
        this.animeList = animeList;
    }

    /**
     * Adds a new {@code Anime} to the {@code AnimeList} that we are building.
     */
    public AnimeListBuilder withAnime(Anime anime) {
        animeList.addAnime(anime);
        return this;
    }

    public AnimeList build() {
        return animeList;
    }
}
