package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.getTypicalApplications;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for the Position entity.
 */
public class PositionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Position(null));
    }

    @Test
    public void constructor_invalidPosition_throwsIllegalArgumentException() {
        // position is blank
        String invalidPosition = "";
        assertThrows(IllegalArgumentException.class, () -> new Position(invalidPosition));

        // position too long
        String tooLongPosition = "verylongpositionthatislongerthan40characters";
        assertThrows(IllegalArgumentException.class, () -> new Position(tooLongPosition));
    }

    @Test
    public void isValidPosition() {
        // null position
        assertThrows(NullPointerException.class, () -> Position.isValidPosition(null));

        // invalid position
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only
        assertFalse(Position.isValidPosition("^")); // only non-alphanumeric characters
        assertFalse(Position.isValidPosition("engineer*")); // contains non-alphanumeric characters
        assertFalse(Position.isValidPosition("verylongpositionthatislongerthan40characters")); // too long

        // valid position
        assertTrue(Position.isValidPosition("photographer")); // alphabets only
        assertTrue(Position.isValidPosition("10th backup dancer")); // alphanumeric characters
        assertTrue(Position.isValidPosition("Photographer")); // with capital letters
        assertTrue(Position.isValidPosition("software engineer assisting intern")); // long positions
    }

    @Test
    public void equals() {
        Position p = new Position("Dancer");

        // same object -> return true
        assertTrue(p.equals(p));

        // null -> return false
        assertFalse(p.equals(null));

        // different position -> return false
        assertFalse(p.equals(new Position("Videographer")));

        // same position -> return true
        assertTrue(p.equals(new Position("Dancer")));
    }

    @Test
    public void getComparator_success() {
        List<Application> applicationList = getTypicalApplications();
        applicationList.sort(Position.getComparator());
    }

    @Test
    public void toHashCode_success() {
        String positionString = "Software Engineer";
        assertEquals(positionString.hashCode(), AMAZON.getPosition().hashCode());
    }

}

