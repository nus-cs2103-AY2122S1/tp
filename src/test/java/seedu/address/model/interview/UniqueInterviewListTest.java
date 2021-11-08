package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInterviews.ASSISTANT_INTERVIEW;
import static seedu.address.testutil.TypicalInterviews.BOOKKEEPER_INTERVIEW;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.interview.exceptions.DuplicateInterviewException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;
import seedu.address.testutil.InterviewBuilder;

public class UniqueInterviewListTest {

    private final UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();

    @Test
    public void contains_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.contains(null));
    }

    @Test
    public void contains_interviewNotInList_returnsFalse() {
        assertFalse(uniqueInterviewList.contains(ASSISTANT_INTERVIEW));
    }

    @Test
    public void contains_interviewInList_returnsTrue() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        assertTrue(uniqueInterviewList.contains(ASSISTANT_INTERVIEW));
    }

    @Test
    public void contains_interviewWithSameIdentityFieldsInList_returnsTrue() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        Interview editedAssistantInterview = new InterviewBuilder(ASSISTANT_INTERVIEW)
                .withPosition(ADMIN_ASSISTANT)
                .build();
        assertTrue(uniqueInterviewList.contains(editedAssistantInterview));
    }

    @Test
    public void add_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.add(null));
    }

    @Test
    public void add_duplicateInterview_throwsDuplicatePositionException() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        assertThrows(DuplicateInterviewException.class, () -> uniqueInterviewList.add(ASSISTANT_INTERVIEW));
    }

    @Test
    public void setInterview_nullTargetInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueInterviewList.setInterview(null, ASSISTANT_INTERVIEW));
    }

    @Test
    public void setInterview_nullEditedInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueInterviewList.setInterview(ASSISTANT_INTERVIEW, null));
    }

    @Test
    public void setInterview_targetInterviewNotInList_throwsInterviewNotFoundException() {
        assertThrows(InterviewNotFoundException.class, () ->
                uniqueInterviewList.setInterview(ASSISTANT_INTERVIEW, ASSISTANT_INTERVIEW));
    }

    @Test
    public void setInterview_editedInterviewIsSameInterview_success() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        uniqueInterviewList.setInterview(ASSISTANT_INTERVIEW, ASSISTANT_INTERVIEW);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        expectedUniqueInterviewList.add(ASSISTANT_INTERVIEW);
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterview_editedInterviewHasSameIdentity_success() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        Interview editedAssistantInterview = new InterviewBuilder(ASSISTANT_INTERVIEW).build();
        uniqueInterviewList.setInterview(ASSISTANT_INTERVIEW, editedAssistantInterview);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        expectedUniqueInterviewList.add(editedAssistantInterview);
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterview_editedInterviewHasDifferentIdentity_success() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        uniqueInterviewList.setInterview(ASSISTANT_INTERVIEW, BOOKKEEPER_INTERVIEW);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        expectedUniqueInterviewList.add(BOOKKEEPER_INTERVIEW);
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterview_editedInterviewHasNonUniqueIdentity_throwsDuplicateInterviewException() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        uniqueInterviewList.add(BOOKKEEPER_INTERVIEW);
        assertThrows(DuplicateInterviewException.class, () ->
                uniqueInterviewList.setInterview(ASSISTANT_INTERVIEW, BOOKKEEPER_INTERVIEW));
    }

    @Test
    public void remove_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.remove(null));
    }

    @Test
    public void remove_interviewDoesNotExist_throwsInterviewNotFoundException() {
        assertThrows(InterviewNotFoundException.class, () -> uniqueInterviewList.remove(ASSISTANT_INTERVIEW));
    }

    @Test
    public void remove_existingInterview_removesInterview() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        uniqueInterviewList.remove(ASSISTANT_INTERVIEW);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterviews_nullUniqueInterviewList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.setInterviews((UniqueInterviewList) null));
    }

    @Test
    public void setInterviews_uniqueInterviewList_replacesOwnListWithProvidedUniqueInterviewList() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        expectedUniqueInterviewList.add(BOOKKEEPER_INTERVIEW);
        uniqueInterviewList.setInterviews(expectedUniqueInterviewList);
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterviews_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.setInterviews((List<Interview>) null));
    }

    @Test
    public void setInterviews_list_replacesOwnListWithProvidedList() {
        uniqueInterviewList.add(ASSISTANT_INTERVIEW);
        List<Interview> interviewList = Collections.singletonList(BOOKKEEPER_INTERVIEW);
        uniqueInterviewList.setInterviews(interviewList);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        expectedUniqueInterviewList.add(BOOKKEEPER_INTERVIEW);
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterviews_listWithDuplicateInterviews_throwsDuplicateInterviewException() {
        List<Interview> listWithDuplicateInterviews = Arrays.asList(ASSISTANT_INTERVIEW, ASSISTANT_INTERVIEW);
        assertThrows(DuplicateInterviewException.class, () ->
                uniqueInterviewList.setInterviews(listWithDuplicateInterviews));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueInterviewList.asUnmodifiableObservableList().remove(0));
    }
}

