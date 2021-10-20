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
        return new Anime[]{
            new Anime(new Name("Asobi Asobase"), getGenreSet("comedy")),
            new Anime(new Name("BLEACH"), getGenreSet("adventure", "supernatural")),
            new Anime(new Name("Charlotte"), getGenreSet("drama", "supernatural")),
            new Anime(new Name("Darling in the Franxx"), getGenreSet("action", "mecha", "science fiction")),
            new Anime(new Name("Haikyuu!!: To the Top"), getGenreSet("sports")),
            new Anime(new Name("Steins;Gate 0"), getGenreSet("science fiction")),
            new Anime(new Name("Watashi ga Motenai no wa Dou Kangaetemo Omaera ga Warui!"),
                    getGenreSet("genre1", "genre2", "genre3", "genre4", "genre5", "genre6", "genre7", "genre8",
                            "genre9", "genre10", "genre11", "genre12", "genre13", "genre14", "genre15")),
            new Anime(new Name("3-gatsu no Lion 2nd Season"), new Episode("22"), new Status("finished"),
                    getGenreSet("drama", "slice of life")),
            new Anime(new Name("Fullmetal Alchemist: Brotherhood"), new Episode("64"), new Status("finished"),
                    getGenreSet("action", "adventure", "comedy", "drama", "fantasy")),
            new Anime(new Name("Blue Period"), new Episode("0"), new Status("towatch"),
                    getGenreSet( "drama", "slice of life")),
            new Anime(new Name("Komi-san wa, Comyushou desu."), new Episode("0"), new Status("towatch"),
                    getGenreSet( "comedy", "slice of life"))
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
