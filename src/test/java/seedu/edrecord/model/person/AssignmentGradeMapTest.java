package seedu.edrecord.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.testutil.TypicalAssignments.IP;
import static seedu.edrecord.testutil.TypicalAssignments.PE;
import static seedu.edrecord.testutil.TypicalAssignments.TP;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.edrecord.model.assignment.Grade;
import seedu.edrecord.model.assignment.Score;

public class AssignmentGradeMapTest {
    private static final Grade VALID_GRADE = new Grade(Optional.of(new Score("50")), Grade.GradeStatus.GRADED);
    private static final Grade VALID_GRADE_2 = new Grade(Optional.empty(), Grade.GradeStatus.SUBMITTED);

    @Test
    public void isValidAdd() {
        AssignmentGradeMap map = new AssignmentGradeMap();
        map.add(IP, VALID_GRADE);
        assertTrue(map.getGrades().containsKey(IP));
        assertTrue(map.getGrades().containsValue(VALID_GRADE));
    }

    @Test
    public void isValidAddAll() {
        AssignmentGradeMap map = new AssignmentGradeMap();
        map.add(IP, VALID_GRADE);
        map.add(TP, VALID_GRADE);
        map.add(PE, VALID_GRADE_2);

        AssignmentGradeMap otherMap = new AssignmentGradeMap();
        otherMap.addAll(map);
        assertEquals(map, otherMap);

        otherMap.addAll(map);
        assertEquals(map, otherMap); // adding all again should overwrite previous entries
    }

    @Test
    public void isEmpty() {
        AssignmentGradeMap map = new AssignmentGradeMap();
        assertTrue(map.isEmpty());

        map.add(IP, VALID_GRADE);
        assertFalse(map.isEmpty());
    }

    @Test
    public void isValidFindGrade() {
        AssignmentGradeMap map = new AssignmentGradeMap();
        map.add(TP, VALID_GRADE);

        assertEquals(map.findGrade(TP), VALID_GRADE);
    }

    @Test
    public void isInvalidFindGrade() {
        AssignmentGradeMap map = new AssignmentGradeMap();
        map.add(TP, VALID_GRADE);

        assertNull(map.findGrade(IP));
    }

    @Test
    public void isValidEquals() {
        AssignmentGradeMap map = new AssignmentGradeMap();
        map.add(IP, VALID_GRADE);
        map.add(TP, VALID_GRADE);

        AssignmentGradeMap otherMap = new AssignmentGradeMap();
        otherMap.add(IP, VALID_GRADE);
        otherMap.add(TP, VALID_GRADE);

        assertEquals(map, otherMap);
    }

    @Test
    public void isInvalidEquals() {
        AssignmentGradeMap map = new AssignmentGradeMap();
        map.add(IP, VALID_GRADE);
        map.add(TP, VALID_GRADE);

        AssignmentGradeMap otherMap = new AssignmentGradeMap();
        otherMap.addAll(map);

        map.add(PE, VALID_GRADE_2);
        assertNotEquals(map, otherMap);
    }

    @Test
    public void isValidRemove() {
        AssignmentGradeMap map = new AssignmentGradeMap();
        map.add(IP, VALID_GRADE);
        map.add(TP, VALID_GRADE);

        map.removeGrade(TP);
        assertNull(map.findGrade(TP));
    }
}
