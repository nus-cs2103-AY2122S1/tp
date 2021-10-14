package seedu.address.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_APEX_LEGENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.MINECRAFT;
import static seedu.address.testutil.TypicalGames.VALORANT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GameBuilder;

public class GameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Game(null));
    }

    @Test
    public void isSameGameId() {
        // same object -> returns true
        assertTrue(MINECRAFT.isSameGameId(MINECRAFT));

        // name differs in case, all other attributes same -> returns true
        Game editedValorant = new GameBuilder(CSGO).withGameId(VALID_GAME_ID_CSGO.toLowerCase()).build();
        assertTrue(CSGO.isSameGameId(editedValorant));

        // different objects
        assertFalse(CSGO.isSameGameId(MINECRAFT));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Game minecraftCopy = new GameBuilder(MINECRAFT).build();
        assertEquals(MINECRAFT, minecraftCopy);

        // same object -> returns true
        assertEquals(MINECRAFT, MINECRAFT);

        // null -> returns false
        assertNotEquals(null, MINECRAFT);

        // different type -> returns false
        assertNotEquals(MINECRAFT, new GameId("a"));

        // different game -> returns false
        assertNotEquals(VALORANT, MINECRAFT);

        // different gameId -> returns false
        Game editedMinecraft = new GameBuilder(MINECRAFT).withGameId(VALID_GAME_ID_APEX_LEGENDS).build();
        assertNotEquals(MINECRAFT, editedMinecraft);
    }

    @Test
    public void hashCode_sameHashCodeOnMultipleCalls() {
        int expectedHashCode = MINECRAFT.hashCode();
        assertEquals(expectedHashCode, MINECRAFT.hashCode());
    }
}
