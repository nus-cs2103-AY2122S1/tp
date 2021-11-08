package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void constructor_emptyStringTagColour_throwsIllegalArgumentException() {
        String validTagName = "ValidTag";
        String invalidTagColour = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(validTagName, invalidTagColour));
    }

    @Test
    public void constructor_invalidWordTagColour_throwsIllegalArgumentException() {
        String validTagName = "ValidTag";
        String invalidTagColour = "#5";
        assertThrows(IllegalArgumentException.class, () -> new Tag(validTagName, invalidTagColour));
    }

    @Test
    public void constructor_invalidColourFieldTagColour_throwsIllegalArgumentException() {
        String validTagName = "ValidTag";
        String invalidTagColour = "bubbleBlue";
        assertThrows(IllegalArgumentException.class, () -> new Tag(validTagName, invalidTagColour));
    }

    @Test
    public void constructor_invalidTagAndTagColour_throwsIllegalArgumentException() {
        String invalidTagName = "";
        String invalidTagColour = "bubbleBlue";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName, invalidTagColour));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        assertFalse(Tag.isValidTagName("#bro4life")); // invalid tag name
        assertFalse(Tag.isValidTagName("")); // empty tag name

        assertTrue(Tag.isValidTagName("friend")); // alphabet-only tag name
        assertTrue(Tag.isValidTagName("12345")); // numeric-only tag name
        assertTrue(Tag.isValidTagName("friend12345")); // alphanumeric tag name

    }

    @Test
    public void isValidTagColour() {
        // null tag colour
        assertThrows(NullPointerException.class, () -> Tag.isValidTagColour(null));

        assertFalse(Tag.isValidTagColour("#yellow")); // invalid tag colour
        assertFalse(Tag.isValidTagColour("#12")); // invalid colour code
        assertFalse(Tag.isValidTagColour("")); // empty tag name

        assertTrue(Tag.isValidTagColour("#0000FF")); // valid tag colour
        assertTrue(Tag.isValidTagColour("#123456")); // valid tag colour
        assertTrue(Tag.isValidTagColour("yellow")); // valid tag colour

    }

}
