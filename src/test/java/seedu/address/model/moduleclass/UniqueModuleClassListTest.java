package seedu.address.model.moduleclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalModuleClasses.CS2100_LAB1;
import static seedu.address.testutil.TypicalModuleClasses.CS2100_TUT1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.moduleclass.exceptions.DuplicateModuleClassException;
import seedu.address.model.moduleclass.exceptions.ModuleClassNotFoundException;
import seedu.address.testutil.ModuleClassBuilder;

public class UniqueModuleClassListTest {

    private final UniqueModuleClassList uniqueClassList = new UniqueModuleClassList();

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
        ModuleClass editedCS2100_LAB1 = new ModuleClassBuilder(CS2100_LAB1).withRemark("hello").build();
        assertTrue(uniqueClassList.contains(editedCS2100_LAB1));
    }

    @Test
    public void add_nullModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.add(null));
    }

    @Test
    public void add_duplicateModuleClass_throwsDuplicateModuleClassException() {
        uniqueClassList.add(CS2100_LAB1);
        assertThrows(DuplicateModuleClassException.class, () -> uniqueClassList.add(CS2100_LAB1));
    }

    @Test
    public void setModuleClass_nullTargetModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setModuleClass(null, CS2100_LAB1));
    }

    @Test
    public void setModuleClass_nullEditedModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setModuleClass(CS2100_LAB1, null));
    }

    @Test
    public void setModuleClass_targeClassNotInList_throwsPersonNotFoundException() {
        assertThrows(ModuleClassNotFoundException.class, () -> uniqueClassList.setModuleClass(
                CS2100_LAB1, CS2100_LAB1));
    }

    @Test
    public void setModuleClass_editedClassIsSameModuleClass_success() {
        uniqueClassList.add(CS2100_LAB1);
        uniqueClassList.setModuleClass(CS2100_LAB1, CS2100_LAB1);
        UniqueModuleClassList expectedUniqueClassList = new UniqueModuleClassList();
        expectedUniqueClassList.add(CS2100_LAB1);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClass_editedModuleClassHasDifferentIdentity_success() {
        uniqueClassList.add(CS2100_LAB1);
        uniqueClassList.setModuleClass(CS2100_LAB1, CS2100_TUT1);
        UniqueModuleClassList expectedUniqueClassList = new UniqueModuleClassList();
        expectedUniqueClassList.add(CS2100_TUT1);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setModuleClass_editedClassHasNonUniqueIdentity_throwsDuplicateModuleClassException() {
        uniqueClassList.add(CS2100_LAB1);
        uniqueClassList.add(CS2100_TUT1);
        assertThrows(DuplicateModuleClassException.class, () -> uniqueClassList.setModuleClass(
                CS2100_LAB1, CS2100_TUT1));
    }

    @Test
    public void remove_nullModuleClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.remove(null));
    }

    @Test
    public void remove_ModuleClassDoesNotExist_throwsModuleClassNotFoundException() {
        assertThrows(ModuleClassNotFoundException.class, () -> uniqueClassList.remove(CS2100_LAB1));
    }

    @Test
    public void remove_existingClass_removesModuleClass() {
        uniqueClassList.add(CS2100_LAB1);
        uniqueClassList.remove(CS2100_LAB1);
        UniqueModuleClassList expectedUniqueClassList = new UniqueModuleClassList();
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setModuleClass_nullUniqueModuleClassList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setModuleClasses((UniqueModuleClassList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueClassList.add(CS2100_LAB1);
        UniqueModuleClassList expectedUniqueClassList = new UniqueModuleClassList();
        expectedUniqueClassList.add(CS2100_TUT1);
        uniqueClassList.setModuleClasses(expectedUniqueClassList);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setModuleClasses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setModuleClasses((List<ModuleClass>) null));
    }

    @Test
    public void setModuleClasses_list_replacesOwnListWithProvidedList() {
        uniqueClassList.add(CS2100_LAB1);
        List<ModuleClass> classList = Collections.singletonList(CS2100_TUT1);
        uniqueClassList.setModuleClasses(classList);
        UniqueModuleClassList expectedUniqueClassList = new UniqueModuleClassList();
        expectedUniqueClassList.add(CS2100_TUT1);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClasses_listWithDuplicateModuleClasses_throwsDuplicateModuleClassException() {
        List<ModuleClass> listWithDuplicateClasses = Arrays.asList(CS2100_LAB1, CS2100_LAB1);
        assertThrows(DuplicateModuleClassException.class, () -> uniqueClassList.setModuleClasses(
                listWithDuplicateClasses));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueClassList.asUnmodifiableObservableList().remove(0));
    }

}
