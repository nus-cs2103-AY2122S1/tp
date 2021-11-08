package seedu.fast.model.tag;

import static seedu.fast.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PriorityTag(null));
    }

}

