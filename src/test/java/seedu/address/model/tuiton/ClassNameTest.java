package seedu.address.model.tuiton;

import org.junit.jupiter.api.Test;
import seedu.address.model.tuition.ClassName;

import static seedu.address.testutil.Assert.assertThrows;

public class ClassNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassName(null));
    }
}
