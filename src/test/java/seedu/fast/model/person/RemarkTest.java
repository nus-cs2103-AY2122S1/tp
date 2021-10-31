package seedu.fast.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void hashcode() {
        Remark standard = new Remark("");
        Remark remarkWithSameValue = new Remark("");
        Remark remarkWithDifferentValue = new Remark("Test");

        assertTrue(standard.hashCode() == remarkWithSameValue.hashCode());
        assertTrue(standard.hashCode() == standard.hashCode());

        assertFalse(standard.hashCode() == remarkWithDifferentValue.hashCode());
    }

    @Test
    public void isValidRemark() {
        // null remark
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid remarks
        assertFalse(Remark.isValidRemark(
                "this is a fourty-six chara string that fails. ")); // 46 chara

        // valid remarks
        assertTrue(Remark.isValidRemark(LengthUtil.EMPTY_STRING)); // empty string
        assertTrue(Remark.isValidRemark(LengthUtil.WHITE_SPACE_STRING)); // spaces only
        assertTrue(Remark.isValidRemark("Likes to eat")); // less than 20 chara
        assertTrue(Remark.isValidRemark(LengthUtil.ONE_CHARA)); // one character
        assertTrue(Remark.isValidRemark(
                "this is a fourty-five chara string that pass.")); // 45 chara

    }

}
