package seedu.address.testutil;

import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Student;

public class TypicalTuition {

    // Test Lessons, direct copy from json equivalent
    public static final Student ALICE = TypicalPersons.ALICE;
    public static final Student BENSON = TypicalPersons.BENSON;
    public static final Student CARL = TypicalPersons.CARL;
    public static final Lesson SCIENCE_P2 = TypicalLessons.SCIENCE_P2;
    public static final Lesson MATH_S2 = TypicalLessons.MATH_S2;
    public static final Lesson PHYSICS_S2 = TypicalLessons.PHYSICS_S2;

    private TypicalTuition() {} //prevents instantiation

    /**
     * Returns an {@code AddressBook} with a typical tuition setup.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();

        // set up associations
        List<Student> students = getTypicalPersons();
        List<Lesson> lessons = getTypicalLessons();
        lessons.get(INDEX_SECOND_LESSON.getZeroBased())
                .addStudent(students.get(INDEX_SECOND_PERSON.getZeroBased())); //BENSON enrolled in MATH_S2 lesson

        for (Student student : students) {
            ab.addPerson(student);
        }
        for (Lesson lesson : lessons) {
            ab.addLesson(lesson);
        }
        return ab;
    }

    private static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(SCIENCE_P2.createClone(), MATH_S2.createClone(), PHYSICS_S2));
    }

    public static List<Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE.createClone(), BENSON.createClone(), CARL.createClone()));
    }
}
