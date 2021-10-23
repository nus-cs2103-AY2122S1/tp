package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP1;
import static seedu.address.testutil.TypicalGroups.GROUP2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.testutil.GroupBuilder;

public class UniqueGroupListTest {

    private final UniqueGroupList uniqueGroupList = new UniqueGroupList();

    @Test
    public void contains_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.contains(null));
    }

    @Test
    public void contains_groupNotInList_returnsFalse() {
        assertFalse(uniqueGroupList.contains(GROUP1));
    }

    @Test
    public void contains_groupInList_returnsTrue() {
        uniqueGroupList.add(GROUP1);
        assertTrue(uniqueGroupList.contains(GROUP1));
    }

    @Test
    public void contains_groupWithSameIdentityFieldsInList_returnsTrue() {
        uniqueGroupList.add(GROUP1);
        Group editedGroup1 = new GroupBuilder(GROUP1).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(uniqueGroupList.contains(editedGroup1));
    }

    @Test
    public void add_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.add(null));
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateStudentException() {
        uniqueGroupList.add(GROUP1);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.add(GROUP1));
    }

    @Test
    public void setGroup_nullTargetGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroup(null, GROUP1));
    }

    @Test
    public void setGroup_nullEditedGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroup(GROUP1, null));
    }

    @Test
    public void setGroup_targetGroupNotInList_throwsStudentNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.setGroup(GROUP1, GROUP1));
    }

    @Test
    public void setGroup_editedGroupIsSameGroup_success() {
        uniqueGroupList.add(GROUP1);
        uniqueGroupList.setGroup(GROUP1, GROUP1);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(GROUP1);
        assertEquals(uniqueGroupList, this.uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasSameIdentity_success() {
        uniqueGroupList.add(GROUP1);
        Group editedGroup = new GroupBuilder(GROUP1).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueGroupList.setGroup(GROUP1, editedGroup);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(editedGroup);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasDifferentIdentity_success() {
        uniqueGroupList.add(GROUP1);
        uniqueGroupList.setGroup(GROUP1, GROUP2);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(GROUP2);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasNonUniqueIdentity_throwsDuplicateStudentException() {
        uniqueGroupList.add(GROUP1);
        uniqueGroupList.add(GROUP2);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.setGroup(GROUP1, GROUP2));
    }

    @Test
    public void remove_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.remove(null));
    }

    @Test
    public void remove_groupDoesNotExist_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.remove(GROUP1));
    }

    @Test
    public void remove_existingGroup_removesGroup() {
        uniqueGroupList.add(GROUP1);
        uniqueGroupList.remove(GROUP1);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_nullUniqueGroupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((UniqueGroupList) null));
    }

    @Test
    public void setGroups_uniqueGroupList_replacesOwnListWithProvidedUniqueGroupList() {
        uniqueGroupList.add(GROUP1);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(GROUP2);
        uniqueGroupList.setGroups(expectedUniqueGroupList);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((List<Group>) null));
    }

    @Test
    public void setGroups_list_replacesOwnListWithProvidedList() {
        uniqueGroupList.add(GROUP1);
        List<Group> groupList = Collections.singletonList(GROUP2);
        uniqueGroupList.setGroups(groupList);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(GROUP2);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_listWithDuplicateGroups_throwsDuplicateGroupException() {
        List<Group> listWithDuplicateGroups = Arrays.asList(GROUP1, GROUP1);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.setGroups(listWithDuplicateGroups));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueGroupList.asUnmodifiableObservableList().remove(0));
    }
}
