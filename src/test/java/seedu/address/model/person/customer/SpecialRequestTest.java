package seedu.address.model.person.customer;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SpecialRequestTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SpecialRequest(null));
    }

    @Test
    public void constructor_invalidSpecialRequestName_throwsIllegalArgumentException() {
        String invalidSpecialRequestName = "";
        assertThrows(IllegalArgumentException.class, () -> new SpecialRequest(invalidSpecialRequestName));
    }

    @Test
    public void isValidSpecialRequestName() {
        // null SpecialRequest name
        assertThrows(NullPointerException.class, () -> SpecialRequest.isValidSpecialRequestName(null));
    }

}
