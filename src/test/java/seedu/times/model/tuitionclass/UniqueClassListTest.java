package seedu.times.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.testutil.Assert.assertThrows;
import static seedu.times.testutil.TypicalTimestable.JC_CHEMISTRY;
import static seedu.times.testutil.TypicalTimestable.JC_MATHS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.times.model.tuitionclass.exceptions.DuplicateClassException;
import seedu.times.model.tuitionclass.exceptions.OverlappingClassException;
import seedu.times.model.tuitionclass.exceptions.TuitionClassNotFoundException;
import seedu.times.testutil.TuitionClassBuilder;

public class UniqueClassListTest {
    private final UniqueClassList uniqueClassList = new UniqueClassList();

    @Test
    public void contains_nullTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.contains(null));
    }

    @Test
    public void contains_tuitionClassNotInList_returnsFalse() {
        assertFalse(uniqueClassList.contains(JC_CHEMISTRY));
    }

    @Test
    public void contains_tuitionClassInList_returnsTrue() {
        uniqueClassList.add(JC_CHEMISTRY);
        assertTrue(uniqueClassList.contains(JC_CHEMISTRY));
    }

    @Test
    public void contains_tuitionClassWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClassList.add(JC_CHEMISTRY);
        TuitionClass editedClass = new TuitionClassBuilder(JC_CHEMISTRY).withRate("69")
                .withLocation("Punggol Central Waterway Point #2-14").build();
        assertTrue(uniqueClassList.contains(editedClass));
    }

    @Test
    public void add_nullTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.add(null));
    }

    @Test
    public void add_duplicateTuitionClass_throwsInvalidClassException() {
        uniqueClassList.add(JC_CHEMISTRY);
        assertThrows(OverlappingClassException.class, () -> uniqueClassList.add(JC_CHEMISTRY));
    }

    @Test
    public void setClass_nullTargetTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClass(null, JC_CHEMISTRY));
    }

    @Test
    public void setClass_nullEditedTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClass(JC_CHEMISTRY, null));
    }

    @Test
    public void setClass_targetTuitionClassNotInList_throwsTuitionClassNotFoundException() {
        assertThrows(TuitionClassNotFoundException.class, () -> uniqueClassList.setClass(JC_CHEMISTRY, JC_CHEMISTRY));
    }

    @Test
    public void setClass_editedTuitionClassIsSameTuitionClass_success() {
        uniqueClassList.add(JC_CHEMISTRY);
        uniqueClassList.setClass(JC_CHEMISTRY, JC_CHEMISTRY);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        expectedUniqueClassList.add(JC_CHEMISTRY);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClass_editedTuitionClassHasSameIdentity_success() {
        uniqueClassList.add(JC_CHEMISTRY);
        TuitionClass editedChem = new TuitionClassBuilder(JC_CHEMISTRY).withRate("69")
                .withLocation("Punggol Central Waterway Point #2-14").build();
        uniqueClassList.setClass(JC_CHEMISTRY, editedChem);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        expectedUniqueClassList.add(editedChem);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClass_editedTuitionClassHasDifferentIdentity_success() {
        uniqueClassList.add(JC_CHEMISTRY);
        uniqueClassList.setClass(JC_CHEMISTRY, JC_MATHS);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        expectedUniqueClassList.add(JC_MATHS);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClass_editedTuitionClassHasNonUniqueIdentity_throwsDuplicateClassException() {
        uniqueClassList.add(JC_CHEMISTRY);
        uniqueClassList.add(JC_MATHS);
        assertThrows(DuplicateClassException.class, () -> uniqueClassList.setClass(JC_CHEMISTRY, JC_MATHS));
    }

    @Test
    public void delete_nullTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.delete(null));
    }

    @Test
    public void delete_tuitionClassDoesNotExist_throwsTuitionClassNotFoundException() {
        assertThrows(TuitionClassNotFoundException.class, () -> uniqueClassList.delete(JC_CHEMISTRY));
    }

    @Test
    public void delete_existingTuitionClass_removesTuitionClass() {
        uniqueClassList.add(JC_CHEMISTRY);
        uniqueClassList.delete(JC_CHEMISTRY);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        assertEquals(uniqueClassList, expectedUniqueClassList);
    }

    @Test
    public void setClasses_nullUniqueClassList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClasses((List<TuitionClass>) null));
    }

    @Test
    public void setClasses_uniqueClassList_replacesOwnListWithProvidedList() {
        uniqueClassList.add(JC_CHEMISTRY);
        List<TuitionClass> tuitionClassList = new ArrayList<>();
        tuitionClassList.add(JC_MATHS);
        uniqueClassList.setClasses(tuitionClassList);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        expectedUniqueClassList.add(JC_MATHS);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClasses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClasses((List<TuitionClass>) null));
    }

    @Test
    public void setClasses_listWithDuplicateTuitionClass_throwsDuplicateClassException() {
        List<TuitionClass> listWithDuplicateTuitionClass = Arrays.asList(JC_CHEMISTRY, JC_CHEMISTRY);
        assertThrows(DuplicateClassException.class, () -> uniqueClassList.setClasses(listWithDuplicateTuitionClass));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueClassList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals_twoUniqueClassListSameClassesDifferentOrder_notEquals() {
        uniqueClassList.add(JC_CHEMISTRY);
        uniqueClassList.add(JC_MATHS);
        UniqueClassList uniqueClassList2 = new UniqueClassList();
        uniqueClassList2.add(JC_MATHS);
        uniqueClassList2.add(JC_CHEMISTRY);
        assertFalse(uniqueClassList.equals(uniqueClassList2));
    }
}
