package seedu.awe.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalGroups.AMSTERDAM;
import static seedu.awe.testutil.TypicalGroups.BALI;
import static seedu.awe.testutil.TypicalPersons.HOON;
import static seedu.awe.testutil.TypicalPersons.IDA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.awe.model.group.exceptions.DuplicateGroupException;
import seedu.awe.model.group.exceptions.GroupNotFoundException;
import seedu.awe.testutil.GroupBuilder;

public class UniqueGroupListTest {
    private final UniqueGroupList uniqueGroupList = new UniqueGroupList();

    @Test
    public void contains_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.contains(null));
    }

    @Test
    public void contains_groupNotInList_returnsFalse() {
        assertFalse(uniqueGroupList.contains(BALI));
    }

    @Test
    public void contains_groupInList_returnsTrue() {
        uniqueGroupList.add(BALI);
        assertTrue(uniqueGroupList.contains(BALI));
    }

    @Test
    public void contains_groupWithSameIdentityFieldsInList_returnsTrue() {
        uniqueGroupList.add(BALI);
        Group editedBali = new GroupBuilder(BALI).withMembers(IDA, HOON).build();
        assertTrue(uniqueGroupList.contains(editedBali));
    }

    @Test
    public void add_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.add(null));
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateGroupException() {
        uniqueGroupList.add(BALI);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.add(BALI));
    }

    @Test
    public void setGroup_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroup(null, BALI));
    }

    @Test
    public void setGroup_nullEditedGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroup(BALI, null));
    }

    @Test
    public void setGroup_targetGroupNotInList_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.setGroup(BALI, BALI));
    }

    @Test
    public void setGroup_editedGroupIsSameGroup_success() {
        uniqueGroupList.add(BALI);
        uniqueGroupList.setGroup(BALI, BALI);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(BALI);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasSameIdentity_success() {
        uniqueGroupList.add(BALI);
        Group editedBali = new GroupBuilder(BALI).withTags("Business").build();
        uniqueGroupList.setGroup(BALI, editedBali);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(editedBali);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasDifferentIdentity_success() {
        uniqueGroupList.add(BALI);
        uniqueGroupList.setGroup(BALI, AMSTERDAM);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(AMSTERDAM);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasNonUniqueIdentity_throwsDuplicateGroupException()
            throws DuplicateGroupException {
        uniqueGroupList.add(BALI);
        uniqueGroupList.add(AMSTERDAM);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.setGroup(BALI, AMSTERDAM));
    }

    @Test
    public void remove_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.remove(null));
    }

    @Test
    public void remove_groupDoesNotExist_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.remove(BALI));
    }

    @Test
    public void remove_existingGroup_removesGroup() {
        uniqueGroupList.add(BALI);
        uniqueGroupList.remove(BALI);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_nullUniqueGroupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((UniqueGroupList) null));
    }

    @Test
    public void setGroups_uniqueGroupList_replacesOwnListWithProvidedUniqueGroupsList() {
        uniqueGroupList.add(BALI);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(AMSTERDAM);
        uniqueGroupList.setGroups(expectedUniqueGroupList);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((List<Group>) null));
    }

    @Test
    public void setGroups_list_replacesOwnListWithProvidedList() {
        uniqueGroupList.add(BALI);
        List<Group> groupList = Collections.singletonList(AMSTERDAM);
        uniqueGroupList.setGroups(groupList);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(AMSTERDAM);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_listWithDuplicateGroups_throwsDuplicateGroupException() {
        List<Group> listWithDuplicateGroups = Arrays.asList(AMSTERDAM, AMSTERDAM);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.setGroups(listWithDuplicateGroups));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueGroupList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void groupsAreUnique_listWithDuplicateGroups_returnsFalse() {
        List<Group> listWithDuplicateGroups = Arrays.asList(AMSTERDAM, AMSTERDAM);
        assertFalse(uniqueGroupList.groupsAreUnique(listWithDuplicateGroups));
    }

    @Test
    public void groupsAreUnique_listWithoutDuplicateGroups_returnsTrue() {
        List<Group> listWithDuplicateGroups = Arrays.asList(AMSTERDAM, BALI);
        assertTrue(uniqueGroupList.groupsAreUnique(listWithDuplicateGroups));
    }

    @Test
    public void iterator() {
        // Empty List
        assertFalse(uniqueGroupList.iterator().hasNext());

        // Contains 1 group
        uniqueGroupList.add(AMSTERDAM);
        Iterator<Group> uniqueGroupListIterator = uniqueGroupList.iterator();

        ObservableList<Group> singleGroupObservableList = FXCollections.observableArrayList(AMSTERDAM);
        Iterator<Group> singleGroupObservableListIterator = singleGroupObservableList.iterator();

        assertEquals(singleGroupObservableListIterator.next(), uniqueGroupListIterator.next());
        assertFalse(uniqueGroupListIterator.hasNext());

        // Contains > 1 Group
        uniqueGroupList.add(BALI);
        uniqueGroupListIterator = uniqueGroupList.iterator();

        ObservableList<Group> multipleGroupObservableList = FXCollections.observableArrayList(AMSTERDAM, BALI);
        Iterator<Group> multipleGroupObservableListIterator = multipleGroupObservableList.iterator();

        assertEquals(multipleGroupObservableListIterator.next(), uniqueGroupListIterator.next());
        assertEquals(multipleGroupObservableListIterator.next(), uniqueGroupListIterator.next());
        assertFalse(uniqueGroupListIterator.hasNext());
    }

    @Test
    public void equals() {
        // same instance -> true
        assertTrue(uniqueGroupList.equals(uniqueGroupList));

        // null -> false
        assertFalse(uniqueGroupList.equals(null));

        // list is passed in -> false
        assertFalse(uniqueGroupList.equals(new ArrayList<>()));

        // different group -> return false
        UniqueGroupList containExtraGroup = new UniqueGroupList();
        containExtraGroup.add(AMSTERDAM);

        assertFalse(uniqueGroupList.equals(containExtraGroup));

        // contain same amount of person
        uniqueGroupList.add(AMSTERDAM);

        assertTrue(uniqueGroupList.equals(containExtraGroup));
    }
}
