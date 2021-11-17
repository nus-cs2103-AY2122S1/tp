package socialite.model.handle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import socialite.testutil.Assert;

public class TwitterTest {
    @Test
    public void isValidHandle() {
        // check null
        Assert.assertThrows(NullPointerException.class, () -> Twitter.isValidHandle(null));

        // check empty string and spaces
        assertFalse(Twitter.isValidHandle(""));
        assertFalse(Twitter.isValidHandle("  "));

        // test invalid names, wrong lengths and non alphanumerical or '_'
        assertFalse(Twitter.isValidHandle("bob"));
        assertFalse(Twitter.isValidHandle("somethingverylong"));
        assertFalse(Twitter.isValidHandle("bob.by"));
        assertFalse(Twitter.isValidHandle("bob$%"));

        // valid names
        assertTrue(Twitter.isValidHandle("bobb"));
        assertTrue(Twitter.isValidHandle("BOBBY"));
        assertTrue(Twitter.isValidHandle("bobby_boy"));
        assertTrue(Twitter.isValidHandle("bObbY_boy99"));
        assertTrue(Twitter.isValidHandle("12345"));
    }

    @Test
    public void hasLinkPrefix() {
        Twitter t = new Twitter("name1");
        assertEquals("https://www.twitter.com/name1", t.getUrl());
    }

    @Test
    public void testEquals() {
        Twitter t1 = new Twitter("validhandle");
        Twitter t2 = new Twitter("validhandle");
        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }
}
