package seedu.address.model.assignment;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssignmentBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalAssignments.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS1231S_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS3230_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS3230_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS1231S_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS3230_LAB;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT_CS1101S_MISSION;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT_CS1231S_TUTORIAL;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT_CS3230_LAB;

public class AssignmentTest {


    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(ASSIGNMENT_CS1101S_MISSION));

        // null -> returns false
        assertFalse(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(null));

        // same description, all other attributes different -> returns false
        Assignment editedCs1101sAssignment = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withCompletedStatus().withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS1231S_TUTORIAL).build();
        assertTrue(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(editedCs1101sAssignment));

        // different description, all other attributes same -> returns false
        editedCs1101sAssignment = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withDescription("I like CS").build();
        assertFalse(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(editedCs1101sAssignment));

        // different status, all other attributes same -> return true
        Assignment editedCs2106Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withPendingStatus().build();
        assertTrue(ASSIGNMENT_CS3230_LAB.isSameAssignment(ASSIGNMENT_CS3230_LAB));

        // description differs in case, all other attributes same -> returns true
        Assignment editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDescription(VALID_DESCRIPTION_CS3230_LAB.toLowerCase()).build();
        assertTrue(ASSIGNMENT_CS3230_LAB.isSameAssignment(editedCs3230Lab));

        // description has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_DESCRIPTION_CS3230_LAB + " ";
        editedCs3230Lab = new AssignmentBuilder().withDescription(nameWithTrailingSpaces).build();
        assertFalse(ASSIGNMENT_CS3230_LAB.isSameAssignment(editedCs3230Lab));

        // same description and same due date constructed using friendly commands -> returns true
        Assignment cs2100LabDueTmr = new AssignmentBuilder(ASSIGNMENT_CS2100_LAB).build();
        Assignment cs2100LabDueTmrManual = new AssignmentBuilder().withDescription(VALID_DESCRIPTION_CS2100_LAB)
                .withDueDate("tmr", "1300").withPendingStatus().build();
        assertEquals(cs2100LabDueTmr, cs2100LabDueTmrManual);

        // same description and same due date constructed using friendly commands manually -> returns true
        Assignment geq1000QuizDueToday = new AssignmentBuilder(ASSIGNMENT_GEQ1000_QUIZ).build();
        String[] dateArr = LocalDate.now().toString().split("-");
        List<String> list = Arrays.asList(dateArr);
        Collections.reverse(list);
        String date = formatDate(list);
        String[] args = new String[]{
            VALID_DESCRIPTION_GEQ1000_QUIZ,
            new DueDate(date, "2359").toString(),
            String.valueOf(Status.createPendingStatus())};
        Assignment geq1000QuizDueTodayManual = new Assignment(args);
        assertEquals(geq1000QuizDueToday, geq1000QuizDueTodayManual);
    }

    /**
     * Reverses a list containing YYYY/MM/DD to form DD/MM/YYYY
     *
     * @param list containing wrong format local date
     * @return String in the form of DD/MM/YYYY
     */
    private String formatDate(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String s : list) {
            sb.append((s));
            if (index < 2) {
                sb.append("/");
            }
            index++;
        }
        return sb.toString();
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assignment cs3230LabCopy = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB).build();
        assertEquals(cs3230LabCopy, ASSIGNMENT_CS3230_LAB);

        // same object -> returns true
        assertEquals(ASSIGNMENT_CS3230_LAB, ASSIGNMENT_CS3230_LAB);

        // null -> returns false
        assertNotEquals(null, ASSIGNMENT_CS3230_LAB);

        // different type -> returns false
        assertNotEquals(5, ASSIGNMENT_CS3230_LAB);

        // different assignment -> returns false
        assertNotEquals(ASSIGNMENT_CS3230_LAB, ASSIGNMENT_CS1231S_TUTORIAL);

        // different description -> returns false
        Assignment editedCs1231sTutorial = new AssignmentBuilder(ASSIGNMENT_CS1231S_TUTORIAL)
                .withDescription(VALID_DESCRIPTION_CS3230_LAB).build();
        assertNotEquals(ASSIGNMENT_CS1231S_TUTORIAL, editedCs1231sTutorial);

        // different status, change from pending to completed -> returns false
        editedCs1231sTutorial = new AssignmentBuilder(ASSIGNMENT_CS1231S_TUTORIAL).withCompletedStatus().build();
        assertNotEquals(ASSIGNMENT_CS1231S_TUTORIAL, editedCs1231sTutorial);

        // different due date -> returns false
        Assignment editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS1231S_TUTORIAL).build();
        assertNotEquals(ASSIGNMENT_CS3230_LAB, editedCs3230Lab);

        // same time but different date -> returns false
        editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS3230_LAB).build();
        assertNotEquals(ASSIGNMENT_CS3230_LAB, editedCs3230Lab);

        // same date but different time -> returns false
        editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS3230_LAB, VALID_TIME_CS1231S_TUTORIAL).build();
        assertNotEquals(ASSIGNMENT_CS3230_LAB, editedCs3230Lab);

    }

    @Test
    void compareTo() {
        //Assignment with date: 11/01/2021, time: 0800, status: completed
        Assignment comparingAssignment = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB).build();

        //Same values has equal weight
        Assignment assignmentCopy = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB).build();
        assertTrue(comparingAssignment.compareTo(assignmentCopy) == 0);

        //Same time and status with later date(01/02/2021) has more weight-> returns true
        Assignment laterDateAssignment = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS3230_LAB).build();
        assertTrue(laterDateAssignment.compareTo(comparingAssignment) > 0);

        //Same date and status with later time(1800) has more weight-> returns true
        Assignment laterTimeAssignment = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS3230_LAB, VALID_TIME_CS1231S_TUTORIAL).build();
        assertTrue(laterTimeAssignment.compareTo(comparingAssignment) > 0);

        //Same date and time with pending status has less weight-> returns true
        Assignment pendingStatusAssignment = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withPendingStatus().build();
        assertTrue(pendingStatusAssignment.compareTo(comparingAssignment) < 0);

        //Same time with later date(01/02/2021) and pending status has less weight-> returns true
        Assignment laterDateAndPendingAssignment = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS3230_LAB).withPendingStatus().build();
        assertTrue(laterDateAndPendingAssignment.compareTo(comparingAssignment) < 0);

        //Same date with later time(1800) and pending status has less weight-> returns true
        Assignment laterTimeAndPendingAssignment = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS3230_LAB, VALID_TIME_CS1231S_TUTORIAL).withPendingStatus().build();
        assertTrue(laterTimeAndPendingAssignment.compareTo(comparingAssignment) < 0);

        //later date(01/02/2021) and time(1800) and pending status has less weight-> returns true
        Assignment earlierDateTimeAndPendingAssignment = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS1231S_TUTORIAL).withPendingStatus().build();
        assertTrue(earlierDateTimeAndPendingAssignment.compareTo(comparingAssignment) < 0);
    }
}
