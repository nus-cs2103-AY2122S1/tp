package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2100_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS3230_LAB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only
        assertFalse(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidDescription("do cs210*0")); // contains non-alphanumeric characters

        // valid description
        assertTrue(Description.isValidDescription("Do Tutorial")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("cs2100 homework")); // alphanumeric characters
        assertTrue(Description.isValidDescription("CS2100 homework")); // with capital letters
        assertTrue(Description.isValidDescription("Finish Cs3230 tutorial and assignment")); // long Descriptions
    }

    @Test
    public void equals() {
        // same description -> true
        assertTrue(new Description(VALID_DESCRIPTION_CS3230_LAB).equals(new Description(VALID_DESCRIPTION_CS3230_LAB)));

        // different description -> false
        assertFalse(new Description(VALID_DESCRIPTION_CS3230_LAB)
                .equals(new Description(VALID_DESCRIPTION_CS2100_LAB)));

        // different case -> true
        assertTrue(new Description(VALID_DESCRIPTION_CS3230_LAB)
                .equals(new Description(VALID_DESCRIPTION_CS3230_LAB.toUpperCase())));
    }
}
