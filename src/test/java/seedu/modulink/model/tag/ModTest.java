package seedu.modulink.model.tag;

import static seedu.modulink.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mod(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Mod(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Mod.isValidTagName(null));
    }

}
