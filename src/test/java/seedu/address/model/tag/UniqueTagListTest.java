package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ZOOM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalTags.TAG_FORGETFUL;
import static seedu.address.testutil.TypicalTags.TAG_UNPAID;
import static seedu.address.testutil.TypicalTags.TAG_ZOOM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.exceptions.TagNotFoundException;
import seedu.address.testutil.PersonBuilder;

class UniqueTagListTest {
    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void containsTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.containsTag(null));
    }

    @Test
    public void containsTag_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.containsTag(TAG_UNPAID));
    }

    @Test
    public void containsTag_tagInList_returnsTrue() {
        Tag sample = TAG_UNPAID;
        uniqueTagList.addTag(sample);
        assertTrue(uniqueTagList.containsTag(sample));
    }

    @Test
    public void addTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.addTag(null));
    }

    @Test
    public void addTagFromPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.addTagFromPerson(null));
    }

    @Test
    public void addTagFromPerson_sorted_success() {
        uniqueTagList.addTagFromPerson(BENSON);

        UniqueTagList unsortedList = new UniqueTagList();
        unsortedList.addTag(TAG_UNPAID);
        unsortedList.addTag(TAG_FORGETFUL);

        UniqueTagList sortedList = new UniqueTagList();
        sortedList.addTag(TAG_FORGETFUL);
        sortedList.addTag(TAG_UNPAID);
        assertNotEquals(unsortedList, uniqueTagList);
        assertEquals(sortedList, uniqueTagList);
    }

    @Test
    public void addTagFromPersonList_nullPersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.addTagFromPerson(null));
    }

    @Test
    public void addTagFromPersonList_success() {
        List<Person> samplePersonList = new ArrayList<>(Arrays.asList(AMY, BOB));
        Tag tagWithTwoStudents = TAG_FORGETFUL;
        Tag tagWithOneStudents = TAG_ZOOM;
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.addTag(tagWithTwoStudents);
        expectedUniqueTagList.addTag(tagWithTwoStudents);
        expectedUniqueTagList.addTag(tagWithOneStudents);

        uniqueTagList.addTagFromPersonList(samplePersonList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertEquals(2, uniqueTagList.getNumStudentsForTag(tagWithTwoStudents));
        assertEquals(1, uniqueTagList.getNumStudentsForTag(tagWithOneStudents));
    }

    @Test
    public void removeTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.removeTag(null));
    }

    @Test
    public void removeTag_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.removeTag(TAG_UNPAID));
    }

    @Test
    public void removeTag_uniqueTag_success() {
        Tag sample = TAG_UNPAID;
        uniqueTagList.addTag(sample);
        uniqueTagList.removeTag(sample);

        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertThrows(NullPointerException.class, () -> uniqueTagList.getNumStudentsForTag(sample));
    }

    @Test
    public void removeTag_duplicateTag_success() {
        Tag sample = TAG_UNPAID;
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.addTag(sample);

        uniqueTagList.addTag(sample);
        uniqueTagList.addTag(sample);
        uniqueTagList.removeTag(sample);
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertEquals(1, uniqueTagList.getNumStudentsForTag(sample));
    }

    @Test
    public void removeTagFromPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.removeTagFromPerson(null));
    }

    @Test
    public void removeTagFromPerson_nonExistentTag_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.removeTag(TAG_UNPAID));
    }

    @Test
    public void removeTagFromPerson_emptyResultList_success() {
        uniqueTagList.addTag(TAG_FORGETFUL);
        uniqueTagList.addTag(TAG_UNPAID);
        uniqueTagList.removeTagFromPerson(BENSON);
        assertEquals(new UniqueTagList(), uniqueTagList);
        assertThrows(NullPointerException.class, () -> uniqueTagList.getNumStudentsForTag(TAG_FORGETFUL));
        assertThrows(NullPointerException.class, () -> uniqueTagList.getNumStudentsForTag(TAG_UNPAID));
    }

    @Test
    public void removeTagFromPerson_nonEmptyResultList_success() {
        Tag tagToRemove = TAG_FORGETFUL;
        Tag tagRemained = TAG_UNPAID;
        uniqueTagList.addTag(tagToRemove);
        uniqueTagList.addTag(tagRemained);
        uniqueTagList.removeTagFromPerson(ALICE);

        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.addTag(tagRemained);

        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertEquals(1, uniqueTagList.getNumStudentsForTag(tagRemained));
        assertThrows(NullPointerException.class, () -> uniqueTagList.getNumStudentsForTag(tagToRemove));
    }

    @Test
    public void editTagFromPerson_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.editTagFromPerson(null, ALICE));
    }

    @Test
    public void editTagFromPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.editTagFromPerson(ALICE, null));
    }

    @Test
    public void editTagFromPerson_bothNullPersons_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.editTagFromPerson(null, null));
    }

    @Test
    public void editTagFromPerson_success() {
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.addTag(TAG_ZOOM);

        uniqueTagList.addTagFromPerson(AMY);
        Person editedPerson = new PersonBuilder().withName(VALID_NAME_AMY).withTags(VALID_TAG_ZOOM).build();
        uniqueTagList.editTagFromPerson(AMY, editedPerson);
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertThrows(NullPointerException.class, () -> uniqueTagList.getNumStudentsForTag(TAG_FORGETFUL));
        assertEquals(1, uniqueTagList.getNumStudentsForTag(TAG_ZOOM));
    }

    @Test
    public void asUnmodifiableTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueTagList.asUnmodifiableTagList().remove(0));
    }

    @Test
    public void asUnmodifiableTagList_sortedList_success() {
        uniqueTagList.addTag(TAG_FORGETFUL);
        uniqueTagList.addTag(TAG_ZOOM);

        ObservableList<Tag> sortedList =
                FXCollections.unmodifiableObservableList(
                        FXCollections.observableList(Arrays.asList(TAG_FORGETFUL, TAG_ZOOM)));
        ObservableList<Tag> unsortedList =
                FXCollections.unmodifiableObservableList(
                        FXCollections.observableArrayList(Arrays.asList(TAG_ZOOM, TAG_FORGETFUL)));
        assertEquals(sortedList, uniqueTagList.asUnmodifiableTagList());
        assertNotEquals(unsortedList, uniqueTagList.asUnmodifiableTagList());
    }

    @Test
    public void hashCode_sameInternalList_success() {
        UniqueTagList resultList = new UniqueTagList();
        resultList.addTag(TAG_FORGETFUL);

        uniqueTagList.addTag(TAG_FORGETFUL);
        assertEquals(resultList.hashCode(), uniqueTagList.hashCode());
    }

    @Test
    public void hashCode_differentInternalListUnequals_success() {
        UniqueTagList resultList = new UniqueTagList();
        resultList.addTag(TAG_FORGETFUL);

        uniqueTagList.addTag(TAG_UNPAID);
        assertNotEquals(resultList, uniqueTagList);
    }

    @Test
    public void equals_sameInternalList_success() {
        UniqueTagList resultList = new UniqueTagList();
        resultList.addTag(TAG_FORGETFUL);

        uniqueTagList.addTag(TAG_FORGETFUL);
        assertEquals(resultList, uniqueTagList);
    }

    @Test
    public void equals_unequalsDifferentInternalList_success() {
        UniqueTagList resultList = new UniqueTagList();
        resultList.addTag(TAG_FORGETFUL);

        uniqueTagList.addTag(TAG_UNPAID);
        assertNotEquals(resultList, uniqueTagList);
    }
}
