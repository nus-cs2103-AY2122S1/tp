package seedu.anilist.model.anime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalAnime.ALICE;
import static seedu.anilist.testutil.TypicalAnime.BOB;

import org.junit.jupiter.api.Test;

import seedu.anilist.testutil.AnimeBuilder;

public class AnimeTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Anime anime = new AnimeBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> anime.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameAnime(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameAnime(null));

        // same name, all other attributes different -> returns true
        Anime editedAlice = new AnimeBuilder(ALICE)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameAnime(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new AnimeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameAnime(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Anime editedBob = new AnimeBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameAnime(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new AnimeBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameAnime(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Anime aliceCopy = new AnimeBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Anime editedAlice = new AnimeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new AnimeBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
