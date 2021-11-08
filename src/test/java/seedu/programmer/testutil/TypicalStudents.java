package seedu.programmer.testutil;

import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASS_ID_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASS_ID_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.programmer.testutil.TypicalLabs.getTypicalLabList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.student.Student;

/**
 * A utility class containing a list of {@code student} objects to be used in tests.
 */
public class TypicalStudents {


    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withStudentId("A0212425H").withClassId("B01")
            .withEmail("e0422331@u.nus.edu").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withStudentId("A0212325H").withClassId("B02")
            .withEmail("e0433332@u.nus.edu").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withStudentId("A0112425H").withClassId("B03")
            .withEmail("e0433336@u.nus.edu").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withStudentId("A0512425H").withClassId("B02")
            .withEmail("e0417739@u.nus.edu").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withStudentId("A0612425H").withClassId("B01")
            .withEmail("e0418722@u.nus.edu").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz")
            .withStudentId("A0912425H").withClassId("B11")
            .withEmail("e0418212@u.nus.edu").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best")
            .withStudentId("A0852425H").withClassId("B11")
            .withEmail("e0418617@u.nus.edu").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier")
            .withStudentId("A0123456H").withClassId("B01")
            .withEmail("e0418399@u.nus.edu").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller")
            .withStudentId("A1234567H").withClassId("B01")
            .withEmail("e0422388@u.nus.edu").build();

    // Manually added - student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withStudentId(VALID_STUDENT_ID_AMY)
            .withClassId(VALID_CLASS_ID_AMY).withEmail(VALID_EMAIL_AMY).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withStudentId(VALID_STUDENT_ID_BOB)
            .withClassId(VALID_CLASS_ID_BOB).withEmail(VALID_EMAIL_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**

     * Returns an {@code ProgrammerError} with all the typical students.
     */
    public static ProgrammerError getTypicalProgrammerError() {
        ProgrammerError pe = new ProgrammerError();
        for (Student student : getTypicalStudents()) {
            student.setLabList(getTypicalLabList());
            pe.addStudent(student);
        }
        return pe;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
