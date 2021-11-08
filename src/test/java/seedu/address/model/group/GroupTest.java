package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DESCRIPTION_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS2103T;
import static seedu.address.testutil.TypicalGroups.TYPICAL_GROUP_CS2101;
import static seedu.address.testutil.TypicalGroups.TYPICAL_GROUP_CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;


public class GroupTest {

    @Test
    public void isSameGroup() {
        // same object -> returns true
        assertTrue(TYPICAL_GROUP_CS2103T.isSameGroup(TYPICAL_GROUP_CS2103T));

        // null -> returns false
        assertFalse(TYPICAL_GROUP_CS2103T.isSameGroup(null));

        // same name, all other attributes different -> returns true
        Group editedDefaultGroup = new GroupBuilder(TYPICAL_GROUP_CS2103T).withDescription(VALID_DESC_BOB).build();
        assertTrue(TYPICAL_GROUP_CS2103T.isSameGroup(editedDefaultGroup));

        // name differs in case, all other attributes same -> returns false
        editedDefaultGroup = new GroupBuilder(TYPICAL_GROUP_CS2103T)
                .withGroupName(VALID_GROUP_NAME_CS2103T.toLowerCase()).build();
        assertFalse(TYPICAL_GROUP_CS2103T.isSameGroup(editedDefaultGroup));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_GROUP_NAME_CS2103T + " ";
        editedDefaultGroup = new GroupBuilder(TYPICAL_GROUP_CS2103T).withGroupName(nameWithTrailingSpaces).build();
        assertFalse(TYPICAL_GROUP_CS2103T.isSameGroup(editedDefaultGroup));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Group defaultCopy = new GroupBuilder(TYPICAL_GROUP_CS2103T).build();
        assertTrue(TYPICAL_GROUP_CS2103T.equals(defaultCopy));

        // same object -> returns true
        assertTrue(TYPICAL_GROUP_CS2103T.equals(TYPICAL_GROUP_CS2103T));

        // null -> returns false
        assertFalse(TYPICAL_GROUP_CS2103T.equals(null));

        // different group -> returns false
        assertFalse(TYPICAL_GROUP_CS2103T.equals(TYPICAL_GROUP_CS2101));

        // different name -> returns false
        Group editedDefaultGroup = new GroupBuilder(TYPICAL_GROUP_CS2103T)
                .withGroupName(VALID_GROUP_NAME_CS1101S).build();
        assertFalse(TYPICAL_GROUP_CS2103T.equals(editedDefaultGroup));

        // different description -> returns false
        editedDefaultGroup = new GroupBuilder(TYPICAL_GROUP_CS2103T)
                .withDescription(VALID_GROUP_DESCRIPTION_CS1101S).build();
        assertFalse(TYPICAL_GROUP_CS2103T.equals(editedDefaultGroup));
    }
}
