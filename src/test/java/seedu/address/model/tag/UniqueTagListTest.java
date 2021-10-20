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
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.exceptions.DuplicateTagException;
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
    public void addTag_uniqueTag_success() {
        Tag sample = TAG_UNPAID;
        uniqueTagList.addTag(sample);
        List<Tag> expectedList = new ArrayList<>(List.of(sample));
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.setTags(expectedList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertEquals(1, UniqueTagList.getNumStudentsForTag(sample));
    }

    @Test
    public void addTag_duplicateTag_success() {
        Tag tagWithTwoStudents = TAG_UNPAID;
        List<Tag> expectedList = new ArrayList<>(List.of(tagWithTwoStudents));
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.setTags(expectedList);
        uniqueTagList.addTag(tagWithTwoStudents);
        uniqueTagList.addTag(tagWithTwoStudents);
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertEquals(2, UniqueTagList.getNumStudentsForTag(tagWithTwoStudents));
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
        expectedUniqueTagList.setTags(Arrays.asList(tagWithTwoStudents, tagWithOneStudents));
        uniqueTagList.addTagFromPersonList(samplePersonList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertEquals(2, UniqueTagList.getNumStudentsForTag(tagWithTwoStudents));
        assertEquals(1, UniqueTagList.getNumStudentsForTag(tagWithOneStudents));
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
        assertThrows(NullPointerException.class, () -> UniqueTagList.getNumStudentsForTag(sample));
    }

    @Test
    public void removeTag_duplicateTag_success() {
        Tag sample = TAG_UNPAID;
        List<Tag> expectedList = new ArrayList<>(List.of(sample));
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.setTags(expectedList);

        uniqueTagList.addTag(sample);
        uniqueTagList.addTag(sample);
        uniqueTagList.removeTag(sample);
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertEquals(1, UniqueTagList.getNumStudentsForTag(sample));
    }

    @Test
    public void removeTagFromPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.removeTagFromPerson(null));
    }

    @Test
    public void removeTagFromPerson_emptyResultList_success() {
        uniqueTagList.setTags(Arrays.asList(TAG_FORGETFUL, TAG_UNPAID));
        uniqueTagList.removeTagFromPerson(BENSON);
        assertEquals(new UniqueTagList(), uniqueTagList);
        assertThrows(NullPointerException.class, () -> UniqueTagList.getNumStudentsForTag(TAG_FORGETFUL));
        assertThrows(NullPointerException.class, () -> UniqueTagList.getNumStudentsForTag(TAG_UNPAID));
    }

    @Test
    public void removeTagFromPerson_nonEmptyResultList_success() {
        Tag tagToRemove = TAG_FORGETFUL;
        Tag tagRemained = TAG_UNPAID;
        uniqueTagList.setTags(Arrays.asList(tagToRemove, tagRemained));
        uniqueTagList.removeTagFromPerson(ALICE);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.setTags(List.of(tagRemained));
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertEquals(1, UniqueTagList.getNumStudentsForTag(tagRemained));
        assertThrows(NullPointerException.class, () -> UniqueTagList.getNumStudentsForTag(tagToRemove));
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags(null));
    }

    @Test
    public void setTags_replacesOwnListWithProvidedList_success() {
        Tag tagToReplace = TAG_UNPAID;
        uniqueTagList.addTag(tagToReplace);
        Tag replacedTag = TAG_FORGETFUL;
        List<Tag> tagList = Collections.singletonList(replacedTag);
        uniqueTagList.setTags(tagList);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.addTag(replacedTag);
        assertEquals(expectedUniqueTagList, uniqueTagList);
        assertThrows(NullPointerException.class, () -> UniqueTagList.getNumStudentsForTag(tagToReplace));
        assertEquals(1, UniqueTagList.getNumStudentsForTag(replacedTag));
    }

    @Test
    public void setTags_listWithTags_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(TAG_UNPAID, TAG_UNPAID);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicateTags));
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
        assertThrows(NullPointerException.class, () -> UniqueTagList.getNumStudentsForTag(TAG_FORGETFUL));
        assertEquals(1, UniqueTagList.getNumStudentsForTag(TAG_ZOOM));
    }

    @Test
    public void asUnmodifiableTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueTagList.asUnmodifiableTagList().remove(0));
    }

    @Test
    public void asUnmodifiableTagList_sortedList_success() {
        uniqueTagList.setTags(Arrays.asList(TAG_ZOOM, TAG_FORGETFUL));

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
