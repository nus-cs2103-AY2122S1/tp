package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_G1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_G2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPONAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_G2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP1;
import static seedu.address.testutil.TypicalGroups.GROUP2;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

public class GroupTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Group group = new GroupBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> group.getTags().remove(0));
    }

    @Test
    public void isSameGroup() {
        // same object -> returns true
        assertTrue(GROUP1.isSameGroup(GROUP1));

        // null -> returns false
        assertFalse(GROUP1.isSameGroup(null));

        Group editedGroup1 = new GroupBuilder(GROUP1).withName(VALID_GROUPNAME_G1).build();
        assertTrue(GROUP1.isSameGroup(editedGroup1));

        // different name, all other attributes same -> returns false
        editedGroup1 = new GroupBuilder(GROUP1).withName(VALID_GROUPNAME_G2).build();
        assertFalse(GROUP1.isSameGroup(editedGroup1));

        // name differs in case, all other attributes same -> returns true
        Group editedGroup2 = new GroupBuilder(GROUP2).withName(VALID_GROUPNAME_G2.toLowerCase()).build();
        assertTrue(GROUP2.isSameGroup(editedGroup2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Group group1Copy = new GroupBuilder(GROUP1).build();
        assertTrue(GROUP1.equals(group1Copy));

        // same object -> returns true
        assertTrue(GROUP1.equals(GROUP1));

        // null -> returns false
        assertFalse(GROUP1.equals(null));

        // different type -> returns false
        assertFalse(GROUP1.equals(5));

        // different student -> returns false
        assertFalse(GROUP1.equals(GROUP2));

        // different name -> returns false
        Group editedGroup1 = new GroupBuilder(GROUP1).withName(VALID_GROUPNAME_G2).build();
        assertFalse(GROUP1.equals(editedGroup1));

        // different members -> returns false
        editedGroup1 = new GroupBuilder(GROUP1).withMembers(ALICE).build();
        assertFalse(GROUP1.equals(editedGroup1));

        // different year -> returns false
        editedGroup1 = new GroupBuilder(GROUP1).withYear(VALID_YEAR_G2).build();
        assertFalse(GROUP1.equals(editedGroup1));

        // different reponame -> returns false
        editedGroup1 = new GroupBuilder(GROUP1).withRepo(VALID_REPONAME_BOB).build();
        assertFalse(GROUP1.equals(editedGroup1));

        // different tags -> returns false
        editedGroup1 = new GroupBuilder(GROUP1).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(GROUP1.equals(editedGroup1));
    }
}
