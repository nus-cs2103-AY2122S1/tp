package seedu.teachbook.testutil;

import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.gradeobject.GradingSystem;
import seedu.teachbook.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder()
            .withName("Alice Pauline")
            .withPhone("94351253")
            .withClass("A")
            .withEmail("alice@example.com")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withRemark("Allergic to seafood.")
            .withTags("monitor")
            .withAttendance("Absent 2021-11-02T00:15:10.749009800")
            .build();
    public static final Student BENSON = new StudentBuilder()
            .withName("Benson Meier")
            .withPhone("98765432")
            .withClass("A")
            .withEmail("johnd@example.com")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withRemark("Needs to improve on maths.")
            .withTags("vice monitor")
            .withAttendance("Present 2021-11-02T00:15:10.749009800")
            .build();
    public static final Student CARL = new StudentBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withClass("A")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .build();
    public static final Student ELLE = new StudentBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withClass("A")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .build();
    public static final Student FIONA = new StudentBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withClass("A")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withClass("A").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns a {@code TeachBook} with all the typical students in typical classes.
     */
    public static TeachBook getTypicalTeachBook() {
        TeachBook tb = new TeachBook();
        List<Class> typicalClassList = TypicalClasses.getTypicalClasses();
        Class typicalA = typicalClassList.get(0);
        Class typicalB = typicalClassList.get(1);
        Class studentClassA = new Class(typicalA.getClassName());
        Class studentClassB = new Class(typicalB.getClassName());
        studentClassA.setStudentsOfThisClass(typicalA.getUniqueStudentListOfThisClass());
        studentClassB.setStudentsOfThisClass(typicalB.getUniqueStudentListOfThisClass());
        tb.addClass(studentClassA);
        tb.addClass(studentClassB);
        GradingSystem typicalGradeSystem = new GradingSystem(Arrays.asList(new Grade("A") , new Grade("B")));
        tb.setGradingSystem(typicalGradeSystem);
        return tb;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON));
    }
}
