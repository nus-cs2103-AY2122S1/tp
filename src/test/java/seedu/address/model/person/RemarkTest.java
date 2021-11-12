package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void isValidRemark() {
        // null remark
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid remark
        assertFalse(Remark.isValidRemark(" ")); // spaces only
        assertFalse(Remark.isValidRemark("Ãgent")); // non-ASCII character

        // valid remark
        assertTrue(Remark.isValidRemark("overseas")); // alphabets only
        assertTrue(Remark.isValidRemark("international student")); // alphabets only with space
        assertTrue(Remark.isValidRemark("12345")); // numbers only
        assertTrue(Remark.isValidRemark("came late by 5mins")); // alphanumeric characters
        assertTrue(Remark.isValidRemark("International student from Malaysia")); // with capital letters
        assertTrue(Remark.isValidRemark("Handed up assignment late on 5th October")); // long remarks
        assertTrue(Remark.isValidRemark("Came to class @2pm")); // with special characters
        assertTrue(Remark.isValidRemark("Will come at ~5pm - 5.15pm")); // with special characters
        assertTrue(Remark.isValidRemark("Assignment #2 handed up")); // with special characters

    }
}
