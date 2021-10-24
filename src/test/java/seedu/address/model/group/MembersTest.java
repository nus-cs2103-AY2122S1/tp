package seedu.address.model.group;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MembersTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Members(null));
    }

}
