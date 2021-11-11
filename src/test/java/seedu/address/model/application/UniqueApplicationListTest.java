package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.GRAB;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.application.exceptions.ApplicationNotFoundException;
import seedu.address.model.application.exceptions.DuplicateApplicationException;
import seedu.address.testutil.ApplicationBuilder;

public class UniqueApplicationListTest {

    private final UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();

    @Test
    public void contains_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.contains(null));
    }

    @Test
    public void contains_applicationNotInList_returnsFalse() {
        assertFalse(uniqueApplicationList.contains(AMAZON));
    }

    @Test
    public void contains_applicationInList_returnsTrue() {
        uniqueApplicationList.add(AMAZON);
        assertTrue(uniqueApplicationList.contains(AMAZON));
    }

    @Test
    public void contains_applicationWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApplicationList.add(AMAZON);
        Application editedAlice = new ApplicationBuilder(AMAZON)
                .build();
        assertTrue(uniqueApplicationList.contains(editedAlice));
    }

    @Test
    public void add_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.add(null));
    }

    @Test
    public void add_duplicateApplication_throwsDuplicateApplicationException() {
        uniqueApplicationList.add(AMAZON);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList.add(AMAZON));
    }

    @Test
    public void setApplication_nullTargetApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(null, AMAZON));
    }

    @Test
    public void setApplication_nullEditedApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(AMAZON, null));
    }

    @Test
    public void setApplication_targetApplicationNotInList_throwsApplicationNotFoundException() {
        assertThrows(ApplicationNotFoundException.class, () -> uniqueApplicationList.setApplication(AMAZON, AMAZON));
    }

    @Test
    public void setApplication_editedApplicationIsSameApplication_success() {
        uniqueApplicationList.add(AMAZON);
        uniqueApplicationList.setApplication(AMAZON, AMAZON);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(AMAZON);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplication_editedApplicationHasSameIdentity_success() {
        uniqueApplicationList.add(AMAZON);
        Application editedAlice = new ApplicationBuilder(AMAZON)
                .build();
        uniqueApplicationList.setApplication(AMAZON, editedAlice);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(editedAlice);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplication_editedApplicationHasDifferentIdentity_success() {
        uniqueApplicationList.add(AMAZON);
        uniqueApplicationList.setApplication(AMAZON, BYTEDANCE);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(BYTEDANCE);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplication_editedApplicationHasNonUniqueIdentity_throwsDuplicateApplicationException() {
        uniqueApplicationList.add(AMAZON);
        uniqueApplicationList.add(BYTEDANCE);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList.setApplication(
                AMAZON, BYTEDANCE));
    }

    @Test
    public void remove_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.remove(null));
    }

    @Test
    public void remove_applicationDoesNotExist_throwsApplicationNotFoundException() {
        assertThrows(ApplicationNotFoundException.class, () -> uniqueApplicationList.remove(AMAZON));
    }

    @Test
    public void remove_existingApplication_removesApplication() {
        uniqueApplicationList.add(AMAZON);
        uniqueApplicationList.remove(AMAZON);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplications_nullUniqueApplicationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList
                .setApplications((UniqueApplicationList) null));
    }

    @Test
    public void setApplications_uniqueApplicationList_replacesOwnListWithProvidedUniqueApplicationList() {
        uniqueApplicationList.add(AMAZON);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(BYTEDANCE);
        uniqueApplicationList.setApplications(expectedUniqueApplicationList);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplications_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplications((List<Application>) null));
    }

    @Test
    public void setApplications_list_replacesOwnListWithProvidedList() {
        uniqueApplicationList.add(AMAZON);
        List<Application> applicationList = Collections.singletonList(BYTEDANCE);
        uniqueApplicationList.setApplications(applicationList);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(BYTEDANCE);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplications_listWithDuplicateApplications_throwsDuplicateApplicationException() {
        List<Application> listWithDuplicateApplications = Arrays.asList(AMAZON, AMAZON);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList
                .setApplications(listWithDuplicateApplications));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueApplicationList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(uniqueApplicationList.equals(uniqueApplicationList));

        // null -> return false
        assertFalse(uniqueApplicationList.equals(null));
    }

    @Test
    public void toHashCode_success() {
        uniqueApplicationList.add(AMAZON);
        uniqueApplicationList.add(BYTEDANCE);
        uniqueApplicationList.add(GRAB);
        assertEquals(getTypicalInternship().hashCode(), uniqueApplicationList.hashCode());
    }
}
