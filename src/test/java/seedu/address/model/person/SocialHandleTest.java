package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SocialHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SocialHandle(null));
    }

    @Test
    public void isValidSocialHandle() {
        // null socialHandle
        assertThrows(NullPointerException.class, () -> SocialHandle.isValidSocialHandle(null));

        // invalid socialHandle
        assertFalse(SocialHandle.isValidSocialHandle(" ")); // spaces only
        assertFalse(SocialHandle.isValidSocialHandle("ife:hello")); // platform is more than 2 char
        assertFalse(SocialHandle.isValidSocialHandle("i:hello")); // platform is less than 2 char
        assertFalse(SocialHandle.isValidSocialHandle("ig:fewf fewfef")); // contains space in handle
        assertFalse(SocialHandle.isValidSocialHandle("fewfef")); // no platform
        assertFalse(SocialHandle.isValidSocialHandle("ig:")); // no value specified
        assertFalse(SocialHandle.isValidSocialHandle("")); //no social handle

        // invalid platform
        assertFalse(SocialHandle.isValidPlatform("t"));
        assertFalse(SocialHandle.isValidPlatform("instagram")); // for instagram
        assertFalse(SocialHandle.isValidPlatform("telegram"));

        // invalid value
        assertFalse(SocialHandle.isValidValue("efefe ewfefe"));

        // valid socialHandle
        assertTrue(SocialHandle.isValidSocialHandle("telegram:wdf87ewew8f"));
        assertTrue(SocialHandle.isValidSocialHandle("fb:dssd"));
        assertTrue(SocialHandle.isValidSocialHandle("sc:32323"));
        assertTrue(SocialHandle.isValidSocialHandle("tw:dsaw2"));
        assertTrue(SocialHandle.isValidSocialHandle("gh:0o303e0"));
        assertTrue(SocialHandle.isValidSocialHandle("dc:dsdswd"));

        // valid platform
        assertTrue(SocialHandle.isValidPlatform("ig")); // for instagram
        assertTrue(SocialHandle.isValidPlatform("tg")); // for telegram
        assertTrue(SocialHandle.isValidPlatform("fb"));
        assertTrue(SocialHandle.isValidPlatform("gh"));
        assertTrue(SocialHandle.isValidPlatform("tw"));
        assertTrue(SocialHandle.isValidPlatform("in"));
        assertTrue(SocialHandle.isValidPlatform("dc"));
        assertTrue(SocialHandle.isValidPlatform("sc"));

        // valid platform
        assertTrue(SocialHandle.isValidValue("t"));
        assertTrue(SocialHandle.isValidValue("deweffi"));

        // parse platform
        assertTrue(SocialHandle.isValidPlatform(SocialHandle.parsePlatform("instagram"))); // for instagram
        assertTrue(SocialHandle.isValidPlatform(SocialHandle.parsePlatform("telegram"))); // for telegram

    }

}
