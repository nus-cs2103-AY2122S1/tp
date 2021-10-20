package seedu.address.model.modulelesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalModuleLessons.CS2100_LAB1;
import static seedu.address.testutil.TypicalModuleLessons.CS2100_TUT1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.modulelesson.exceptions.DuplicateModuleLessonException;
import seedu.address.model.modulelesson.exceptions.ModuleLessonNotFoundException;
import seedu.address.testutil.ModuleLessonBuilder;

public class UniqueModuleLessonListTest {

    private final UniqueModuleLessonList uniqueClassList = new UniqueModuleLessonList();

    @Test
    public void contains_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.contains(null));
    }

    @Test
    public void contains_classNotInList_returnsFalse() {
        assertFalse(uniqueClassList.contains(CS2100_LAB1));
    }

    @Test
    public void contains_classInList_returnsTrue() {
        uniqueClassList.add(CS2100_LAB1);
        assertTrue(uniqueClassList.contains(CS2100_LAB1));
    }

    @Test
    public void contains_classWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClassList.add(CS2100_LAB1);
        ModuleLesson editedCS2100Lab1 = new ModuleLessonBuilder(CS2100_LAB1).withRemark("hello").build();
        assertTrue(uniqueClassList.contains(editedCS2100Lab1));
    }

    @Test
    public void add_nullModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.add(null));
    }

    @Test
    public void add_duplicateModuleClass_throwsDuplicateModuleClassException() {
        uniqueClassList.add(CS2100_LAB1);
        assertThrows(DuplicateModuleLessonException.class, () -> uniqueClassList.add(CS2100_LAB1));
    }

    @Test
    public void setModuleClass_nullTargetModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setModuleLesson(null, CS2100_LAB1));
    }

    @Test
    public void setModuleClass_nullEditedModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setModuleLesson(CS2100_LAB1, null));
    }

    @Test
    public void setModuleClass_targeClassNotInList_throwsPersonNotFoundException() {
        assertThrows(ModuleLessonNotFoundException.class, () -> uniqueClassList.setModuleLesson(
                CS2100_LAB1, CS2100_LAB1));
    }

    @Test
    public void setModuleClass_editedClassIsSameModuleClass_success() {
        uniqueClassList.add(CS2100_LAB1);
        uniqueClassList.setModuleLesson(CS2100_LAB1, CS2100_LAB1);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        expectedUniqueClassList.add(CS2100_LAB1);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClass_editedModuleClassHasDifferentIdentity_success() {
        uniqueClassList.add(CS2100_LAB1);
        uniqueClassList.setModuleLesson(CS2100_LAB1, CS2100_TUT1);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        expectedUniqueClassList.add(CS2100_TUT1);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setModuleClass_editedClassHasNonUniqueIdentity_throwsDuplicateModuleClassException() {
        uniqueClassList.add(CS2100_LAB1);
        uniqueClassList.add(CS2100_TUT1);
        assertThrows(DuplicateModuleLessonException.class, () -> uniqueClassList.setModuleLesson(
                CS2100_LAB1, CS2100_TUT1));
    }

    @Test
    public void remove_nullModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.remove(null));
    }

    @Test
    public void remove_moduleClassDoesNotExist_throwsModuleClassNotFoundException() {
        assertThrows(ModuleLessonNotFoundException.class, () -> uniqueClassList.remove(CS2100_LAB1));
    }

    @Test
    public void remove_existingClass_removesModuleClass() {
        uniqueClassList.add(CS2100_LAB1);
        uniqueClassList.remove(CS2100_LAB1);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setModuleClass_nullUniqueModuleClassList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setModuleLessons((UniqueModuleLessonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueClassList.add(CS2100_LAB1);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        expectedUniqueClassList.add(CS2100_TUT1);
        uniqueClassList.setModuleLessons(expectedUniqueClassList);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setModuleClasses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setModuleLessons((List<ModuleLesson>) null));
    }

    @Test
    public void setModuleClasses_list_replacesOwnListWithProvidedList() {
        uniqueClassList.add(CS2100_LAB1);
        List<ModuleLesson> classList = Collections.singletonList(CS2100_TUT1);
        uniqueClassList.setModuleLessons(classList);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        expectedUniqueClassList.add(CS2100_TUT1);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClasses_listWithDuplicateModuleClasses_throwsDuplicateModuleClassException() {
        List<ModuleLesson> listWithDuplicateClasses = Arrays.asList(CS2100_LAB1, CS2100_LAB1);
        assertThrows(DuplicateModuleLessonException.class, () -> uniqueClassList.setModuleLessons(
                listWithDuplicateClasses));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueClassList.asUnmodifiableObservableList().remove(0));
    }

}
