package seedu.anilist.model.anime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BNHA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SHOUNEN;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalAnimes.ALICE;
import static seedu.anilist.testutil.TypicalAnimes.BNHA;

import org.junit.jupiter.api.Test;

import seedu.anilist.testutil.AnimeBuilder;

public class AnimeTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Anime anime = new AnimeBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> anime.getGenres().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameAnime(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameAnime(null));

        // same name, all other attributes different -> returns true
        Anime editedAlice = new AnimeBuilder(ALICE)
                .withGenres(VALID_GENRE_SHOUNEN).build();
        assertTrue(ALICE.isSameAnime(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new AnimeBuilder(ALICE).withName(VALID_NAME_BNHA).build();
        assertFalse(ALICE.isSameAnime(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Anime editedBob = new AnimeBuilder(BNHA).withName(VALID_NAME_BNHA.toLowerCase()).build();
        assertFalse(BNHA.isSameAnime(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BNHA + " ";
        editedBob = new AnimeBuilder(BNHA).withName(nameWithTrailingSpaces).build();
        assertFalse(BNHA.isSameAnime(editedBob));
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

        // different anime -> returns false
        assertFalse(ALICE.equals(BNHA));

        // different name -> returns false
        Anime editedAlice = new AnimeBuilder(ALICE).withName(VALID_NAME_BNHA).build();
        assertFalse(ALICE.equals(editedAlice));

        // different genres -> returns false
        editedAlice = new AnimeBuilder(ALICE).withGenres(VALID_GENRE_SHOUNEN).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
