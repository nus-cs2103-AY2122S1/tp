package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G02;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalClassmate;
import static seedu.address.testutil.TypicalTutorialClasses.G01;
import static seedu.address.testutil.TypicalTutorialClasses.G02;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TutorialGroupBuilder;

public class ClassmateTest {

    private final Classmate classmate = new Classmate();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), classmate.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> classmate.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyClassmate_replacesData() {
        Classmate newData = getTypicalClassmate();
        classmate.resetData(newData);
        assertEquals(newData, classmate);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student alice = new StudentBuilder(ALICE).build();
        List<Student> newStudents = Arrays.asList(ALICE, alice);
        ClassmateStub newData = new ClassmateStub(newStudents, new ArrayList<>());

        assertThrows(DuplicateStudentException.class, () -> classmate.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> classmate.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInClassmate_returnsFalse() {
        assertFalse(classmate.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInClassmate_returnsTrue() {
        classmate.addStudent(ALICE);
        assertTrue(classmate.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithDifferentAddressAndTagFieldsInClassmate_returnsFalse() {
        classmate.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertFalse(classmate.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> classmate.getStudentList().remove(0));
    }

    @Test
    public void hasTutorialGroup_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> classmate.hasTutorialGroup(null));
    }

    @Test
    public void hasTutorialGroup_tutorialGroupNotInClassmate_returnsFalse() {
        classmate.addTutorialClass(G01);
        assertFalse(classmate.hasTutorialGroup(TUT_01));
    }

    @Test
    public void hasTutorialGroup_tutorialGroupInClassmate_returnsTrue() {
        classmate.addTutorialClass(G01);
        classmate.addTutorialGroup(TUT_01);
        assertTrue(classmate.hasTutorialGroup(TUT_01));
    }

    @Test
    public void hasTutorialGroup_studentWithDifferentClassCodeInClassmate_returnsFalse() {
        classmate.addTutorialClass(G01);
        classmate.addTutorialClass(G02);
        TutorialGroup editedTutorialGroup = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_CLASSCODE_G02).build();
        assertFalse(classmate.hasTutorialGroup(editedTutorialGroup));
    }

    /**
     * A stub ReadOnlyClassmate whose students list can violate interface constraints.
     */
    private static class ClassmateStub implements ReadOnlyClassmate {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<TutorialClass> tutorialClasses = FXCollections.observableArrayList();

        ClassmateStub(Collection<Student> students, Collection<TutorialClass> tutorialClasses) {
            this.students.setAll(students);
            this.tutorialClasses.setAll(tutorialClasses);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<TutorialClass> getTutorialClassList() {
            return tutorialClasses;
        }

    }

}
