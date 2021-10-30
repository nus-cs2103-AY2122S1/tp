package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tutoraid.model.lesson.exceptions.DuplicateStudentInLessonException;
import tutoraid.model.lesson.exceptions.StudentNotFoundInLessonException;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalStudents;

public class StudentsTest {

    private final Students studentList = new Students(new ArrayList<>());

    @Test
    public void contains_nullStudent_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> studentList.hasStudent(null));
    }

    @Test
    public void contains_studentNotInList_returnsFalse() {
        assertFalse(studentList.hasStudent(TypicalStudents.ALICE));
    }

    @Test
    public void contains_studentInList_returnsTrue() {
        studentList.addStudent(TypicalStudents.ALICE);
        assertTrue(studentList.hasStudent(TypicalStudents.ALICE));
    }

    @Test
    public void add_nullStudent_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> studentList.addStudent(null));
    }

    @Test
    public void add_duplicateStudent_throwsDuplicateStudentException() {
        studentList.addStudent(TypicalStudents.ALICE);
        Assert.assertThrows(DuplicateStudentInLessonException.class, () -> studentList.addStudent(
                TypicalStudents.ALICE));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> studentList.removeStudent(null));
    }

    @Test
    public void remove_studentDoesNotExist_throwsStudentNotFoundException() {
        Assert.assertThrows(StudentNotFoundInLessonException.class, () -> studentList.removeStudent(
                TypicalStudents.ALICE));
    }

    @Test
    public void remove_existingStudent_removesStudent() {
        studentList.addStudent(TypicalStudents.ALICE);
        studentList.removeStudent(TypicalStudents.ALICE);
        Students expectedStudentList = new Students(new ArrayList<>());
        assertEquals(expectedStudentList, studentList);
    }
}
