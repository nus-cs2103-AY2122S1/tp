package tutoraid.testutil;

import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_NAME_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PROGRESS_LIST_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PROGRESS_LIST_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_PHONE_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tutoraid.model.StudentBook;
import tutoraid.model.student.InitialStudent;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.Phone;
import tutoraid.model.student.ProgressList;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {
    public static final Student ALICE = new StudentBuilder()
            .withStudentName("Alice Pauline")
            .withStudentPhone("94351234")
            .withParentName("Mrs Tan")
            .withParentPhone("94351253")
            .withProgressList(new ArrayList<>())
            .build();
    public static final Student BENSON = new StudentBuilder()
            .withStudentName("Benson Meier")
            .withStudentPhone("98765423")
            .withParentName("Mrs Meier")
            .withParentPhone("98765432")
            .withProgressList(new ArrayList<>())
            .build();
    public static final InitialStudent INITIAL_BENSON = new InitialStudent(
            new StudentName("Benson Meier"),
            new Phone("98765423"),
            new ParentName("Mrs Meier"),
            new Phone("98765432"),
            new ProgressList(),
            new ArrayList<>());
    public static final Student CARL = new StudentBuilder()
            .withStudentName("Carl Kurz")
            .withStudentPhone("95352563")
            .withParentName("Mr Kurz")
            .withParentPhone("95352567")
            .withProgressList(new ArrayList<>())
            .build();
    public static final Student DANIEL = new StudentBuilder()
            .withStudentName("Daniel Meier")
            .withStudentPhone("87652533")
            .withParentName("Mrs Meier")
            .withParentPhone("98765432")
            .withProgressList(new ArrayList<>())
            .build();
    public static final Student ELLE = new StudentBuilder()
            .withStudentName("Elle Meyer")
            .withStudentPhone("9482224")
            .withParentName("Mrs Meyer")
            .withParentPhone("9482290")
            .build();
    public static final Student FIONA = new StudentBuilder()
            .withStudentName("Fiona Kunz")
            .withStudentPhone("9482427")
            .withParentName("Mr Daniel")
            .withParentPhone("9482423")
            .withProgressList(new ArrayList<>())
            .build();
    public static final Student GEORGE = new StudentBuilder()
            .withStudentName("George Best")
            .withStudentPhone("9482442")
            .withParentName("Mrs Kayla")
            .withParentPhone("94824432")
            .withProgressList(new ArrayList<>())
            .build();

    // Manually added
    public static final Student HOON = new StudentBuilder()
            .withStudentName("Hoon Meier")
            .withStudentPhone("8482424")
            .withParentName("Mrs Meier")
            .withParentPhone("98765432")
            .withProgressList(new ArrayList<>())
            .build();
    public static final Student IDA = new StudentBuilder()
            .withStudentName("Ida Mueller")
            .withStudentPhone("8482131")
            .withParentName("Mr Mueller")
            .withParentPhone("8482155")
            .withProgressList(new ArrayList<>())
            .build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder()
            .withStudentName(VALID_STUDENT_NAME_AMY)
            .withStudentPhone(VALID_STUDENT_PHONE_AMY)
            .withParentName(VALID_PARENT_NAME_AMY)
            .withParentPhone(VALID_PARENT_PHONE_AMY)
            .withProgressList(VALID_PROGRESS_LIST_AMY)
            .build();
    public static final Student BOB = new StudentBuilder()
            .withStudentName(VALID_STUDENT_NAME_BOB)
            .withStudentPhone(VALID_STUDENT_PHONE_BOB)
            .withParentName(VALID_PARENT_NAME_BOB)
            .withParentPhone(VALID_PARENT_PHONE_BOB)
            .withProgressList(VALID_PROGRESS_LIST_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {
    } // prevents instantiation

    /**
     * Returns an {@code StudentBook} with all the typical students.
     */
    public static StudentBook getTypicalStudentBook() {
        StudentBook ab = new StudentBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(
                ALICE.copy(),
                BENSON.copy(),
                CARL.copy(),
                DANIEL.copy(),
                ELLE.copy(),
                FIONA.copy(),
                GEORGE.copy()
        ));
    }
}
