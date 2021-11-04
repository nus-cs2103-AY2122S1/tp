package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordUtilTest {

    @Test
    public void incorrect_formatPasswordReturns_false() {
        // empty password
        assertFalse(PasswordUtil.isValidPassword(""));

        // password too short
        assertFalse(PasswordUtil.isValidPassword("1232a!"));

        // contain illegal characters
        assertFalse(PasswordUtil.isValidPassword("`````sdfdf1212121"));

        // does not contain all three types of characters
        assertFalse(PasswordUtil.isValidPassword("gsdjfkhk123123"));
        assertFalse(PasswordUtil.isValidPassword("@#%$^@&*dfghj"));
        assertFalse(PasswordUtil.isValidPassword("121212121!!!!"));
        assertFalse(PasswordUtil.isValidPassword("2132354241412"));
        assertFalse(PasswordUtil.isValidPassword("sdfgjbsjkdfsdfkhsdf"));
        assertFalse(PasswordUtil.isValidPassword("!@#$%^&*(*&^%$#$%^&"));

        // contains /
        assertFalse(PasswordUtil.isValidPassword("password1234/"));

        // valid cases
        assertTrue(PasswordUtil.isValidPassword("p1!@#$%&*()_+=|<>?{}~-[]"));

        // valid plus additional characters
        assertTrue(PasswordUtil.isValidPassword("password1!@''`"));
    }
}
