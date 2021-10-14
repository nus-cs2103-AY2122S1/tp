package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_ID_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorialIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialId(null));
    }

    @Test
    public void constructor_invalidTutorialId_throwsIllegalArgumentException() {
        String invalidTutorialId = "";
        assertThrows(IllegalArgumentException.class, () -> new TutorialId(invalidTutorialId));
    }

    @Test
    public void isValidTutorialId() {
        // null TutorialId
        assertThrows(NullPointerException.class, () -> TutorialId.isValidTutorialId(null));

        // blank TutorialId
        assertFalse(TutorialId.isValidTutorialId("")); // empty string
        assertFalse(TutorialId.isValidTutorialId(" ")); // spaces only

        // invalid TutorialId with only 2 digits
        assertFalse(TutorialId.isValidTutorialId("000")); // invalid because it has 3 digits
        assertFalse(TutorialId.isValidTutorialId("16a")); // invalid because its has an extra alphabet
        assertFalse(TutorialId.isValidTutorialId("0a")); // invalid because its has an alphabet
        assertFalse(TutorialId.isValidTutorialId("aa")); // invalid because it does not have 2 digits

        // valid TutorialId
        assertTrue(TutorialId.isValidTutorialId("16"));
    }

    @Test
    public void isEqualTutorialId() {
        TutorialId tutorialId = new TutorialId("16");
        TutorialId differentTutorialId = new TutorialId("00");
        TutorialId sameTutorialId = new TutorialId("16");

        // Different TutorialId
        assertFalse(tutorialId.equals(differentTutorialId));

        // Same Object
        assertTrue(tutorialId.equals(tutorialId));

        // Different Objects Same TutorialId
        assertTrue(tutorialId.equals(sameTutorialId));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(VALID_TUTORIAL_ID_AMY.hashCode(), VALID_TUTORIAL_ID_AMY.hashCode());
        assertNotEquals(VALID_TUTORIAL_ID_AMY.hashCode(), VALID_TUTORIAL_ID_BOB.hashCode());
    }
}
