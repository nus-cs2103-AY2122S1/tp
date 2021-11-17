package socialite.model.handle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import socialite.testutil.Assert;

public class InstagramTest {

    @Test
    public void isValidHandle() {
        // null Instagram handle
        Assert.assertThrows(NullPointerException.class, () -> Instagram.isValidHandle(null));

        // blank Instagram handles
        assertFalse(Instagram.isValidHandle("")); // empty string
        assertFalse(Instagram.isValidHandle(" ")); // spaces only

        // invalid Instagram handles
        assertFalse(Instagram.isValidHandle(" invalidHandle")); // leading space
        assertFalse(Instagram.isValidHandle("invalidHandle ")); // trailing space
        assertFalse(Instagram.isValidHandle("john!chapman")); // invalid token '!'
        assertFalse(Instagram.isValidHandle("john&chapman")); // invalid token '&'
        assertFalse(Instagram.isValidHandle("john#chapman")); // invalid token '#'
        assertFalse(Instagram.isValidHandle("john-chapman")); // invalid token '-'
        assertFalse(Instagram.isValidHandle("ThisIsALongHandleThatShouldBeInvalid")); // exceeds max length

        // invalid Instagram handles with incorrect placement of periods
        assertFalse(Instagram.isValidHandle("john..doe")); // cannot have consecutive periods
        assertFalse(Instagram.isValidHandle("john...doe")); // cannot have consecutive periods
        assertFalse(Instagram.isValidHandle(".johnny")); // cannot begin with '.'
        assertFalse(Instagram.isValidHandle("johnny.")); // cannot end with '.'
        assertFalse(Instagram.isValidHandle(".johnny.")); // cannot start and end with '.'

        // invalid Instagram handles with incorrect placement of underscores
        assertFalse(Instagram.isValidHandle("john__doe")); // cannot have consecutive '_'
        assertFalse(Instagram.isValidHandle("john___doe")); // cannot have consecutive '_'
        assertFalse(Instagram.isValidHandle("_johnny")); // cannot begin with '_'
        assertFalse(Instagram.isValidHandle("johnny_")); // cannot end with '_'
        assertFalse(Instagram.isValidHandle("_johnny_")); // cannot start and end with '_'

        // invalid Instagram handles with incorrect placement of acceptable special characters
        assertFalse(Instagram.isValidHandle("john_.doe")); // cannot have consecutive special characters
        assertFalse(Instagram.isValidHandle("john._doe")); // cannot have consecutive special characters

        // valid Instagram handles
        assertTrue(Instagram.isValidHandle("1")); // at least 1 character
        assertTrue(Instagram.isValidHandle("jo.hn")); // full stop
        assertTrue(Instagram.isValidHandle("john_chapman")); // underscore
        assertTrue(Instagram.isValidHandle("sarahWalters1")); // lower & uppercase with digits
        assertTrue(Instagram.isValidHandle("123Steve.3")); // start with digits
        assertTrue(Instagram.isValidHandle("Nat_321")); // start with uppercase letter

    }
    @Test
    public void hasLinkPrefix() {
        Instagram i = new Instagram("name1");
        assertEquals("https://www.instagram.com/name1", i.getUrl());
    }

    @Test
    public void testEquals() {
        Instagram ig1 = new Instagram("validhandle");
        Instagram ig2 = new Instagram("validhandle");
        assertEquals(ig1, ig2);
        assertEquals(ig1.hashCode(), ig2.hashCode());
    }
}
