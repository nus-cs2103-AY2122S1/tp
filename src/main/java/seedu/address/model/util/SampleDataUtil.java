package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AnimeList;
import seedu.address.model.ReadOnlyAnimeList;
import seedu.address.model.anime.Anime;
import seedu.address.model.anime.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Anime[] getSampleAnime() {
        return new Anime[] {
            new Anime(new Name("Alex Yeoh"), getTagSet("friends")),
            new Anime(new Name("Bernice Yu"), getTagSet("colleagues", "friends")),
            new Anime(new Name("Charlotte Oliveiro"), getTagSet("neighbours")),
            new Anime(new Name("David Li"), getTagSet("family")),
            new Anime(new Name("Irfan Ibrahim"), getTagSet("classmates")),
            new Anime(new Name("Roy Balakrishnan"), getTagSet("colleagues"))
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
