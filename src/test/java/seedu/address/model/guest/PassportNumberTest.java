package seedu.address.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PassportNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PassportNumber(null));
    }

    @Test
    public void constructor_invalidPassportNumber_throwsIllegalArgumentException() {
        String invalidPassportNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new PassportNumber(invalidPassportNumber));
    }

    @Test
    public void isValidPassportNumber() {
        // null passport number
        assertThrows(NullPointerException.class, () -> PassportNumber.isValidPassportNumber(null));

        // invalid passport numbers
        assertFalse(PassportNumber.isValidPassportNumber("")); // empty string
        assertFalse(PassportNumber.isValidPassportNumber(" ")); // spaces only
        assertFalse(PassportNumber.isValidPassportNumber("^")); // only non-alphanumeric characters
        assertFalse(PassportNumber.isValidPassportNumber("peter*")); // contains non-alphanumeric characters

        // valid passport numbers
        assertTrue(PassportNumber.isValidPassportNumber("fdsfsdafs")); // alphabets only
        assertTrue(PassportNumber.isValidPassportNumber("12345312312")); // numbers only
        assertTrue(PassportNumber.isValidPassportNumber("fsdaf2312")); // alphanumeric characters
        assertTrue(PassportNumber.isValidPassportNumber("AFSRESDFDS")); // with capital letters
        assertTrue(PassportNumber.isValidPassportNumber("32131211D"));
    }

}
