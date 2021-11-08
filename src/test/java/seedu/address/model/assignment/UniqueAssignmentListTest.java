package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS1101S_MISSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS2106_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS2103_QUIZ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS1101S_MISSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS2100_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS2106_PROJECT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT_CS1101S_MISSION;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT_CS2103_QUIZ;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.address.testutil.AssignmentBuilder;

class UniqueAssignmentListTest {
    private final UniqueAssignmentList uniqueAssignmentList = new UniqueAssignmentList();

    @Test
    public void contains_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.contains(null));
    }

    @Test
    public void contains_assignmentNotInList_returnsFalse() {
        assertFalse(uniqueAssignmentList.contains(ASSIGNMENT_CS1101S_MISSION));
    }

    @Test
    public void contains_assignmentInList_returnsTrue() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        assertTrue(uniqueAssignmentList.contains(ASSIGNMENT_CS1101S_MISSION));
    }

    @Test
    public void contains_assignmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        Assignment editedCS1101SMission = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION).withCompletedStatus()
                .withDueDate(VALID_DATE_CS2106_PROJECT, VALID_TIME_CS2106_PROJECT).build();
        assertTrue(uniqueAssignmentList.contains(editedCS1101SMission));
    }

    @Test
    public void add_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.add(null));
    }

    @Test
    public void add_duplicateAssignment_throwsDuplicateAssignmentException() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION));
    }

    @Test
    public void setAssignment_nullTargetAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAssignmentList.setAssignment(null, ASSIGNMENT_CS1101S_MISSION));
    }

    @Test
    public void setAssignment_nullEditedAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAssignmentList.setAssignment(ASSIGNMENT_CS1101S_MISSION, null));
    }

    @Test
    public void setAssignment_targetAssignmentNotInList_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () ->
                uniqueAssignmentList.setAssignment(ASSIGNMENT_CS1101S_MISSION, ASSIGNMENT_CS1101S_MISSION));
    }

    @Test
    public void setAssignment_editedAssignmentIsSameAssignment_success() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.setAssignment(ASSIGNMENT_CS1101S_MISSION, ASSIGNMENT_CS1101S_MISSION);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasSameIdentity_success() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        Assignment editedCS1101Mission = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION).withCompletedStatus()
                .withDueDate(VALID_DATE_CS2106_PROJECT, VALID_TIME_CS2106_PROJECT).build();
        uniqueAssignmentList.setAssignment(ASSIGNMENT_CS1101S_MISSION, editedCS1101Mission);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(editedCS1101Mission);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasDifferentIdentity_success() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.setAssignment(ASSIGNMENT_CS1101S_MISSION, ASSIGNMENT_CS2103_QUIZ);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT_CS2103_QUIZ);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasNonUniqueIdentity_throwsDuplicateAssignmentException() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.add(ASSIGNMENT_CS2103_QUIZ);
        assertThrows(DuplicateAssignmentException.class, () ->
                uniqueAssignmentList.setAssignment(ASSIGNMENT_CS1101S_MISSION, ASSIGNMENT_CS2103_QUIZ));
    }

    @Test
    public void delete_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.delete(null));
    }

    @Test
    public void delete_assignmentDoesNotExist_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.delete(ASSIGNMENT_CS1101S_MISSION));
    }

    @Test
    public void delete_existingAssignment_deletesAssignment() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.delete(ASSIGNMENT_CS1101S_MISSION);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void done_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.done(null));
    }

    @Test
    public void done_assignmentDoesNotExist_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.done(ASSIGNMENT_CS1101S_MISSION));
    }

    @Test
    public void done_existingAssignment_markDoneAssignment() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.done(ASSIGNMENT_CS1101S_MISSION);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        Assignment completedCS1101SMission = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION).withCompletedStatus()
                .build();
        expectedUniqueAssignmentList.add(completedCS1101SMission);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullUniqueAssignmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAssignmentList.setAssignments((UniqueAssignmentList) null));
    }

    @Test
    public void setAssignments_uniqueAssignmentList_replacesOwnListWithProvidedUniqueAssignmentList() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT_CS2103_QUIZ);
        uniqueAssignmentList.setAssignments(expectedUniqueAssignmentList);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignments((List<Assignment>) null));
    }

    @Test
    public void setAssignments_list_replacesOwnListWithProvidedList() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        List<Assignment> assignmentList = Collections.singletonList(ASSIGNMENT_CS2103_QUIZ);
        uniqueAssignmentList.setAssignments(assignmentList);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT_CS2103_QUIZ);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_listWithDuplicateAssignments_throwsDuplicateAssignmentException() {
        List<Assignment> listWithDuplicateAssignments =
                Arrays.asList(ASSIGNMENT_CS1101S_MISSION, ASSIGNMENT_CS1101S_MISSION);
        assertThrows(DuplicateAssignmentException.class, () ->
                uniqueAssignmentList.setAssignments(listWithDuplicateAssignments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueAssignmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void clearAllAssignment_listWithAssignments_clearAssignmentList() {
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.add(ASSIGNMENT_CS2103_QUIZ);
        UniqueAssignmentList expectedAssignmentList = new UniqueAssignmentList();
        uniqueAssignmentList.clearAllAssignments();
        assertEquals(expectedAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void sort_assignmentsWithDifferentStatusOnly_completedAssignmentsAtBottomOfList() {
        Assignment completedCS1101SMission = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withDescription("completedCS1101SMission").withCompletedStatus().build();
        uniqueAssignmentList.add(completedCS1101SMission);
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.sort();
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        expectedUniqueAssignmentList.add(completedCS1101SMission);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void sort_assignmentsWithDifferentDateOnly_laterAssignmentsAtBottomOfList() {
        Assignment earlierDateCS1101SMission = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withDescription("earlierDateCS1101SMission")
                .withDueDate(VALID_DATE_CS2106_PROJECT, VALID_TIME_CS1101S_MISSION).build();
        Assignment laterDateCS1101SMission = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withDescription("laterDateCS1101SMission")
                .withDueDate(VALID_DATE_CS2103_QUIZ, VALID_TIME_CS1101S_MISSION).build();
        uniqueAssignmentList.add(laterDateCS1101SMission);
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.add(earlierDateCS1101SMission);
        uniqueAssignmentList.sort();
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(earlierDateCS1101SMission);
        expectedUniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        expectedUniqueAssignmentList.add(laterDateCS1101SMission);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void sort_assignmentsWithDifferentTimeOnly_laterAssignmentsAtBottomOfList() {
        Assignment earlierTimeCS1101SMission = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withDescription("earlierTimeCS1101SMission")
                .withDueDate(VALID_DATE_CS1101S_MISSION, VALID_TIME_CS2106_PROJECT).build();
        Assignment laterTimeCS1101SMission = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withDescription("laterTimeCS1101SMission")
                .withDueDate(VALID_DATE_CS1101S_MISSION, VALID_TIME_CS2100_TUTORIAL).build();
        uniqueAssignmentList.add(laterTimeCS1101SMission);
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.add(earlierTimeCS1101SMission);
        uniqueAssignmentList.sort();
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(earlierTimeCS1101SMission);
        expectedUniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        expectedUniqueAssignmentList.add(laterTimeCS1101SMission);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void sort_assignmentsWithDifferentDateTimeAndStatus_sortedListByCompletedStatusAndDate() {
        Assignment earlierDateTimeCompletedCS1101SMission = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withDescription("earlierDateTimeCompletedCS1101SMission")
                .withDueDate(VALID_DATE_CS2106_PROJECT, VALID_TIME_CS2106_PROJECT).withCompletedStatus().build();
        uniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        uniqueAssignmentList.add(earlierDateTimeCompletedCS1101SMission);
        uniqueAssignmentList.sort();
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ASSIGNMENT_CS1101S_MISSION);
        expectedUniqueAssignmentList.add(earlierDateTimeCompletedCS1101SMission);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }
}
