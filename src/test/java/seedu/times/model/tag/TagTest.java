package seedu.times.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName1 = "";
        String invalidTagName2 = " sec 4 e math";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName1));
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName2));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void isNameMatchingIgnoreCase() {
        Tag mathsTag = new Tag("Maths");
        //same name, same letter case -> returns true
        assertTrue(mathsTag.isNameMatchingIgnoreCase("Maths"));

        //same name, different letter case -> returns true
        assertTrue(mathsTag.isNameMatchingIgnoreCase("mAtHS"));

        //different name -> returns false
        assertFalse(mathsTag.isNameMatchingIgnoreCase("Physics"));
    }


}
