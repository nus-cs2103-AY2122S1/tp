package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void translate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Role.translateStringToRole(null));
    }

    @Test
    public void translate_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> Role.translateStringToRole(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null address
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid addresses
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("booper")); // invalid String

        // valid addresses
        assertTrue(Role.isValidRole("bartender"));
        assertTrue(Role.isValidRole("floor"));
    }
}
