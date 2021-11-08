package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.FINALS;
import static seedu.address.testutil.TypicalAssessments.MIDTERMS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.assessment.exceptions.AssessmentNotFoundException;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;
import seedu.address.testutil.AssessmentBuilder;

public class UniqueAssessmentListTest {

    private final UniqueAssessmentList uniqueAssessmentList = new UniqueAssessmentList();

    @Test
    public void contains_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.contains(null));
    }

    @Test
    public void contains_assessmentNotInList_returnsFalse() {
        assertFalse(uniqueAssessmentList.contains(MIDTERMS));
    }

    @Test
    public void contains_assessmentInList_returnsTrue() {
        uniqueAssessmentList.add(MIDTERMS);
        assertTrue(uniqueAssessmentList.contains(MIDTERMS));
    }

    @Test
    public void contains_assessmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAssessmentList.add(MIDTERMS);
        Assessment editedMidterms = new AssessmentBuilder(MIDTERMS).build();
        assertTrue(uniqueAssessmentList.contains(editedMidterms));
    }

    @Test
    public void add_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.add(null));
    }

    @Test
    public void add_duplicateAssessment_throwsDuplicateAssessmentException() {
        uniqueAssessmentList.add(MIDTERMS);
        assertThrows(DuplicateAssessmentException.class, () -> uniqueAssessmentList.add(MIDTERMS));
    }

    @Test
    public void setAssessment_nullTargetAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.setAssessment(null, MIDTERMS));
    }

    @Test
    public void setAssessment_nullEditedAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.setAssessment(MIDTERMS, null));
    }

    @Test
    public void setAssessment_targetAssessmentNotInList_throwsAssessmentNotFoundException() {
        assertThrows(AssessmentNotFoundException.class, () -> uniqueAssessmentList.setAssessment(MIDTERMS, MIDTERMS));
    }

    @Test
    public void setAssessment_editedAssessmentIsSameAssessment_success() {
        uniqueAssessmentList.add(MIDTERMS);
        uniqueAssessmentList.setAssessment(MIDTERMS, MIDTERMS);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(MIDTERMS);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessment_editedAssessmentHasSameIdentity_success() {
        uniqueAssessmentList.add(MIDTERMS);
        Assessment editedMidterms = new AssessmentBuilder(MIDTERMS).build();
        uniqueAssessmentList.setAssessment(MIDTERMS, editedMidterms);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(editedMidterms);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessment_editedAssessmentHasDifferentIdentity_success() {
        uniqueAssessmentList.add(MIDTERMS);
        uniqueAssessmentList.setAssessment(MIDTERMS, FINALS);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(FINALS);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessment_editedAssessmentHasNonUniqueIdentity_throwsDuplicateAssessmentException() {
        uniqueAssessmentList.add(MIDTERMS);
        uniqueAssessmentList.add(FINALS);
        assertThrows(DuplicateAssessmentException.class, () -> uniqueAssessmentList.setAssessment(MIDTERMS, FINALS));
    }

    @Test
    public void remove_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.remove(null));
    }

    @Test
    public void remove_assessmentDoesNotExist_throwsAssessmentNotFoundException() {
        assertThrows(AssessmentNotFoundException.class, () -> uniqueAssessmentList.remove(MIDTERMS));
    }

    @Test
    public void remove_existingAssessment_removesAssessment() {
        uniqueAssessmentList.add(MIDTERMS);
        uniqueAssessmentList.remove(MIDTERMS);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessments_nullUniqueAssessmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.setAssessment((UniqueAssessmentList) null));
    }

    @Test
    public void setAssessments_uniqueAssessmentList_replacesOwnListWithProvidedUniqueAssessmentList() {
        uniqueAssessmentList.add(MIDTERMS);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(FINALS);
        uniqueAssessmentList.setAssessment(expectedUniqueAssessmentList);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.setAssessment((List<Assessment>) null));
    }

    @Test
    public void setAssessments_list_replacesOwnListWithProvidedList() {
        uniqueAssessmentList.add(MIDTERMS);
        List<Assessment> assessmentListList = Collections.singletonList(FINALS);
        uniqueAssessmentList.setAssessment(assessmentListList);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(FINALS);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessments_listWithDuplicateStudents_throwsDuplicateAssessmentException() {
        List<Assessment> listWithDuplicateAssessments = Arrays.asList(MIDTERMS, MIDTERMS);
        assertThrows(DuplicateAssessmentException.class, () -> uniqueAssessmentList
                .setAssessment(listWithDuplicateAssessments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAssessmentList.asUnmodifiableObservableList().remove(0));
    }
}
