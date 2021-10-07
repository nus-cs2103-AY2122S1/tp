package seedu.plannermd.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_RISK_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;
import static seedu.plannermd.testutil.patient.TypicalPatients.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.plannermd.model.person.UniquePersonList;
import seedu.plannermd.model.person.exceptions.DuplicatePersonException;
import seedu.plannermd.model.person.exceptions.PersonNotFoundException;
import seedu.plannermd.testutil.patient.PatientBuilder;

public class UniquePatientListTest {

    private final UniquePersonList<Patient> uniquePatientList = new UniquePersonList<>();

    @Test
    public void contains_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.contains(null));
    }

    @Test
    public void contains_patientNotInList_returnsFalse() {
        assertFalse(uniquePatientList.contains(ALICE));
    }

    @Test
    public void contains_patientInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        assertTrue(uniquePatientList.contains(ALICE));
    }

    @Test
    public void contains_patientWithSameIdentityFieldsInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .withRisk(VALID_RISK_BOB)
                .build();
        assertTrue(uniquePatientList.contains(editedAlice));
    }

    @Test
    public void add_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.add(null));
    }

    @Test
    public void add_duplicatePatient_throwsDuplicatePatientException() {
        uniquePatientList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.add(ALICE));
    }

    @Test
    public void setPatient_nullTargetPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPerson(null, ALICE));
    }

    @Test
    public void setPatient_nullEditedPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPerson(ALICE, null));
    }

    @Test
    public void setPatient_targetPatientNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePatientList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPatient_editedPatientIsSamePerson_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPerson(ALICE, ALICE);
        UniquePersonList<Patient> expectedUniquePatientList = new UniquePersonList<>();
        expectedUniquePatientList.add(ALICE);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatient_editedPatientHasSameIdentity_success() {
        uniquePatientList.add(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .withRisk(VALID_RISK_BOB)
                .build();
        uniquePatientList.setPerson(ALICE, editedAlice);
        UniquePersonList<Patient> expectedUniquePatientList = new UniquePersonList();
        expectedUniquePatientList.add(editedAlice);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatient_editedPatientHasDifferentIdentity_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPerson(ALICE, BOB);
        UniquePersonList<Patient> expectedUniquePatientList = new UniquePersonList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatient_editedPatientHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePatientList.add(ALICE);
        uniquePatientList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove(null));
    }

    @Test
    public void remove_patientDoesNotExist_throwsPatientNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePatientList.remove(ALICE));
    }

    @Test
    public void remove_existingPatient_removesPatient() {
        uniquePatientList.add(ALICE);
        uniquePatientList.remove(ALICE);
        UniquePersonList<Patient> expectedUniquePatientList = new UniquePersonList();
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_nullUniquePatientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPersons((UniquePersonList<Patient>) null));
    }

    @Test
    public void setPatients_uniquePatientList_replacesOwnListWithProvidedUniquePatientList() {
        uniquePatientList.add(ALICE);
        UniquePersonList<Patient> expectedUniquePatientList = new UniquePersonList<>();
        expectedUniquePatientList.add(BOB);
        uniquePatientList.setPersons(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPersons((List<Patient>) null));
    }

    @Test
    public void setPatients_list_replacesOwnListWithProvidedList() {
        uniquePatientList.add(ALICE);
        List<Patient> patientList = Collections.singletonList(BOB);
        uniquePatientList.setPersons(patientList);
        UniquePersonList<Patient> expectedUniquePatientList = new UniquePersonList<>();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_listWithDuplicatePatients_throwsDuplicatePersonException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setPersons(listWithDuplicatePatients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePatientList.asUnmodifiableObservableList().remove(0));
    }
}
