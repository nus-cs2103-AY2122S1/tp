package socialite.model.handle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import socialite.testutil.Assert;

public class FacebookTest {

    @Test
    public void isValidHandle() {
        // null Facebook username
        Assert.assertThrows(NullPointerException.class, () -> Facebook.isValidHandle(null));

        // blank Facebook usernames
        assertFalse(Facebook.isValidHandle("")); // empty string
        assertFalse(Facebook.isValidHandle(" ")); // spaces only

        // invalid Facebook usernames
        assertFalse(Facebook.isValidHandle("dave")); // less than 5 characters
        assertFalse(Facebook.isValidHandle(" invalidHandle")); // leading space
        assertFalse(Facebook.isValidHandle("invalidHandle ")); // trailing space
        assertFalse(Facebook.isValidHandle("john_chapman")); // invalid token '_'
        assertFalse(Facebook.isValidHandle("john&chapman")); // invalid token '&'
        assertFalse(Facebook.isValidHandle("john#chapman")); // invalid token '#'
        assertFalse(Facebook.isValidHandle("john.com")); // invalid token ".com"
        assertFalse(Facebook.isValidHandle("john.net")); // invalid token ".net"

        // invalid Facebook usernames with incorrect placement of periods
        assertFalse(Facebook.isValidHandle("john..doe")); // cannot have consecutive periods
        assertFalse(Facebook.isValidHandle("john...doe")); // cannot have consecutive periods
        assertFalse(Facebook.isValidHandle(".johnny")); // cannot begin with '.'
        assertFalse(Facebook.isValidHandle("johnny.")); // cannot end with '.'
        assertFalse(Facebook.isValidHandle(".johnny.")); // cannot start and end with '.'

        // valid Facebook usernames
        assertTrue(Facebook.isValidHandle("john1")); // at least 5 characters
        assertTrue(Facebook.isValidHandle("jo.hn")); // at least 5 characters with full stop
        assertTrue(Facebook.isValidHandle("john.common")); // extends beyond ".com"
        assertTrue(Facebook.isValidHandle("john.network")); // extends beyond ".net"
        assertTrue(Facebook.isValidHandle("sarahWalters1")); // lower & uppercase with digits
        assertTrue(Facebook.isValidHandle("123Steve.3")); // start with digits
        assertTrue(Facebook.isValidHandle("Nat.321")); // start with uppercase letter

    }

    @Test
    public void hasLinkPrefix() {
        Facebook f = new Facebook("name1");
        assertEquals("https://www.facebook.com/name1", f.getUrl());
    }

    @Test
    public void testEquals() {
        Facebook fb1 = new Facebook("validhandle");
        Facebook fb2 = new Facebook("validhandle");
        assertEquals(fb1, fb2);
        assertEquals(fb1.hashCode(), fb2.hashCode());
    }
}
