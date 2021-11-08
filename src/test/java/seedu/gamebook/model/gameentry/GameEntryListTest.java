package seedu.gamebook.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.gamebook.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.gamebook.model.gameentry.exceptions.GameEntryNotFoundException;

public class GameEntryListTest {

    private static final String DATETIME_STRING = "2021-10-21 10:06";
    private static final GameEntry POKER = new GameEntry("Poker", "100", "80",
            DATETIME_STRING, "10", "Marina bay sands", ""
    );
    private static final GameEntry ROULETTE = new GameEntry("Roulette", "100", "80.12",
            DATETIME_STRING, "10", "Marina bay sands", ""
    );
    private final GameEntryList gameEntryList = new GameEntryList();

    @Test
    public void contains_nullGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gameEntryList.contains(null));
    }

    @Test
    public void contains_gameEntryNotInList_returnsFalse() {
        assertFalse(gameEntryList.contains(POKER));
    }

    @Test
    public void contains_gameEntryInList_returnsTrue() {
        gameEntryList.add(POKER);
        assertTrue(gameEntryList.contains(POKER));
    }

    @Test
    public void contains_gameEntryWithSameIdentityFieldsInList_returnsTrue() {
        gameEntryList.add(POKER);
        GameEntry editedPoker = new GameEntry("Poker", "100", "80",
                DATETIME_STRING, "100", "Resort World Sentosa", ""
        );
        assertTrue(gameEntryList.contains(editedPoker));
    }

    @Test
    public void add_nullGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gameEntryList.add(null));
    }

    @Test
    public void setPerson_nullTargetGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gameEntryList.setGameEntry(null, POKER));
    }

    @Test
    public void setPerson_nullEditedGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gameEntryList.setGameEntry(POKER, null));
    }

    @Test
    public void setPerson_targetGameEntryNotInList_throwsGameEntryNotFoundException() {
        assertThrows(GameEntryNotFoundException.class, () -> gameEntryList.setGameEntry(POKER, POKER));
    }

    @Test
    public void setGameEntry_editedGameEntryIsSameGameEntry_success() {
        gameEntryList.add(POKER);
        gameEntryList.setGameEntry(POKER, POKER);
        GameEntryList expectedGameEntryList = new GameEntryList();
        expectedGameEntryList.add(POKER);
        assertEquals(expectedGameEntryList, gameEntryList);
    }

    @Test
    public void setGameEntry_editedGameEntryHasSameIdentity_success() {
        gameEntryList.add(POKER);

        GameEntry editedPoker = new GameEntry("Poker", "100", "80",
                "2021-10-12", "100", "Resort World Sentosa", ""
        );
        gameEntryList.setGameEntry(POKER, editedPoker);
        GameEntryList expectedGameEntryList = new GameEntryList();
        expectedGameEntryList.add(editedPoker);
        assertEquals(expectedGameEntryList, gameEntryList);
    }

    @Test
    public void setGameEntry_editedGameEntryHasDifferentIdentity_success() {
        gameEntryList.add(POKER);
        gameEntryList.setGameEntry(POKER, ROULETTE);
        GameEntryList expectedGameEntryList = new GameEntryList();
        expectedGameEntryList.add(ROULETTE);
        assertEquals(expectedGameEntryList, gameEntryList);
    }

    @Test
    public void remove_nullGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gameEntryList.remove(null));
    }

    @Test
    public void remove_gameEntryDoesNotExist_throwsGameEntryNotFoundException() {
        assertThrows(GameEntryNotFoundException.class, () -> gameEntryList.remove(POKER));
    }

    @Test
    public void remove_existingGameEntry_removesGameEntry() {
        gameEntryList.add(POKER);
        gameEntryList.remove(POKER);
        GameEntryList expectedGameEntryList = new GameEntryList();
        assertEquals(expectedGameEntryList, gameEntryList);
    }

    @Test
    public void setGameEntries_nullGameEntryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gameEntryList.setGameEntries((GameEntryList) null));
    }

    @Test
    public void setGameEntries_gameEntryList_replacesOwnListWithProvidedGameEntryList() {
        gameEntryList.add(POKER);
        GameEntryList expectedGameEntryList = new GameEntryList();
        expectedGameEntryList.add(ROULETTE);
        gameEntryList.setGameEntries(expectedGameEntryList);
        assertEquals(expectedGameEntryList, gameEntryList);
    }

    @Test
    public void setGameEntries_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gameEntryList.setGameEntries((List<GameEntry>) null));
    }

    @Test
    public void setGameEntries_list_replacesOwnListWithProvidedList() {
        gameEntryList.add(POKER);
        List<GameEntry> gameEntryList = Collections.singletonList(POKER);
        this.gameEntryList.setGameEntries(gameEntryList);
        GameEntryList expectedGameEntryList = new GameEntryList();
        expectedGameEntryList.add(POKER);
        assertEquals(expectedGameEntryList, this.gameEntryList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> gameEntryList.asUnmodifiableObservableList().remove(0));
    }

}
