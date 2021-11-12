package seedu.address.model.person.interests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterestTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Interest(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException1() {
        String invalidInterest = "";
        assertThrows(IllegalArgumentException.class, () -> new Interest(invalidInterest));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException2() {
        String invalidInterest = " ";
        assertThrows(IllegalArgumentException.class, () -> new Interest(invalidInterest));
    }

    @Test
    public void isValidInterest() {
        // null Interest
        assertThrows(NullPointerException.class, () -> Interest.isValidInterest(null));

        //invalid Interest
        assertFalse(Interest.isValidInterest("")); //empty string
        assertFalse(Interest.isValidInterest("   ")); //spaces

        //valid Interest
        assertTrue(Interest.isValidInterest("this is valid"));
        assertTrue(Interest.isValidInterest("12873"));
    }

    @Test
    public void equals() {
        Interest interest = new Interest("My interest");

        // same object -> returns true
        assertTrue(interest.equals(interest));

        // same values -> returns true
        Interest interestCopy = new Interest("My interest");
        assertTrue(interest.equals(interestCopy));

        // different types -> returns false
        assertFalse(interest.equals(1));

        // null -> returns false
        assertFalse(interest.equals(null));

        // different remark -> returns false
        Interest differentInterest = new Interest("different interest");
        assertFalse(interest.equals(differentInterest));

        // different capitalisation -> returns true
        Interest differentCapsInterest = new Interest("mY InTeReSt");
        assertTrue(interest.equals(differentCapsInterest));

    }
}
