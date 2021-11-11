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
        assertFalse(PasswordUtil.isValidPassword("1234567"));

        // password too long
        assertFalse(PasswordUtil.isValidPassword("123456789012345678901234567890123"));

        // contain illegal characters
        assertFalse(PasswordUtil.isValidPassword("/////////////"));
        assertFalse(PasswordUtil.isValidPassword("--------------"));
        assertFalse(PasswordUtil.isValidPassword("               "));
        assertFalse(PasswordUtil.isValidPassword("password1234/"));
        assertFalse(PasswordUtil.isValidPassword("password-1234"));
        assertFalse(PasswordUtil.isValidPassword("password 1234"));

        // valid cases
        assertTrue(PasswordUtil.isValidPassword("p1!@#$%&*()_+=|<>?{}~[]"));
        assertTrue(PasswordUtil.isValidPassword("12345678"));
        assertTrue(PasswordUtil.isValidPassword("12345678901234567890123456789012"));
        assertTrue(PasswordUtil.isValidPassword("password"));
        assertTrue(PasswordUtil.isValidPassword("password12!"));
    }
}
