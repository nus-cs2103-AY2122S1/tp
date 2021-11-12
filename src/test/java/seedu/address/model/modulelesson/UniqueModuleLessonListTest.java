package seedu.address.model.modulelesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalModuleLessons.CS2100_LAB1;
import static seedu.address.testutil.TypicalModuleLessons.CS2106_TUT1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.modulelesson.exceptions.DuplicateModuleLessonException;
import seedu.address.model.modulelesson.exceptions.ModuleLessonNotFoundException;
import seedu.address.testutil.ModuleLessonBuilder;

public class UniqueModuleLessonListTest {

    private final UniqueModuleLessonList uniqueLessonList = new UniqueModuleLessonList();

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.contains(null));
    }

    @Test
    public void contains_lessonNotInList_returnsFalse() {
        assertFalse(uniqueLessonList.contains(CS2100_LAB1));
    }

    @Test
    public void contains_lessonInList_returnsTrue() {
        uniqueLessonList.add(CS2100_LAB1);
        assertTrue(uniqueLessonList.contains(CS2100_LAB1));
    }

    @Test
    public void contains_lessonWithSameIdentityFieldsInList_returnsTrue() {
        uniqueLessonList.add(CS2100_LAB1);
        ModuleLesson editedCS2100Lab1 = new ModuleLessonBuilder(CS2100_LAB1).withRemark("hello").build();
        assertTrue(uniqueLessonList.contains(editedCS2100Lab1));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.add(null));
    }

    @Test
    public void add_duplicateModuleLesson_throwsDuplicateModuleLessonException() {
        uniqueLessonList.add(CS2100_LAB1);
        assertThrows(DuplicateModuleLessonException.class, () -> uniqueLessonList.add(CS2100_LAB1));
    }

    @Test
    public void setModuleLesson_nullTargetModuleLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.setModuleLesson(null, CS2100_LAB1));
    }

    @Test
    public void setModuleLesson_nullEditedModuleLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.setModuleLesson(CS2100_LAB1, null));
    }

    @Test
    public void setModuleLesson_targetLessonNotInList_throwsPersonNotFoundException() {
        assertThrows(ModuleLessonNotFoundException.class, () -> uniqueLessonList.setModuleLesson(
                CS2100_LAB1, CS2100_LAB1));
    }

    @Test
    public void setModuleLesson_editedLessonIsSameModuleLesson_success() {
        uniqueLessonList.add(CS2100_LAB1);
        uniqueLessonList.setModuleLesson(CS2100_LAB1, CS2100_LAB1);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        expectedUniqueClassList.add(CS2100_LAB1);
        assertEquals(expectedUniqueClassList, uniqueLessonList);
    }

    @Test
    public void setLesson_editedModuleLessonHasDifferentIdentity_success() {
        uniqueLessonList.add(CS2100_LAB1);
        uniqueLessonList.setModuleLesson(CS2100_LAB1, CS2106_TUT1);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        expectedUniqueClassList.add(CS2106_TUT1);
        assertEquals(expectedUniqueClassList, uniqueLessonList);
    }

    @Test
    public void setModuleLesson_editedLessonHasNonUniqueIdentity_throwsDuplicateModuleLessonException() {
        uniqueLessonList.add(CS2100_LAB1);
        uniqueLessonList.add(CS2106_TUT1);
        assertThrows(DuplicateModuleLessonException.class, () -> uniqueLessonList.setModuleLesson(
                CS2100_LAB1, CS2106_TUT1));
    }

    @Test
    public void remove_nullModuleLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.remove(null));
    }

    @Test
    public void remove_moduleLessonDoesNotExist_throwsModuleLessonNotFoundException() {
        assertThrows(ModuleLessonNotFoundException.class, () -> uniqueLessonList.remove(CS2100_LAB1));
    }

    @Test
    public void remove_existingLesson_removesModuleLesson() {
        uniqueLessonList.add(CS2100_LAB1);
        uniqueLessonList.remove(CS2100_LAB1);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        assertEquals(expectedUniqueClassList, uniqueLessonList);
    }

    @Test
    public void setModuleLesson_nullUniqueModuleLessonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList
                .setModuleLessons((UniqueModuleLessonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueLessonList.add(CS2100_LAB1);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        expectedUniqueClassList.add(CS2106_TUT1);
        uniqueLessonList.setModuleLessons(expectedUniqueClassList);
        assertEquals(expectedUniqueClassList, uniqueLessonList);
    }

    @Test
    public void setModuleLessons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.setModuleLessons((List<ModuleLesson>) null));
    }

    @Test
    public void setModuleLessons_list_replacesOwnListWithProvidedList() {
        uniqueLessonList.add(CS2100_LAB1);
        List<ModuleLesson> classList = Collections.singletonList(CS2106_TUT1);
        uniqueLessonList.setModuleLessons(classList);
        UniqueModuleLessonList expectedUniqueClassList = new UniqueModuleLessonList();
        expectedUniqueClassList.add(CS2106_TUT1);
        assertEquals(expectedUniqueClassList, uniqueLessonList);
    }

    @Test
    public void setLessons_listWithDuplicateModuleLessons_throwsDuplicateModuleLessonException() {
        List<ModuleLesson> listWithDuplicateClasses = Arrays.asList(CS2100_LAB1, CS2100_LAB1);
        assertThrows(DuplicateModuleLessonException.class, () -> uniqueLessonList.setModuleLessons(
                listWithDuplicateClasses));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueLessonList.asUnmodifiableObservableList().remove(0));
    }

}
