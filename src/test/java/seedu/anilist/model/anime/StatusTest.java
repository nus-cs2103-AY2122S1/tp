package seedu.anilist.model.anime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STATUS_ALPHA;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STATUS_NUMERIC;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STRING_EMPTY;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STRING_NON_ASCII;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_STRING_SPACE;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_FINISHED;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_TOWATCH;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_TOWATCH_SHORT_FORM;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_WATCHING;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_WATCHING_SHORT_FORM;
import static seedu.anilist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Status(INVALID_STRING_SPACE));
        assertThrows(IllegalArgumentException.class, () -> new Status(INVALID_STRING_EMPTY));
        assertThrows(IllegalArgumentException.class, () -> new Status(INVALID_STRING_NON_ASCII));
        assertThrows(IllegalArgumentException.class, () -> new Status(INVALID_STATUS_ALPHA));
        assertThrows(IllegalArgumentException.class, () -> new Status(INVALID_STATUS_NUMERIC));
    }

    @Test
    public void isValidStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid status
        assertFalse(Status.isValidStatus(INVALID_STRING_SPACE));
        assertFalse(Status.isValidStatus(INVALID_STRING_EMPTY));
        assertFalse(Status.isValidStatus(INVALID_STRING_NON_ASCII));
        assertFalse(Status.isValidStatus(INVALID_STATUS_ALPHA));
        assertFalse(Status.isValidStatus(INVALID_STATUS_NUMERIC));

        // valid status
        assertTrue(Status.isValidStatus(VALID_STATUS_WATCHING));
        assertTrue(Status.isValidStatus(VALID_STATUS_WATCHING_SHORT_FORM));
        assertTrue(Status.isValidStatus(VALID_STATUS_TOWATCH));
        assertTrue(Status.isValidStatus(VALID_STATUS_TOWATCH_SHORT_FORM));
        assertTrue(Status.isValidStatus(VALID_STATUS_FINISHED));
    }
}
