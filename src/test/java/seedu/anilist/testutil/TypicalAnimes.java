package seedu.anilist.testutil;

import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_AKIRA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BNHA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_TAG_SHOUNEN;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_TAG_SUPERHERO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.anilist.model.AnimeList;
import seedu.anilist.model.anime.Anime;

/**
 * A utility class containing a list of {@code Anime} objects to be used in tests.
 */
public class TypicalAnimes {

    public static final Anime AOT = new AnimeBuilder().withName("Attack on Titan: The Final Season")
            .withTags("action").build();
    public static final Anime BRS = new AnimeBuilder().withName("Black Rock Shooter")
            .withTags("fantasy", "action").build();
    public static final Anime CSM = new AnimeBuilder().withName("Chainsaw Man").build();
    public static final Anime DBZ = new AnimeBuilder().withName("Dragon Ball Z")
            .withTags("action").build();
    public static final Anime ELF = new AnimeBuilder().withName("Elfen Lied").build();
    public static final Anime FSN = new AnimeBuilder().withName("Fate/stay night").build();
    public static final Anime GS = new AnimeBuilder().withName("Goblin Slayer: Goblin's Crown").build();
    public static final Anime HXH = new AnimeBuilder().withName("Hunter x Hunter (2011)").build();
    public static final Anime IDOL = new AnimeBuilder().withName("iDOLM@STER").build();

    // Manually added - Anime's details found in {@code CommandTestUtil}
    public static final Anime AKIRA = new AnimeBuilder().withName(VALID_NAME_AKIRA)
            .withTags(VALID_TAG_SUPERHERO).build();
    public static final Anime BNHA = new AnimeBuilder().withName(VALID_NAME_BNHA)
            .withTags(VALID_TAG_SHOUNEN, VALID_TAG_SUPERHERO).build();

    public static final String KEYWORD_MATCHING_HUNTER = "Hunter"; // A keyword that matches HUNTER

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
        return new ArrayList<>(Arrays.asList(AOT, BRS, CSM, DBZ, ELF, FSN, GS));
    }
}
