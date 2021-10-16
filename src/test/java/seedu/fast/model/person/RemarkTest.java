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

}
