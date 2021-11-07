package seedu.anilist.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_ACTION;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalAnimes.AOT;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnime;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeStats;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.exceptions.DuplicateAnimeException;
import seedu.anilist.model.genre.Genre;
import seedu.anilist.model.stats.Stats;
import seedu.anilist.testutil.AnimeBuilder;

/**
 * Contains integration tests (interaction with Stats) for {@code AnimeList}.
 */
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
        Anime editedAot = new AnimeBuilder(AOT).withGenres(VALID_GENRE_ACTION)
                .build();
        List<Anime> newAnimes = Arrays.asList(AOT, editedAot);
        AnimeListStub newData = new AnimeListStub(newAnimes);

        assertThrows(DuplicateAnimeException.class, () -> animeList.resetData(newData));
    }

    @Test
    public void hasAnime_nullAnime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> animeList.hasAnime(null));
    }

    @Test
    public void hasAnime_animeNotInAnimeList_returnsFalse() {
        assertFalse(animeList.hasAnime(AOT));
    }

    @Test
    public void hasAnime_animeInAnimeList_returnsTrue() {
        animeList.addAnime(AOT);
        assertTrue(animeList.hasAnime(AOT));
    }

    @Test
    public void hasAnime_animeWithSameIdentityFieldsInAnimeList_returnsTrue() {
        animeList.addAnime(AOT);
        Anime editedAot = new AnimeBuilder(AOT).withGenres(VALID_GENRE_ACTION)
                .build();
        assertTrue(animeList.hasAnime(editedAot));
    }

    @Test
    public void getAnimeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> animeList.getAnimeList().remove(0));
    }

    @Test
    public void removeAnime_nonEmptyAnimeList_returnsTrue() {
        animeList.addAnime(AOT);
        animeList.removeAnime(AOT);
        AnimeList expectedAnimeList = new AnimeList();
        assertEquals(expectedAnimeList, animeList);
    }

    @Test
    public void fetchStats_emptyAnimeList_returnsTrue() {
        HashMap<Genre, Integer> expectedGenreCountHashmap = new HashMap<>();
        Stats expectedStats = new Stats(0, 0, 0, expectedGenreCountHashmap);
        assertEquals(expectedStats, animeList.fetchUserStats());
    }

    @Test
    public void fetchStats_oneAnimeInAnimeList_returnsTrue() {
        Anime animeSingleGenre = new AnimeBuilder().withGenres(VALID_GENRE_ACTION).build();
        animeList.addAnime(animeSingleGenre);

        HashMap<Genre, Integer> expectedGenreCountHashmap = new HashMap<>();
        int actionGenreCount = 1;
        expectedGenreCountHashmap.put(new Genre(VALID_GENRE_ACTION), actionGenreCount);
        Stats expectedStats = new Stats(1 , 0, 0, expectedGenreCountHashmap);
        assertEquals(expectedStats, animeList.fetchUserStats());
    }

    @Test
    public void fetchStats_multipleAnimesInAnimeList_returnsTrue() {
        animeList.setAnimeList(getTypicalAnime());
        Stats expectedStats = getTypicalAnimeStats();
        assertEquals(expectedStats, animeList.fetchUserStats());
    }

    @Test
    public void equals() {
        assertTrue(animeList.equals(new AnimeList()));

        animeList.setAnimeList(getTypicalAnime());
        AnimeList expectedAnimeList = new AnimeList();
        expectedAnimeList.setAnimeList(getTypicalAnime());

        assertTrue(animeList.equals(expectedAnimeList));
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
