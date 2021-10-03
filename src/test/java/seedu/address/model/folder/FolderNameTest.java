package seedu.address.model.folder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class FolderNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FolderName(null));
    }

    @Test
    public void constructor_invalidFolderName_throwsIllegalArgumentException() {
        String invalidFolderName = "";
        assertThrows(IllegalArgumentException.class, () -> new FolderName(invalidFolderName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> FolderName.isValidName(null));

        // invalid name
        assertFalse(FolderName.isValidName("")); // empty string
        assertFalse(FolderName.isValidName(" ")); // spaces only
        assertFalse(FolderName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(FolderName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(FolderName.isValidName("peter jack")); // alphabets only
        assertTrue(FolderName.isValidName("12345")); // numbers only
        assertTrue(FolderName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(FolderName.isValidName("Capital Tan")); // with capital letters
        assertTrue(FolderName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
