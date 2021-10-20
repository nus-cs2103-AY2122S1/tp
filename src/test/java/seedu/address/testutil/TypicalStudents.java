package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.student.Student;

public class TypicalStudents {

    //some samples, can add more/modify for testing
    public static final Student ALICE = new StudentBuilder()
            .withStudentId("A1234560A")
            .withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withTeleHandle("@Alice")
            .build();
    public static final Student BENSON = new StudentBuilder()
            .withStudentId("A1234561A")
            .withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withTeleHandle("@Benson")
            .build();
    public static final Student CARL = new StudentBuilder()
            .withStudentId("A1234562A")
            .withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withTeleHandle("@Carl")
            .build();
    public static final Student DANIEL = new StudentBuilder()
            .withStudentId("A1234563A")
            .withName("Daniel Meier")
            .withEmail("cornelia@example.com")
            .withTeleHandle("@Daniel")
            .build();
    public static final Student ELLE = new StudentBuilder()
            .withStudentId("A1234564A")
            .withName("Elle Meyer")
            .withEmail("werner@example.com")
            .withTeleHandle("@Elle")
            .build();
    public static final Student FIONA = new StudentBuilder()
            .withStudentId("A1234565A")
            .withName("Fiona Kunz")
            .withEmail("lydia@example.com")
            .withTeleHandle("@Fiona")
            .build();
    public static final Student GEORGE = new StudentBuilder()
            .withStudentId("A1234566A")
            .withName("George Best")
            .withEmail("anna@example.com")
            .withTeleHandle("@George")
            .build();

    /**
     * Returns a list of students.
     * @return A list of students
     */
    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
