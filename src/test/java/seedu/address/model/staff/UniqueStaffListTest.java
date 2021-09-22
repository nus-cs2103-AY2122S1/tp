package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.staff.exceptions.DuplicateStaffException;
import seedu.address.model.staff.exceptions.StaffNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueStaffListTest {

    private final UniqueStaffList uniqueStaffList = new UniqueStaffList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueStaffList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueStaffList.add(ALICE);
        assertTrue(uniqueStaffList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStaffList.add(ALICE);
        Staff editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStaffList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueStaffList.add(ALICE);
        assertThrows(DuplicateStaffException.class, () -> uniqueStaffList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaff(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaff(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(StaffNotFoundException.class, () -> uniqueStaffList.setStaff(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueStaffList.add(ALICE);
        uniqueStaffList.setStaff(ALICE, ALICE);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(ALICE);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueStaffList.add(ALICE);
        Staff editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStaffList.setStaff(ALICE, editedAlice);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(editedAlice);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueStaffList.add(ALICE);
        uniqueStaffList.setStaff(ALICE, BOB);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(BOB);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueStaffList.add(ALICE);
        uniqueStaffList.add(BOB);
        assertThrows(DuplicateStaffException.class, () -> uniqueStaffList.setStaff(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(StaffNotFoundException.class, () -> uniqueStaffList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueStaffList.add(ALICE);
        uniqueStaffList.remove(ALICE);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaffs((UniqueStaffList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueStaffList.add(ALICE);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(BOB);
        uniqueStaffList.setStaffs(expectedUniqueStaffList);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaffs((List<Staff>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueStaffList.add(ALICE);
        List<Staff> staffList = Collections.singletonList(BOB);
        uniqueStaffList.setStaffs(staffList);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(BOB);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Staff> listWithDuplicateStaffs = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStaffException.class, () -> uniqueStaffList.setStaffs(listWithDuplicateStaffs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStaffList.asUnmodifiableObservableList().remove(0));
    }
}
