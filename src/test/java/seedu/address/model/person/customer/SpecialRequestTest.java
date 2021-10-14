package seedu.address.model.person.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALREQUEST_ROCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALREQUEST_SILENCE;
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

    @Test
    public void equals() {
        SpecialRequest specialRequest = new SpecialRequest(VALID_SPECIALREQUEST_ROCK);

        // same values -> returns true
        SpecialRequest toCopy = new SpecialRequest(VALID_SPECIALREQUEST_ROCK);
        assertTrue(specialRequest.equals(toCopy));

        // same object -> returns true
        assertTrue(specialRequest.equals(specialRequest));

        // null -> returns false
        assertFalse(specialRequest.equals(null));

        // different type -> returns false
        assertFalse(specialRequest.equals(5));

        // different SpecialRequest -> returns false
        SpecialRequest different = new SpecialRequest(VALID_SPECIALREQUEST_SILENCE);
        assertFalse(specialRequest.equals(different));

    }
}
