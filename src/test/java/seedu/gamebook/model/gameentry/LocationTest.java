package seedu.gamebook.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.gamebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_capitalizationTest() {
        Location lowerCaseGameType = new Location("some location name");
        Location upperCaseLocation = new Location("Some Location Name");
        Location mixedCaseLocation = new Location("SoMe LoCatioN nAmE");
        assertEquals(lowerCaseGameType, upperCaseLocation);
        assertEquals(lowerCaseGameType, mixedCaseLocation);
        assertEquals(mixedCaseLocation, upperCaseLocation);
    }
}
