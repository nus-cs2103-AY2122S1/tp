package socialite.model.handle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void isValidTelegram() {
        // null telegram handle
        // Assert.assertThrows(NullPointerException.class, () -> new Telegram(null));

        // blank telegram handle
        assertFalse(Telegram.isValidHandle("")); // empty string
        assertFalse(Telegram.isValidHandle(" ")); // spaces only

        // invalid handles
        assertFalse(Telegram.isValidHandle("adam")); // less than 5 characters
        assertFalse(Telegram.isValidHandle(" test_handle")); // leading space
        assertFalse(Telegram.isValidHandle("test_handle ")); // trailing space
        assertFalse(Telegram.isValidHandle("john.appleseed")); // dot instead of underscore
        assertFalse(Telegram.isValidHandle("john+appleseed")); // invalid token "+"
        assertFalse(Telegram.isValidHandle("john%appleseed")); // invalid token "%"

        // invalid handles with incorrect placement of underscores
        assertFalse(Telegram.isValidHandle("john__doe")); // cannot have consecutive '_'
        assertFalse(Telegram.isValidHandle("john___doe")); // cannot have consecutive '_'
        assertFalse(Telegram.isValidHandle("_johnny")); // cannot begin with '_'
        assertFalse(Telegram.isValidHandle("johnny_")); // cannot end with '_'
        assertFalse(Telegram.isValidHandle("_johnny_")); // cannot start and end with '_'

        // valid handles
        assertTrue(Telegram.isValidHandle("abcde")); // 5 characters
        assertTrue(Telegram.isValidHandle("ab_cd")); // 5 characters including underscore
        assertTrue(Telegram.isValidHandle("john_appleseed")); // alphanumerical and underscore

        // random names generated
        assertTrue(Telegram.isValidHandle("Jasmin_Lutz"));
        assertTrue(Telegram.isValidHandle("kayleebrady"));
        assertTrue(Telegram.isValidHandle("kumani_rowland"));
        assertTrue(Telegram.isValidHandle("rocky_jeffery"));
        assertTrue(Telegram.isValidHandle("skyla_moss"));
        assertTrue(Telegram.isValidHandle("choiyoungbae"));
    }

    @Test
    public void hasLinkPrefix() {
        Telegram t = new Telegram("name1");
        assertEquals("https://www.t.me/name1", t.getUrl());
    }

    @Test
    public void testEquals() {
        Telegram t1 = new Telegram("validhandle");
        Telegram t2 = new Telegram("validhandle");
        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }
}
