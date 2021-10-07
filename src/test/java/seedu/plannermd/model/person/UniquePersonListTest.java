package seedu.plannermd.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.plannermd.model.person.exceptions.DuplicatePersonException;
import seedu.plannermd.model.person.exceptions.PersonNotFoundException;

public abstract class UniquePersonListTest<T extends Person> {

    private UniquePersonList<T> personList;
    private T alice;
    private T bob;

    protected abstract UniquePersonList<T> createSamplePersonList();
    //Typical People should not be in sample list
    protected abstract T typicalPersonAlice();
    protected abstract T typicalPersonBob();

    @BeforeEach
    void setUp() {
        personList = createSamplePersonList();
        alice = typicalPersonAlice();
        bob = typicalPersonBob();
    }

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(personList.contains(alice));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        personList.add(alice);
        assertTrue(personList.contains(alice));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        personList.add(alice);
        //TODO: Generic PersonBuilder workaround

        //        T editedAlice = new PatientBuilder(alice)
        //                .withAddress(VALID_ADDRESS_BOB)
        //                .withTags(VALID_TAG_HUSBAND)
        //                .withRisk(VALID_RISK_BOB)
        //                .build();
        //        assertTrue(personList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        personList.add(alice);
        assertThrows(DuplicatePersonException.class, () -> personList.add(alice));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personList.setPerson(null, alice));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personList.setPerson(alice, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> personList.setPerson(alice, alice));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        personList.add(alice);
        personList.setPerson(alice, alice);
        UniquePersonList<T> expectedUniquePersonList = new UniquePersonList<>();
        expectedUniquePersonList.add(alice);
        assertEquals(expectedUniquePersonList, personList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        personList.add(alice);
        //TODO: Generic PersonBuilder workaround

        //        T editedAlice = new PatientBuilder(alice)
        //                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
        //                .withRisk(VALID_RISK_BOB)
        //                .build();
        //        personList.setPerson(alice, editedAlice);
        //        UniquePersonList<T> expectedUniquePersonList = new UniquePersonList<>();
        //        expectedUniquePersonList.add(editedAlice);
        //        assertEquals(expectedUniquePersonList, personList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        personList.add(alice);
        personList.setPerson(alice, bob);
        UniquePersonList<T> expectedUniquePersonList = new UniquePersonList<>();
        expectedUniquePersonList.add(bob);
        assertEquals(expectedUniquePersonList, personList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        personList.add(alice);
        personList.add(bob);
        assertThrows(DuplicatePersonException.class, () -> personList.setPerson(alice, bob));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personList.remove(null));
    }

    @Test
    public void remove_patientDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> personList.remove(alice));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        personList.add(alice);
        personList.remove(alice);
        UniquePersonList<T> expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, personList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personList.setPersons((UniquePersonList<T>) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        personList.add(alice);
        UniquePersonList<T> expectedUniquePersonList = new UniquePersonList<>();
        expectedUniquePersonList.add(bob);
        personList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, personList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personList.setPersons((List<T>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        personList.add(alice);
        List<T> patientList = Collections.singletonList(bob);
        personList.setPersons(patientList);
        UniquePersonList<T> expectedUniquePersonList = new UniquePersonList<>();
        expectedUniquePersonList.add(bob);
        assertEquals(expectedUniquePersonList, personList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<T> listWithDuplicatePersons = Arrays.asList(alice, alice);
        assertThrows(DuplicatePersonException.class, () -> personList.setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
                UnsupportedOperationException.class, () -> personList.asUnmodifiableObservableList().remove(0));
    }
}
