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
        // null address
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid addresses
        assertTrue(Address.isValidAddress(
                "This is a string that contains seventy-six characters which shouldn't pass. ")); // 76 chara

        // valid addresses
        assertTrue(Remark.isValidRemark("")); // empty string
        assertTrue(Remark.isValidRemark(" ")); // spaces only
        assertTrue(Address.isValidAddress("Likes to eat")); // less than 20 chara
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress(
                "This is a string that contains seventy-four characters. which should pass. ")); // 75 chara

    }

}
