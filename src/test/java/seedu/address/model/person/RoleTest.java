package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null role
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid roles
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("engineer@nus")); // contains non-alphanumeric
        assertFalse(Role.isValidRole("software engineer (senior)")); // brackets
        assertFalse(Role.isValidRole("!@#$%^&*()_+")); // only non-alphanumeric

        // valid roles
        assertTrue(Role.isValidRole("software engineer")); // alphabets only
        assertTrue(Role.isValidRole("93121534")); // numbers only
        assertTrue(Role.isValidRole("ieee754 enjoyer")); // alphanumeric characters
        assertTrue(Role.isValidRole("Software Engineer")); // contains capital letters
        assertTrue(Role.isValidRole("Extra looooong Software engineer and tester")); // long roles
    }

}
