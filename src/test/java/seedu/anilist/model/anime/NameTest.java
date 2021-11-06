package seedu.anilist.model.anime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_NAME_LONGER_THAN_MAX_LENGTH;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STRING_EMPTY;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STRING_NON_ASCII;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STRING_SPACE;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_AKIRA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BNHA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_EIGHTY_SIX;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_FATE_ZERO;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_JOJO_GOLDEN_WIND;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_MAX_LENGTH;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_NON_ALPHANUMERIC_ASCII;
import static seedu.anilist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Name(INVALID_STRING_EMPTY));
        assertThrows(IllegalArgumentException.class, () -> new Name(INVALID_STRING_SPACE));
        assertThrows(IllegalArgumentException.class, () -> new Name(INVALID_STRING_NON_ASCII));
        assertThrows(IllegalArgumentException.class, () -> new Name(INVALID_NAME_LONGER_THAN_MAX_LENGTH));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName(INVALID_STRING_EMPTY));
        assertFalse(Name.isValidName(INVALID_STRING_SPACE));
        assertFalse(Name.isValidName(INVALID_STRING_NON_ASCII));
        assertFalse(Name.isValidName(INVALID_NAME_LONGER_THAN_MAX_LENGTH));

        // valid name
        assertTrue(Name.isValidName(VALID_NAME_BNHA));
        assertTrue(Name.isValidName(VALID_NAME_AKIRA));
        assertTrue(Name.isValidName(VALID_NAME_EIGHTY_SIX));
        assertTrue(Name.isValidName(VALID_NAME_FATE_ZERO));
        assertTrue(Name.isValidName(VALID_NAME_JOJO_GOLDEN_WIND));
        assertTrue(Name.isValidName(VALID_NAME_NON_ALPHANUMERIC_ASCII));
        assertTrue(Name.isValidName(VALID_NAME_MAX_LENGTH));
    }
}
