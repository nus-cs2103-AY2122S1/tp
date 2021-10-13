package seedu.anilist.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SHOUNEN;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalAnimes.ALICE;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.exceptions.DuplicateAnimeException;
import seedu.anilist.testutil.AnimeBuilder;

public class AnimeListTest {

    private final AnimeList animeList = new AnimeList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), animeList.getAnimeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> animeList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAnimeList_replacesData() {
        AnimeList newData = getTypicalAnimeList();
        animeList.resetData(newData);
        assertEquals(newData, animeList);
    }

    @Test
    public void resetData_withDuplicateAnimes_throwsDuplicateAnimeException() {
        // Two animes with the same identity fields
        Anime editedAlice = new AnimeBuilder(ALICE).withGenres(VALID_GENRE_SHOUNEN)
                .build();
        List<Anime> newAnimes = Arrays.asList(ALICE, editedAlice);
        AnimeListStub newData = new AnimeListStub(newAnimes);

        assertThrows(DuplicateAnimeException.class, () -> animeList.resetData(newData));
    }

    @Test
    public void hasAnime_nullAnime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> animeList.hasAnime(null));
    }

    @Test
    public void hasAnime_animeNotInAnimeList_returnsFalse() {
        assertFalse(animeList.hasAnime(ALICE));
    }

    @Test
    public void hasAnime_animeInAnimeList_returnsTrue() {
        animeList.addAnime(ALICE);
        assertTrue(animeList.hasAnime(ALICE));
    }

    @Test
    public void hasAnime_animeWithSameIdentityFieldsInAnimeList_returnsTrue() {
        animeList.addAnime(ALICE);
        Anime editedAlice = new AnimeBuilder(ALICE).withGenres(VALID_GENRE_SHOUNEN)
                .build();
        assertTrue(animeList.hasAnime(editedAlice));
    }

    @Test
    public void getAnimeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> animeList.getAnimeList().remove(0));
    }

    /**
     * A stub ReadOnlyAnimeList whose animes list can violate interface constraints.
     */
    private static class AnimeListStub implements ReadOnlyAnimeList {
        private final ObservableList<Anime> anime = FXCollections.observableArrayList();

        AnimeListStub(Collection<Anime> anime) {
            this.anime.setAll(anime);
        }

        @Override
        public ObservableList<Anime> getAnimeList() {
            return anime;
        }
    }

}
