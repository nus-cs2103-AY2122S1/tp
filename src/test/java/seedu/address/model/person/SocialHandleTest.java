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

        // valid socialHandle
        assertTrue(SocialHandle.isValidSocialHandle("ig:dwdwfdsddds"));
        assertTrue(SocialHandle.isValidSocialHandle("ig:")); // no value specified
        assertTrue(SocialHandle.isValidSocialHandle("tl:wdf87ewew8f")); // contains number
        assertTrue(SocialHandle.isValidSocialHandle("")); //no social handle
    }

}
