package seedu.anilist.testutil;

import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.anilist.model.AnimeList;
import seedu.anilist.model.anime.Anime;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Anime ALICE = new PersonBuilder().withName("Alice Pauline").withTags("friends").build();
    public static final Anime BENSON = new PersonBuilder().withName("Benson Meier")
            .withTags("owesMoney", "friends").build();
    public static final Anime CARL = new PersonBuilder().withName("Carl Kurz").build();
    public static final Anime DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withTags("friends").build();
    public static final Anime ELLE = new PersonBuilder().withName("Elle Meyer").build();
    public static final Anime FIONA = new PersonBuilder().withName("Fiona Kunz").build();
    public static final Anime GEORGE = new PersonBuilder().withName("George Best").build();

    // Manually added
    public static final Anime HOON = new PersonBuilder().withName("Hoon Meier").build();
    public static final Anime IDA = new PersonBuilder().withName("Ida Mueller").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Anime AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Anime BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AnimeList getTypicalAddressBook() {
        AnimeList ab = new AnimeList();
        for (Anime anime : getTypicalPersons()) {
            ab.addAnime(anime);
        }
        return ab;
    }

    public static List<Anime> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
