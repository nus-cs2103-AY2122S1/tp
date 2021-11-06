package seedu.anilist.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TabOptionTest {

    private final String validTabStringToWatch = TabOption.VALID_TAB_STRING[0];
    private final String validTabStringFinished = TabOption.VALID_TAB_STRING[4];
    private final String invalidTabString = "nonexistent tab option";

    @Test
    public void isValidTabOption_validString_returnsTrue() {
        assertTrue(TabOption.isValidTabOption(validTabStringToWatch));
        assertTrue(TabOption.isValidTabOption(validTabStringFinished));
    }

    @Test
    public void isValidTabOption_invalidString_returnsFalse() {
        assertFalse(TabOption.isValidTabOption(invalidTabString));
    }

    @Test
    public void equals() {
        TabOption tabOption = new TabOption(validTabStringToWatch);
        TabOption otherTabOption = new TabOption(validTabStringToWatch);
        assertTrue(tabOption.equals(tabOption));
        assertTrue(tabOption.equals(otherTabOption));
    }

    @Test
    public void toString_stringReturned() {
        //TabOption
        TabOption tabOptionOne = new TabOption(validTabStringToWatch);
        assertEquals("towatch", tabOptionOne.toString());
        TabOption tabOptionTwo = new TabOption(validTabStringFinished);
        assertEquals("finished", tabOptionTwo.toString());
    }
}
