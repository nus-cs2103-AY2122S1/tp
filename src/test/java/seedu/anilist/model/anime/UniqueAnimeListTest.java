package seedu.anilist.model.anime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SHOUNEN;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalAnimes.AOT;
import static seedu.anilist.testutil.TypicalAnimes.BNHA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.anilist.model.anime.exceptions.AnimeNotFoundException;
import seedu.anilist.model.anime.exceptions.DuplicateAnimeException;
import seedu.anilist.testutil.AnimeBuilder;

public class UniqueAnimeListTest {

    private final UniqueAnimeList uniqueAnimeList = new UniqueAnimeList();

    @Test
    public void contains_nullAnime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimeList.contains(null));
    }

    @Test
    public void contains_animeNotInList_returnsFalse() {
        assertFalse(uniqueAnimeList.contains(AOT));
    }

    @Test
    public void contains_animeInList_returnsTrue() {
        uniqueAnimeList.add(AOT);
        assertTrue(uniqueAnimeList.contains(AOT));
    }

    @Test
    public void contains_animeWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAnimeList.add(AOT);
        Anime editedAot = new AnimeBuilder(AOT).withGenres(VALID_GENRE_SHOUNEN)
                .build();
        assertTrue(uniqueAnimeList.contains(editedAot));
    }

    @Test
    public void add_nullAnime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimeList.add(null));
    }

    @Test
    public void add_duplicateAnime_throwsDuplicateAnimeException() {
        uniqueAnimeList.add(AOT);
        assertThrows(DuplicateAnimeException.class, () -> uniqueAnimeList.add(AOT));
    }

    @Test
    public void setAnime_nullTargetAnime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimeList.setAnime(null, AOT));
    }

    @Test
    public void setAnime_nullEditedAnime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimeList.setAnime(AOT, null));
    }

    @Test
    public void setAnime_targetAnimeNotInList_throwsAnimeNotFoundException() {
        assertThrows(AnimeNotFoundException.class, () -> uniqueAnimeList.setAnime(AOT, AOT));
    }

    @Test
    public void setAnime_editedAnimeIsSameAnime_success() {
        uniqueAnimeList.add(AOT);
        uniqueAnimeList.setAnime(AOT, AOT);
        UniqueAnimeList expectedUniqueAnimeList = new UniqueAnimeList();
        expectedUniqueAnimeList.add(AOT);
        assertEquals(expectedUniqueAnimeList, uniqueAnimeList);
    }

    @Test
    public void setAnime_editedAnimeHasSameIdentity_success() {
        uniqueAnimeList.add(AOT);
        Anime editedAot = new AnimeBuilder(AOT).withGenres(VALID_GENRE_SHOUNEN)
                .build();
        uniqueAnimeList.setAnime(AOT, editedAot);
        UniqueAnimeList expectedUniqueAnimeList = new UniqueAnimeList();
        expectedUniqueAnimeList.add(editedAot);
        assertEquals(expectedUniqueAnimeList, uniqueAnimeList);
    }

    @Test
    public void setAnime_editedAnimeHasDifferentIdentity_success() {
        uniqueAnimeList.add(AOT);
        uniqueAnimeList.setAnime(AOT, BNHA);
        UniqueAnimeList expectedUniqueAnimeList = new UniqueAnimeList();
        expectedUniqueAnimeList.add(BNHA);
        assertEquals(expectedUniqueAnimeList, uniqueAnimeList);
    }

    @Test
    public void setAnime_editedAnimeHasNonUniqueIdentity_throwsDuplicateAnimeException() {
        uniqueAnimeList.add(AOT);
        uniqueAnimeList.add(BNHA);
        assertThrows(DuplicateAnimeException.class, () -> uniqueAnimeList.setAnime(AOT, BNHA));
    }

    @Test
    public void remove_nullAnime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimeList.remove(null));
    }

    @Test
    public void remove_animeDoesNotExist_throwsAnimeNotFoundException() {
        assertThrows(AnimeNotFoundException.class, () -> uniqueAnimeList.remove(AOT));
    }

    @Test
    public void remove_existingAnime_removesAnime() {
        uniqueAnimeList.add(AOT);
        uniqueAnimeList.remove(AOT);
        UniqueAnimeList expectedUniqueAnimeList = new UniqueAnimeList();
        assertEquals(expectedUniqueAnimeList, uniqueAnimeList);
    }

    @Test
    public void setAnimes_nullUniqueAnimeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimeList.setMultipleAnime((UniqueAnimeList) null));
    }

    @Test
    public void setAnimes_uniqueAnimeList_replacesOwnListWithProvidedUniqueAnimeList() {
        uniqueAnimeList.add(AOT);
        UniqueAnimeList expectedUniqueAnimeList = new UniqueAnimeList();
        expectedUniqueAnimeList.add(BNHA);
        uniqueAnimeList.setMultipleAnime(expectedUniqueAnimeList);
        assertEquals(expectedUniqueAnimeList, uniqueAnimeList);
    }

    @Test
    public void setAnimes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimeList.setMultipleAnime((List<Anime>) null));
    }

    @Test
    public void setAnimes_list_replacesOwnListWithProvidedList() {
        uniqueAnimeList.add(AOT);
        List<Anime> animeList = Collections.singletonList(BNHA);
        uniqueAnimeList.setMultipleAnime(animeList);
        UniqueAnimeList expectedUniqueAnimeList = new UniqueAnimeList();
        expectedUniqueAnimeList.add(BNHA);
        assertEquals(expectedUniqueAnimeList, uniqueAnimeList);
    }

    @Test
    public void setAnimes_listWithDuplicateAnimes_throwsDuplicateAnimeException() {
        List<Anime> listWithDuplicateAnimes = Arrays.asList(AOT, AOT);
        assertThrows(DuplicateAnimeException.class, () -> uniqueAnimeList.setMultipleAnime(listWithDuplicateAnimes));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAnimeList.asUnmodifiableObservableList().remove(0));
    }
}
