package seedu.anilist.testutil;

import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SHOUNEN;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SUPERHERO;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_AKIRA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BNHA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.anilist.model.AnimeList;
import seedu.anilist.model.anime.Anime;

/**
 * A utility class containing a list of {@code Anime} objects to be used in tests.
 */
public class TypicalAnimes {

    public static final Anime ALICE = new AnimeBuilder().withName("Alice Pauline").withGenres("friends").build();
    public static final Anime BENSON = new AnimeBuilder().withName("Benson Meier")
            .withGenres("owesMoney", "friends").build();
    public static final Anime CARL = new AnimeBuilder().withName("Carl Kurz").build();
    public static final Anime DANIEL = new AnimeBuilder().withName("Daniel Meier")
            .withGenres("friends").build();
    public static final Anime ELLE = new AnimeBuilder().withName("Elle Meyer").build();
    public static final Anime FIONA = new AnimeBuilder().withName("Fiona Kunz").build();
    public static final Anime GEORGE = new AnimeBuilder().withName("George Best").build();

    // Manually added
    public static final Anime HOON = new AnimeBuilder().withName("Hoon Meier").build();
    public static final Anime IDA = new AnimeBuilder().withName("Ida Mueller").build();

    // Manually added - Anime's details found in {@code CommandTestUtil}
    public static final Anime AKIRA = new AnimeBuilder().withName(VALID_NAME_AKIRA)
            .withGenres(VALID_GENRE_SUPERHERO).build();
    public static final Anime BNHA = new AnimeBuilder().withName(VALID_NAME_BNHA)
            .withGenres(VALID_GENRE_SHOUNEN, VALID_GENRE_SUPERHERO).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAnimes() {} // prevents instantiation

    /**
     * Returns an {@code AnimeList} with all the typical anime.
     */
    public static AnimeList getTypicalAnimeList() {
        AnimeList ab = new AnimeList();
        for (Anime anime : getTypicalAnime()) {
            ab.addAnime(anime);
        }
        return ab;
    }

    public static List<Anime> getTypicalAnime() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
