package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G02;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_01;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorialgroup.exceptions.DuplicateTutorialGroupException;
import seedu.address.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;
import seedu.address.testutil.TutorialGroupBuilder;

public class UniqueTutorialGroupListTest {

    private final UniqueTutorialGroupList uniqueTutorialGroupList = new UniqueTutorialGroupList();

    @Test
    public void contains_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.contains(null));
    }

    @Test
    public void contains_tutorialGroupNotInList_returnsFalse() {
        assertFalse(uniqueTutorialGroupList.contains(TUT_01));
    }

    @Test
    public void contains_tutorialGroupInList_returnsTrue() {
        uniqueTutorialGroupList.add(TUT_01);
        assertTrue(uniqueTutorialGroupList.contains(TUT_01));
    }

    @Test
    public void contains_tutorialGroupWithDifferentClassCode_returnsFalse() {
        uniqueTutorialGroupList.add(TUT_01);
        TutorialGroup editedTutorialGroup = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_CLASSCODE_G02)
                .build();
        assertFalse(uniqueTutorialGroupList.contains(editedTutorialGroup));
    }

    @Test
    public void add_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.add(null));
    }

    @Test
    public void add_duplicateTutorialGroup_throwsDuplicateTutorialGroupException() {
        uniqueTutorialGroupList.add(TUT_01);
        assertThrows(DuplicateTutorialGroupException.class, () -> uniqueTutorialGroupList.add(TUT_01));
    }

    @Test
    public void remove_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialGroupList.remove(null));
    }

    @Test
    public void remove_tutorialGroupDoesNotExist_throwsTutorialGroupNotFoundException() {
        assertThrows(TutorialGroupNotFoundException.class, () -> uniqueTutorialGroupList.remove(TUT_01));
    }

    @Test
    public void remove_existingTutorialGroup_removesTutorialGroup() {
        uniqueTutorialGroupList.add(TUT_01);
        uniqueTutorialGroupList.remove(TUT_01);
        UniqueTutorialGroupList expectedUniqueTutorialGroupList = new UniqueTutorialGroupList();
        assertEquals(expectedUniqueTutorialGroupList, uniqueTutorialGroupList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTutorialGroupList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        assertTrue(uniqueTutorialGroupList.equals(uniqueTutorialGroupList));

        assertFalse(uniqueTutorialGroupList.equals(null));
        assertFalse(uniqueTutorialGroupList.equals(1));

        UniqueTutorialGroupList copy = new UniqueTutorialGroupList();
        copy.add(TUT_01);
        assertFalse(uniqueTutorialGroupList.equals(copy));

    }
}
