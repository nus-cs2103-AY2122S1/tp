package socialite.model.handle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TikTokTest {
    @Test
    public void isValidHandle() {
        // check empty string and spaces
        assertFalse(TikTok.isValidHandle(""));
        assertFalse(TikTok.isValidHandle("  "));

        // test invalid names, wrong lengths and non alphanumerical or '_'
        assertFalse(TikTok.isValidHandle("bob^by"));
        assertFalse(TikTok.isValidHandle("bob$%"));

        // valid names
        assertTrue(TikTok.isValidHandle("bobb"));
        assertTrue(TikTok.isValidHandle("BOBBY"));
        assertTrue(TikTok.isValidHandle("bobby_boy"));
        assertTrue(TikTok.isValidHandle("bobby.boy"));
        assertTrue(TikTok.isValidHandle("bObbY_boy99"));
        assertTrue(TikTok.isValidHandle("bObbY.boy99"));
        assertTrue(TikTok.isValidHandle("12345"));

    }

    @Test
    public void hasLinkPrefix() {
        TikTok t = new TikTok("name1");
        assertEquals("https://www.tiktok.com/@name1", t.getUrl());
    }

    @Test
    public void testEquals() {
        TikTok tt1 = new TikTok("validhandle");
        TikTok tt2 = new TikTok("validhandle");
        assertEquals(tt1, tt2);
        assertEquals(tt1.hashCode(), tt2.hashCode());
    }
}
