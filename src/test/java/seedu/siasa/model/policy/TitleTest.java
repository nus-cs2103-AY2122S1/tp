package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_TITLE_ALPHANUMERIC;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_TITLE_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TitleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Title(INVALID_POLICY_TITLE_ALPHANUMERIC));
    }

    @Test
    public void isValidTitle() {
        // null address
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // invalid addresses
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(INVALID_POLICY_TITLE_ALPHANUMERIC));

        // valid addresses
        assertTrue(Title.isValidTitle(VALID_POLICY_TITLE_CRITICAL));
        assertTrue(Title.isValidTitle("FULL LIFE"));
    }
}