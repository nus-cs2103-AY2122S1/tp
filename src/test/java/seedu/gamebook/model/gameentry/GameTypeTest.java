package seedu.gamebook.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.gamebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GameTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GameType(null));
    }


    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidGameType1 = "";
        assertThrows(IllegalArgumentException.class, () -> new GameType(invalidGameType1));

        String invalidGameType2 = "                ";
        assertThrows(IllegalArgumentException.class, () -> new GameType(invalidGameType2));

        String invalidGameType3 = "  \n\n\n    ";
        assertThrows(IllegalArgumentException.class, () -> new GameType(invalidGameType3));
    }

    @Test
    public void constructor_capitalizationTest() {
        GameType lowerCaseGameType = new GameType("some game name");
        GameType upperCaseGameType = new GameType("Some Game Name");
        GameType mixedCaseGameType = new GameType("SoMe gAME nAmE");
        assertEquals(lowerCaseGameType, upperCaseGameType);
        assertEquals(lowerCaseGameType, mixedCaseGameType);
        assertEquals(mixedCaseGameType, upperCaseGameType);
    }

}
