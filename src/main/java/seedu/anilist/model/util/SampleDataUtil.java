package seedu.anilist.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.anilist.model.AnimeList;
import seedu.anilist.model.ReadOnlyAnimeList;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.genre.Genre;

/**
 * Contains utility methods for populating {@code AnimeList} with sample data.
 */
public class SampleDataUtil {
    public static Anime[] getSampleAnime() {
        return new Anime[] {
            new Anime(new Name("Asobi Asobase"),
                    new Episode("0"),
                    new Status("w"),
                    getGenreSet("comedy")),
            new Anime(new Name("BLEACH"),
                    new Episode("0"),
                    new Status("w"),
                    getGenreSet("adventure", "supernatural")),
            new Anime(new Name("Charlotte"),
                    new Episode("0"),
                    new Status("w"),
                    getGenreSet("drama", "supernatural")),
            new Anime(new Name("Darling in the Franxx"),
                    new Episode("0"),
                    new Status("w"),
                    getGenreSet("action", "mecha", "science fiction")),
            new Anime(new Name("Haikyuu!!: To the Top"),
                    new Episode("0"),
                    new Status("w"),
                    getGenreSet("sports")),
            new Anime(new Name("Higehiro: After Being Rejected, I Shaved and Took in a High School Runaway"),
                    new Episode("0"),
                    new Status("w"),
                    getGenreSet("genre1", "genre2", "genre3", "genre4", "genre5", "genre6", "genre7", "genre8",
                            "genre9", "genre10", "genre11", "genre12", "genre13", "genre14", "genre15")),
            new Anime(new Name("Steins;Gate 0"),
                    new Episode("0"),
                    new Status("w"),
                    getGenreSet("science fiction"))
        };
    }

    public static ReadOnlyAnimeList getSampleAnimeList() {
        AnimeList sampleAb = new AnimeList();
        for (Anime sampleAnime : getSampleAnime()) {
            sampleAb.addAnime(sampleAnime);
        }
        return sampleAb;
    }

    /**
     * Returns a genre set containing the list of strings given.
     */
    public static Set<Genre> getGenreSet(String... strings) {
        return Arrays.stream(strings)
                .map(Genre::new)
                .collect(Collectors.toSet());
    }

}
