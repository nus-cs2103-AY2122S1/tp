package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G02;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNUMBER_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPTYPE_OP2;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_01;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_02;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TutorialGroupBuilder;

class TutorialGroupTest {

    @Test
    public void isSameTutorialGroup() {
        // same object -> returns true
        assertTrue(TUT_01.isSameTutorialGroup(TUT_01));

        // null -> returns false
        assertFalse(TUT_01.isSameTutorialGroup(null));

        // same class code, all other attributes different -> returns false
        TutorialGroup editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupNumber(VALID_GROUPNUMBER_2)
                .withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // same group number, all other attributes different -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_CLASSCODE_G01)
                .withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // same group type, all other attributes different -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_CLASSCODE_G02)
                .withGroupNumber(VALID_GROUPNUMBER_2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // different class code, all other attributes same -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_CLASSCODE_G02).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // different group number, all other attributes same -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupNumber(VALID_GROUPNUMBER_2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // different group type, all other attributes same -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));
    }

    @Test
    public void equals() {
        // same values -> returns true
        TutorialGroup tut01Copy = new TutorialGroupBuilder(TUT_01).build();
        assertTrue(TUT_01.equals(tut01Copy));

        // same object -> returns true
        assertTrue(TUT_01.equals(TUT_01));

        // null -> returns false
        assertFalse(TUT_01.equals(null));

        // different type -> returns false
        assertFalse(TUT_01.equals(5));

        // different groups -> returns false
        assertFalse(TUT_01.equals(TUT_02));

        // different class code -> returns false
        TutorialGroup editedTut01 = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_CLASSCODE_G02).build();
        assertFalse(TUT_01.equals(editedTut01));

        // different group number -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupNumber(VALID_GROUPNUMBER_2).build();
        assertFalse(TUT_01.equals(editedTut01));

        // different group type -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(TUT_01.equals(editedTut01));
    }

    @Test
    public void toDisplayString() {
        String tutorialGroupStringOne = "OP1 Grp: 1";
        assertEquals(tutorialGroupStringOne, TUT_01.toDisplayString());

        String tutorialGroupStringTwo = "OP2 Grp: 2";
        assertEquals(tutorialGroupStringTwo, TUT_02.toDisplayString());
    }
}
