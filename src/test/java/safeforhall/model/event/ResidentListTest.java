package safeforhall.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import safeforhall.logic.parser.exceptions.ParseException;

public class ResidentListTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ResidentList(null));
    }

    @Test
    public void constructor_nameRoomConflict_throwsIllegalArgumentException() {
        assertThrows(ParseException.class, () ->
                ResidentList.isValidResidentList("Peter, a213")); // name and room
    }

    @Test
    public void isValidResidentList() throws ParseException {
        // null name
        assertThrows(NullPointerException.class, () -> ResidentList.isValidResidentList(null));

        // invalid name
        assertFalse(ResidentList.isValidResidentList(" ")); // spaces only
        assertFalse(ResidentList.isValidResidentList("a213 b423")); // no comma between information

        // valid name
        assertTrue(ResidentList.isValidResidentList("")); // empty string
        assertTrue(ResidentList.isValidResidentList("peter jack")); // alphabets only
        assertTrue(ResidentList.isValidResidentList("Capital Tan")); // with capital letters
        assertTrue(ResidentList.isValidResidentList("peter jack, Capital Tan")); // more than one name
        assertTrue(ResidentList.isValidResidentList("peter jack,Capital Tan")); // comma no space
        assertTrue(ResidentList.isValidResidentList("a213")); // rooms only
        assertTrue(ResidentList.isValidResidentList("A213")); // rooms capital
        assertTrue(ResidentList.isValidResidentList("A213, b423")); // more than one room
    }
}
