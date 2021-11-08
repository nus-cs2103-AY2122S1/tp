package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.StringUtil;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("biscuit*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("butter biscuit")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("100 plus")); // alphanumeric characters
        assertTrue(Name.isValidName("Butter Biscuit")); // with capital letters
        assertTrue(Name.isValidName("Butter Butter Butter Butter Butter Biscuit")); // long names
    }

    @Test
    public void hasSameLower_sameLowerCaseName_returnTrue() {
        String randString = StringUtil.generateRandomString();
        Name lower = new Name(randString.toLowerCase());
        Name upper = new Name(randString.toUpperCase());

        assertTrue(lower.hasSameLower(upper));
    }

    @Test
    public void hasSameLower_differentLowerCaseName_returnFalse() {
        Name lower = new Name(StringUtil.generateRandomString().toLowerCase());
        Name upper = new Name(StringUtil.generateRandomString().toUpperCase());

        assertTrue(lower.hasSameLower(upper));
    }
}
