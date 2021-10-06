package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LanguageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Language(null));
    }

    @Test
    public void constructor_invalidLanguage_throwsIllegalArgumentException() {
        String invalidLanguage = "";
        assertThrows(IllegalArgumentException.class, () -> new Language(invalidLanguage));
    }

    @Test
    public void isValidLanguage() {
        // null language
        assertThrows(NullPointerException.class, () -> Language.isValidLanguage(null));

        // blank language
        assertFalse(Language.isValidLanguage("")); // empty string
        assertFalse(Language.isValidLanguage(" ")); // spaces only

        // valid addresses
        assertTrue(Language.isValidLanguage("Tamil"));
        assertTrue(Language.isValidLanguage("-")); // one character
        assertTrue(Language.isValidLanguage("Cantonese")); // dialects
    }
}
