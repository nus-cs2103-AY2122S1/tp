package seedu.teachbook.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.student.Student;

/**
 * A utility class containing a list of {@code Class} objects to be used in tests.
 */

public class TypicalClasses {
    private static Class aClass = new ClassBuilder().withName("A").build();
    private static Class bClass = new ClassBuilder().withName("B").build();


    private TypicalClasses() {}

    public static Class getAClass() {
        return aClass;
    }

    public static Class getBClass() {
        return bClass;
    }

    public static List<Class> getTypicalClasses() {
        aClass = new ClassBuilder().withName("A").build();
        bClass = new ClassBuilder().withName("B").build();
        for (Student student : TypicalStudents.getTypicalStudents()) {
            student.setStudentClass(aClass);
            aClass.addStudent(student);
        }
        return new ArrayList<>(Arrays.asList(aClass, bClass));
    }


}
