package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G102;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPTYPE_OP2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.TypicalTutorialGroups.G101_OP1_1;
import static seedu.address.testutil.TypicalTutorialGroups.G102_OP2_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TutorialGroupBuilder;

class TutorialGroupTest {

    @Test
    public void isSameTutorialGroup() {
        // same object -> returns true
        assertTrue(G101_OP1_1.isSameTutorialGroup(G101_OP1_1));

        // null -> returns false
        assertFalse(G101_OP1_1.isSameTutorialGroup(null));

        // same class code, all other attributes different -> returns false
        TutorialGroup editedG101_OP_1 = new TutorialGroupBuilder(G101_OP1_1).withGroupName(VALID_GROUPNAME_2)
                .withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(G101_OP1_1.isSameTutorialGroup(editedG101_OP_1));

        // same group name, all other attributes different -> returns false
        editedG101_OP_1 = new TutorialGroupBuilder(G101_OP1_1).withClassCode(VALID_CLASSCODE_G102)
                .withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(G101_OP1_1.isSameTutorialGroup(editedG101_OP_1));

        // same group type, all other attributes different -> returns false
        editedG101_OP_1 = new TutorialGroupBuilder(G101_OP1_1).withClassCode(VALID_CLASSCODE_G102)
                .withGroupName(VALID_GROUPNAME_2).build();
        assertFalse(G101_OP1_1.isSameTutorialGroup(editedG101_OP_1));

        // different class code, all other attributes same -> returns false
        editedG101_OP_1 = new TutorialGroupBuilder(G101_OP1_1).withClassCode(VALID_NAME_BOB).build();
        assertFalse(G101_OP1_1.isSameTutorialGroup(editedG101_OP_1));

        // different group name, all other attributes same -> returns false
        editedG101_OP_1 = new TutorialGroupBuilder(G101_OP1_1).withGroupName(VALID_GROUPNAME_2).build();
        assertFalse(G101_OP1_1.isSameTutorialGroup(editedG101_OP_1));

        // different group type, all other attributes same -> returns false
        editedG101_OP_1 = new TutorialGroupBuilder(G101_OP1_1).withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(G101_OP1_1.isSameTutorialGroup(editedG101_OP_1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        TutorialGroup G101_OP1_1Copy = new TutorialGroupBuilder(G101_OP1_1).build();
        assertTrue(G101_OP1_1.equals(G101_OP1_1Copy));

        // same object -> returns true
        assertTrue(G101_OP1_1.equals(G101_OP1_1));

        // null -> returns false
        assertFalse(G101_OP1_1.equals(null));

        // different type -> returns false
        assertFalse(G101_OP1_1.equals(5));

        // different groups -> returns false
        assertFalse(G101_OP1_1.equals(G102_OP2_2));

        // different class code -> returns false
        TutorialGroup editedG101_OP1_1 = new TutorialGroupBuilder(G101_OP1_1).withClassCode(VALID_CLASSCODE_G102).build();
        assertFalse(G101_OP1_1.equals(editedG101_OP1_1));

        // different group name -> returns false
        editedG101_OP1_1 = new TutorialGroupBuilder(G101_OP1_1).withGroupName(VALID_GROUPNAME_2).build();
        assertFalse(G101_OP1_1.equals(editedG101_OP1_1));

        // different email -> returns false
        editedG101_OP1_1 = new TutorialGroupBuilder(G101_OP1_1).withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(G101_OP1_1.equals(editedG101_OP1_1));
    }
}